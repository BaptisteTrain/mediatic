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
	$rootScope.titre = 'Nouveau Media';
	
	// Redirection
	this.goBackMedia = function() {
		$location.url('/media');
	}
	
	// Add a new media
	this.addMedia = function() {
		var url = 'http://192.168.10.34:8090/resource/media.creation';
		var mediaToSend = {titre: self.mediaToAdd.title, auteur: self.mediaToAdd.author, type: self.mediaToAdd.type};
		$http.post(url, mediaToSend).then(function(response) {});
	};
	
	
}]);