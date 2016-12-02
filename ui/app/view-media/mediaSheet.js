angular
	.module('MediaSheet', [])
	.config(function($routeProvider) {
		$routeProvider.when('/mediaSheet/:id', {
		    templateUrl  : './view-media/mediaSheet.html',
		    controller   : 'MediaSheetCtrl',
		    ControllerAs : 'MediaSheet'
		});
	})
	.controller('MediaSheetCtrl', function($scope, $http, $routeParams) {
		var ctrl = this;
		var id   = $routeParams.id;
		var url  = 'http://192.168.1.93:8090/resource/media.accession?id='+id;
		console.log('Je commande à charger le média.');
		$scope.myMedia = {};
		$http.get(url)
			.then(function(response) {
				console.log('J\'ai fini de charger le média.');
				$scope.myMedia = response.data;
			});
		$scope.isNavCollapsed = true;
		$scope.isCollapsed = false;
		$scope.isCollapsedHorizontal = false;
	})
	.controller('MediaSheetMediaCtrl', function($scope) {
		var ctrl = this;
		ctrl.emprunteurs = [];
		$scope.$watch('myMedia.emprunteurs', function(newValue, oldValue){
			ctrl.emprunteurs.splice(0,ctrl.emprunteurs.length);
			console.log('J\'utilise le média.', newValue, oldValue);
			for(var i in $scope.myMedia.emprunteurs) {
				ctrl.emprunteurs.push({
					nom    : $scope.myMedia.emprunteurs[i].adherent.nom,
					prenom : $scope.myMedia.emprunteurs[i].adherent.prenom
				});
			}
			ctrl.media = {
				mediaEditTitre  : $scope.myMedia.titre,
				mediaEditAuteur : $scope.myMedia.auteur,
				mediaEditType   : $scope.myMedia.type
			};
		});
	})
	.controller('MediaSheetLoanCtrl', function($scope, $http, $routeParams) {
		
	});