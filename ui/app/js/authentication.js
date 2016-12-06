angular.module('MediaticApp').factory('AuthenticationService', function($http, IpService) {
	
	// If the user is connected
	var connected = false;
	
	// Rights for the creation buttons
	var rightMemberCreation = false;
	var rightMediaCreation = false;
	
	return {
		// Check the authentication
		connect : function(login, password) {
			var auth = 'Basic ' + btoa(login + ':' + password);
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