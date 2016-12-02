angular.module('MemberSheet', [])
	.controller('MemberSheetController', ['$scope', function($scope) {
		var mbshCtrl = this;
		
		mbshCtrl.loans =
			[
				{
					reference: '123456798',
					type: 'cd',
					title: 'Star Wars',
					loandate: '02/11/2016'
				},
				
				{
					reference: '123456789',
					type: 'book',
					title: 'Le livre de la jungle',
					loandate: '03/11/2016'
				},
				
				{
					reference: '543216798',
					type: 'dvd',
					title: 'Suits: avocats sur mesure',
					loandate: '08/11/2016'
				}
			];
		
		mbshCtrl.books =
			[
				
			];
	}])
	.config(function($routeProvider) {
		$routeProvider.when('/member/sheet', {
			templateUrl: '/member/fiche_adherent.html',
			controller: 'MemberSheetController',
			controllerAs: 'mbshCtrl'
		});
	})