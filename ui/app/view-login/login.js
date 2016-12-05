'use strict';

angular.module('Login', [])

.config(function($routeProvider) {
	$routeProvider.when('/login', {
		templateUrl: './view-login/login.html',
	    controller: 'LoginCtrl',
	    controllerAs: 'login'
	});
})

.controller('LoginCtrl', ['$location', '$http', '$rootScope', 'AuthentificationService', 
					function($location, $http, $rootScope, AuthentificationService) {
	
	var self = this;
	
	// Don't display the login/mdp error
	this.diplayMessError = false;
	
	// Page's title
	$rootScope.titre = 'Mediatic';
	
	// Menu active
	$rootScope.mediaActive = '';
	$rootScope.memberActive = '';
	
	// Disconnect if previously was connected
	AuthentificationService.disconnect();
	
	// Check the authentication
	this.checkAuth = function() {
		AuthentificationService.connect(this.id, this.psw).then(function(resolution) {
			if (resolution) {
				// Redirection
				$location.url('/media');
			} else {
				// display mess erreur
				self.diplayMessError = true;
			}
		});
	}

}])


.factory('AuthentificationService', function($http, IpService) {
	// F5 bypass
	//var defaut = 'Basic YXplOmF6ZQ==';
	//$http.defaults.headers.common['Authorization'] = defaut;
	
	// If the user is connected
	var connected = false;
	
	// Rights for the creation buttons
	var rightMemberCreation = false;
	var rightMediaCreation = false;
	
	return {
		// Check the authentication
		connect : function(login, password) {
			var auth = 'Basic' + btoa(login + ':' + password);
			var config = {
				headers : {
					'Authorization' : auth
				}
			};
			var url = 'http://'+IpService+':8090/resource/connexion.rights';
			
			return $http.get(url, config).then(function(response) {
				// Authentication OK
				connected = true;
				// Retrieve of the rights
				for (var i in response.data) {
					if (response.data[i] == 'creation-adherent') {
						rightMemberCreation = true;
					} else if (response.data[i] == 'creation-media') {
						rightMediaCreation = true;
					}
				}
				// Authentication for the http services
				$http.defaults.headers.common['Authorization'] = auth;
				return true;
			}, function() {
				// Authentication NOP
				connected = false;
				$http.defaults.headers.common['Authorization'] = 'Basic';
				return false;
			});
		},
		
		// Tells if the user is authenticated
		isConnected : function() {
			return connected;
		},
		
		// Disconnect the user
		disconnect: function() {
			connected = false;
		},
		
		// Has the right to create a new member
		hasRightMemberCreation: function() {
			return rightMemberCreation;
		},
		
		// Has the right to create a new member
		hasRightMediaCreation: function() {
			return rightMediaCreation;
		}
	}
});

;