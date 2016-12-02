angular.module('MemberSheetApp', [])
	.controller('MemberSheetController', ['$scope', function($scope) {
		var msCtrl = this;
	}])
	.config(function($routeProvider) {
		$routeProvider.when('/member', {
			templateUrl: '/member/fiche_adherent.html',
			controller: 'MemberSheetController',
			controllerAs: 'msCtrl'
		});
	})