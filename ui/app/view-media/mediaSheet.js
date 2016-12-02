angular
	.module('MediaSheet', [])
	.config(function($routeProvider) {
		$routeProvider.when('/mediaSheet', {
		    templateUrl: 'view-media/mediaSheet.html',
		    controller: 'MediaSheetCtrl',
		    controllerAs: 'mediaSheet'
		});
	})