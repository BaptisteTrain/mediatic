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
		console.log('verifAuth');
		
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


.factory('AuthentificationService', function($http) {
	// F5 bypass
	//var defaut = 'Basic YXplOmF6ZQ==';
	//$http.defaults.headers.common['Authorization'] = defaut;
	var connected;
	return {
		// Check the authentication
		connect : function(login, password) {
			var auth = 'Basic' + btoa(login + ':' + password);
			var config = {
				headers : {
					'Authorization' : auth
				}
			};
			
			var logPsw = {login: login, mdp:password};
			
			var url = 'http://192.168.10.34:8090/resource/connexion.login';
			return $http.post(url, logPsw).then(function() {
				connected = true;
				$http.defaults.headers.common['Authorization'] = auth;
				return true;
			}, function() {
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
		}
	}
});

;