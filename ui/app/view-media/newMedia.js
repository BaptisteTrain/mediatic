'use strict';

angular.module('NewMedia', [])

.config(function($routeProvider) {
	$routeProvider.when('/newMedia', {
		templateUrl: './view-media/newMedia.html',
	    controller: 'NewMediaCtrl',
	    controllerAs: 'newMedia'
	});
})

.controller('NewMediaCtrl', ['$location', '$http', '$sce', '$rootScope',
						 function($location, $http, $sce, $rootScope) {
	var self = this;

	// Titre de la page
	$rootScope.titre = "Nouveau Media";
	
	// Redirection
	this.goBackMedia = function() {
		$location.url('/media');
	}
	
	
}]);