angular
	.module('MediaSheet', [])
	.config(function($routeProvider) {
		$routeProvider.when('/mediaSheet/:id', {
		    templateUrl  : './view-media/mediaSheet.html',
		    controller   : 'MediaSheetCtrl',
		    ControllerAs : 'MediaSheet'
		});
	})
	.controller('MediaSheetCtrl', function($scope, $http, $routeParams, $rootScope) {
		var ctrl = this;
		var id   = $routeParams.id;
		var url1 = 'http://192.168.1.93:8090/resource/media.accession?id='+id;
		var url2 = 'http://192.168.1.93:8090/resource/adherent.recherche';
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
		// Page's title
		$rootScope.titre = 'MediaSheet';
	})
	.controller('MediaSheetMediaCtrl', function($scope, $http) {
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
		});

		// Edit a media
		ctrl.editMedia = function(id, title, author, type) {
			var url = 'http://192.168.1.93:8090/resource/media.modification';
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
	.controller('MediaSheetLoanCtrl', function($scope, $http) {
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
	});