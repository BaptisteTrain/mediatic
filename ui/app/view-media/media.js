'use strict';

angular.module('Media', ['ngRoute'])

.config(function($routeProvider) {
	$routeProvider.when('/media', {
	    templateUrl: 'view-media/media.html',
	    controller: 'MediaCtrl',
	    controllerAs: 'media'
	});
})

.controller('MediaCtrl', ['$location', '$http', '$sce', '$rootScope',
						 function($location, $http, $sce, $rootScope) {
	var self = this;

	// Titre de la page
	$rootScope.titre = "M&eacute;dia";
	
	
}]);
	
