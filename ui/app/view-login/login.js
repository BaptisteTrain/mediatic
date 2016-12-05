'use strict';

angular.module('Login', [])

.config(function($routeProvider) {
	$routeProvider.when('/login', {
		templateUrl: './view-login/login.html',
	    controller: 'LoginCtrl',
	    controllerAs: 'login'
	});
})

.controller('LoginCtrl', ['$location', '$http', '$scope', 'AuthentificationService', 
					function($location, $http, $scope, AuthentificationService) {
	
	var self = this;
	
	this.diplayMessError = false;
	
	// Check the authentification
	this.checkAuth = function() {
		console.log('verifAuth');
		
		AuthentificationService.connect(this.id, this.psw).then(function(resolution) {
			console.log('AFTER');
			if (resolution) {
				// Redirection
				console.log('END OK');
				$location.url('/media');
			} else {
				console.log('END NOP');
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
		// Check the authentification
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
				console.log('OK CONNECT');
				return true;
			}, function() {
				connected = false;
				$http.defaults.headers.common['Authorization'] = 'Basic';
				console.log('NOP CONNECT');
				return false;
			});
		},
		
		// Tells if the user is authentified
		isConnected : function() {
			return connected;
		}
	}
});

;