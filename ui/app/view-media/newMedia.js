'use strict';

angular.module('NewMedia', [])

.config(function($routeProvider) {
	$routeProvider.when('/newMedia', {
		templateUrl: './view-media/newMedia.html',
	    controller: 'NewMediaCtrl',
	    controllerAs: 'newMedia'
	});
})

.controller('NewMediaCtrl', ['$location', '$http', '$rootScope', 'AuthenticationService', 'IpService', '$timeout', '$scope',
						 function($location, $http, $rootScope, AuthenticationService, IpService, $timeout, $scope) {
	var self = this;
	
	// Check if authenticated
	if (! AuthenticationService.isConnected()) {
		// Redirection toward login
		$location.url('/login');
	}

	// Page's title
	$rootScope.titre = 'Nouveau Media';
	
	// Menu active
	$rootScope.mediaActive = 'active';
	$rootScope.memberActive = '';
	
	// Message creation success
	this.messCreateOk = false;
	
	// Redirection
	this.goBackMedia = function() {
		$location.url('/media');
	}
	
	// Add a new media
	this.addMedia = function() {
		var url = 'http://'+IpService+':8090/resource/media.creation';
		var mediaToSend = {titre: self.mediaToAdd.title, 
						   auteur: self.mediaToAdd.author, 
						   type: self.mediaToAdd.type};
		$http.post(url, mediaToSend).then(function(response) {
			// Show a message
			self.messCreateOk = true;
			$timeout(function(){
		    	$scope.doFade = true;
		    }, 3000);
			// Clear the fields
			self.mediaToAdd = {};
		});
	};
	
	
}]);