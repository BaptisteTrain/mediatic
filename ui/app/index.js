'use strict';

angular
	.module('MediaticApp', ['ngRoute',
							'ui.bootstrap',
							'MemberSearch',
							'MemberSheet', 
							'MediaSheet',
							'Media',
							'NewMedia'])

	.config(function($routeProvider) {
		$routeProvider.otherwise({
			redirectTo: '/media'
		});
	});