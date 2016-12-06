'use strict';

angular.module('Login', [])

.config(function($routeProvider) {
	$routeProvider.when('/login', {
		templateUrl: './view-login/login.html',
	    controller: 'LoginCtrl',
	    controllerAs: 'login'
	});
})

.controller('LoginCtrl', ['$location', '$http', '$rootScope', 'AuthenticationService', 
					function($location, $http, $rootScope, AuthenticationService) {
	
	var self = this;
	
	// Don't display the login/mdp error
	this.diplayMessError = false;
	
	// Page's title
	$rootScope.titre = 'Mediatic';
	
	// Menu active
	$rootScope.mediaActive = '';
	$rootScope.memberActive = '';
	
	// Disconnect if previously was connected
	AuthenticationService.disconnect();
	
	// Check the authentication
	this.checkAuth = function() {
		AuthenticationService.connect(this.id, this.psw).then(function(resolution) {
			if (resolution) {
				// Redirection
				$location.url('/media');
			} else {
				// display mess erreur
				self.diplayMessError = true;
			}
		});
	}

}]);