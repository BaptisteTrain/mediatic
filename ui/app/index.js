'use strict';

angular
	.module('MediaticApp', ['ngRoute',
							'ui.bootstrap',
							'tableSort',
							'Login',
							'SearchMember',
							'MemberSheet',
							'CreateMember',
							'MediaSheet',
							'Media',
							'NewMedia'])

	.config(function($routeProvider) {
		$routeProvider.otherwise({
			redirectTo: '/login'
		});
	})
	
	.controller('IndexCtrl', function(AuthenticationService) {
		// Check the authentication
		this.isConnect = function(){
			return AuthenticationService.isConnected();
		};
	});