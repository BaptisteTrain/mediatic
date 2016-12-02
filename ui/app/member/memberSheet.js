angular.module('MemberSheetApp', [])
	.controller('MemberSheetController', function() {
		var msCtrl = this;
	})
	.config(function($routeProvider) {
		$routeProvider.when('/member', {
			templateUrl: '/view-catalog/fiche_adherent.html',
			controller: 'MemberSheetController',
			controllerAs: 'msCtrl'
		});
	})