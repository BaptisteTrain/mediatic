'use strict';

angular
	.module('MediaticApp', ['ngRoute',
							'ui.bootstrap',
							'tableSort',
							'Login',
							'SearchMember',
							'MemberSheet', 
							'MediaSheet',
							'Media',
							'NewMedia'])

	.config(function($routeProvider) {
		$routeProvider.otherwise({
			redirectTo: '/login'
		});
	});