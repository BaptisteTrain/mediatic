angular
	.module('MediaSheet', [])
	.config(function($routeProvider) {
		$routeProvider.when('/mediaSheet/:id', {
		    templateUrl  : './view-media/mediaSheet.html',
		    controller   : 'MediaSheetCtrl',
		    ControllerAs : 'MediaSheet'
		});
	})

	.controller('MediaSheetCtrl', function($scope, $http, $routeParams, $rootScope, IpService) {
		var ctrl = this;
		var id   = $routeParams.id;
		var url1 = 'http://'+IpService+':8090/resource/media.accession?id='+id;
		var url2 = 'http://'+IpService+':8090/resource/adherent.recherche';
		//console.log('Je commande à charger le média.');
		$scope.myMedia = {};
		$http.get(url1)
			.then(function(response) {
				//console.log('J\'ai fini de charger le média.');
				$scope.myMedia = response.data;
			});
		$http.get(url2)
			.then(function(response){
				$scope.mySearch = response.data;
			});
		// show/hide search form
		$scope.isNavCollapsed = true;
		$scope.isCollapsed = false;
		$scope.isCollapsedHorizontal = false;
	})
	.controller('MediaSheetMediaCtrl', function($scope, $http, $rootScope, IpService) {
		var ctrl = this;
		ctrl.emprunteurs = [];
		$scope.$watch('myMedia.emprunteurs', function(newValue, oldValue){
			ctrl.emprunteurs.splice(0,ctrl.emprunteurs.length);
			//console.log('J\'utilise le média.', newValue, oldValue);
			for(var i in $scope.myMedia.emprunteurs) {
				ctrl.emprunteurs.push({
					nom    : $scope.myMedia.emprunteurs[i].adherent.nom,
					prenom : $scope.myMedia.emprunteurs[i].adherent.prenom
				});
			}
			ctrl.media = {
				mediaEditId  	: $scope.myMedia.id,
				mediaEditTitre  : $scope.myMedia.titre,
				mediaEditAuteur : $scope.myMedia.auteur,
				mediaEditType   : $scope.myMedia.type
			};
			// Page's title
			$rootScope.titre = $scope.myMedia.titre;
		});

		// Edit a media
		ctrl.editMedia = function(id, title, author, type) {
			var url = 'http://'+IpService+':8090/resource/media.modification';
			var data = {
				id	   : id,
				titre  : title, 
				auteur : author,
				type   : type
			}
			$http.post(url, data)
				.success(function (data, status, headers, config) {
					console.log('SUCCESS');
	            })
	            .error(function (data, status, header, config) {
	            	console.log('ERROR');
	            });
		}
	})
	.controller('MediaSheetLoanCtrl', function($scope, $http, IpService) {
		var ctrl = this;
		ctrl.mySearchList = [];
		
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
		
		ctrl.addLoan = function() {
			console.log('kkkkkk');
			if(loanForm.$invalid){
				return;
			}
			var url = 'http://'+IpService+':8090/resource/emprunt.ajout';
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
				id_adherent : ctrl.loanMember,
				id_media	: idMedia,
				depart		: nextDate
			};
			
			console.log(nextDate.toLocaleString());
			$http.post(url, data)
				.success(function (data, status, headers, config) {
					console.log('SUCCESS');
	            })
	            .error(function (data, status, header, config) {
	            	console.log('ERROR');
	            });
			
		}
	});