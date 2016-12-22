angular
	.module('MediaSheet', [])
	.config(function($routeProvider) {
		$routeProvider.when('/mediaSheet/:id', {
		    templateUrl  : './view-media/mediaSheet.html',
		    controller   : 'MediaSheetCtrl',
		    ControllerAs : 'MediaSheet'
		});
	})
	// the main controller
	.controller('MediaSheetCtrl', function($scope, $http, $routeParams, $rootScope, $location, IpService, AuthenticationService, $timeout) {
		var ctrl = this;
		
		// Check if authenticated
		/*if (! AuthenticationService.isConnected()) {
			// Redirection toward login
			$location.url('/login');
		}*/
		
		// Menu active
		$rootScope.mediaActive = 'active';
		$rootScope.memberActive = '';

		// Load of the media
		var id   = $routeParams.id;
		console.log("id = "+id);
		var url1 = 'http://localhost:8080/mediatic/media/detail/'+id;
		var url2 = 'http://localhost:8080/mediatic/api/member/allmembers';

		$scope.myMedia = {};
		function loadData(){
			$http.get(url1)
				.then(function(response) {
					$scope.myMedia = response.data;
					console.log("myMedia = ", $scope.myMedia);
				});
			$http.get(url2)
				.then(function(response){
					$scope.mySearch = response.data;
				});
		}
		loadData();
		
		$scope.$on('addLoan', function(event, data){
			loadData();
		    $scope.doFade = false;
		    
		    $scope.showSuccess = true;
		    
		    $timeout(function(){
		    	$scope.doFade = true;
				$scope.showSuccess = false;
		    }, 3000);
		});
		
		$scope.$on('errorLoan', function(event, data){
		    $scope.doFade = false;
		    
		    $scope.showError = true;
		    
		    $timeout(function(){
		    	$scope.doFade = true;
				$scope.showError = false;
		    }, 3000);
		});
		
		$scope.$on('editMediaError', function(event, data){
		    $scope.doFade = false;
		    
		    $scope.showErrorEdit = true;
		    
		    $timeout(function(){
		    	$scope.doFade = true;
				$scope.showErrorEdit = false;
		    }, 3000);
		});
		
		$scope.$on('editMediaSuccess', function(event, data){
		    $scope.doFade = false;
		    
		    $scope.showSuccessEdit = true;
		    
		    $timeout(function(){
		    	$scope.doFade = true;
				$scope.showSuccessEdit = false;
		    }, 3000);
		});

		// hide success and error message 
		$scope.showSuccess = false;
		$scope.showError = false;
		$scope.showSuccessEdit = false;
		$scope.showErrorEdit = false;
		console.log('showerror = ', $scope.showError);
		//$scope.doFade = false;
		// show/hide search form
		$scope.isNavCollapsed = true;
		$scope.isCollapsed = false;
		$scope.isCollapsedHorizontal = false;
	})
	// The controller of media detail and borrower
	.controller('MediaSheetMediaCtrl', function($scope, $http, $rootScope, IpService) {
		var ctrl = this;
		ctrl.emprunteurs = [];
		
		ctrl.itemsPerPage = 10;
		
		$scope.$watch('myMedia.emprunteurs', function(newValue, oldValue){
			ctrl.emprunteurs.splice(0,ctrl.emprunteurs.length);
			//console.log('J\'utilise le média.', newValue, oldValue);
			for(var i in $scope.myMedia.emprunteurs) {
				ctrl.emprunteurs.push({
					nom    : $scope.myMedia.emprunteurs[i].adherent.nom,
					prenom : $scope.myMedia.emprunteurs[i].adherent.prenom
				});
			}
			//console.log('Les emprunteurs : ', ctrl.emprunteurs);
			ctrl.media = {
				mediaEditId  	: $scope.myMedia.id,
				mediaEditTitre  : $scope.myMedia.title,
				mediaEditAuteur : $scope.myMedia.author,
				mediaEditType   : $scope.myMedia.type
			};
			console.log('$scope.myMedia : ', $scope.myMedia, "$scope.myMedia.title : ", $scope.myMedia.title);
			// Page's title
			$rootScope.titre = $scope.myMedia.titre;
		});
		
		// get the media detail
		$scope.$watch('myMedia', function(newValue, oldValue){
			ctrl.media = {
				mediaEditId  	: $scope.myMedia.id,
				mediaEditTitre  : $scope.myMedia.title,
				mediaEditAuteur : $scope.myMedia.author,
				mediaEditType   : $scope.myMedia.type
			};
			console.log('new $scope.myMedia : ', $scope.myMedia, "$scope.myMedia.title : ", $scope.myMedia.title);
		});

		// Edit a media
		ctrl.editMedia = function(id, title, author, type) {
			var url = 'http://localhost:8080/mediatic/media/update';
			var data = {
				id	   : id,
				title  : title, 
				author : author,
				type   : type
			}
			$http.post(url, data)
				.success(function (data, status, headers, config) {
					console.log('SUCCESS');
					$scope.$emit('editMediaSuccess');
	            })
	            .error(function (data, status, header, config) {
	            	console.log('ERROR');
	            	$scope.$emit('editMediaError');
	            });
		}
	})
	// The controller of loan media
	.controller('MediaSheetLoanCtrl', function($scope, $http, IpService) {
		var ctrl = this;
		ctrl.mySearchList = [];
		
		ctrl.itemsPerPage = 10;
		
		ctrl.searchMember = function(){
			$scope.$watch('mySearch', function(newValue, oldValue){
			
				ctrl.mySearchList.splice(0,ctrl.mySearchList.length);
				for(var i in $scope.mySearch) {
					ctrl.mySearchList.push({
						id 			: $scope.mySearch[i].id,
						firstname   : $scope.mySearch[i].nom,
						secondename : $scope.mySearch[i].prenom
					});
				}
				//console.log(ctrl.mySearchList);
			});
		}

		ctrl.addLoan = function(idMember) {
			if(loanForm.$invalid){
				return;
			}
			var url = 'http://localhost:8080/mediatic/loan/new';
			var time;

			var idMedia   = $scope.myMedia.id;
			var typeMedia = $scope.myMedia.type;
			if(typeMedia == 'Livre') {
				time = 30;
			}else{
				time = 15;
			}
			var today     = '05/12/2016';
			var tabDate   = today.split('/');
			var nextDate  = new Date(tabDate[2], tabDate[1]-1, + tabDate[0] + 30);
			
			var data      = {
				id_adherent : idMember,
				id_media	: idMedia,
				depart		: nextDate
			};
			
			$http.post(url, data)
				.success(function (data, status, headers, config) {
					console.log('SUCCESS');
					$scope.$emit('addLoan');
					ctrl.loanMember = null;
					$scope.isNavCollapsed = true;
	            })
	            .error(function (data, status, header, config) {
	            	console.log('ERROR');
	            	$scope.$emit('errorLoan');
	            });
		}
	});