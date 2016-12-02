'use strict';

angular
	.module('MediaticApp', ['ngRoute',
							'ui.bootstrap', 
							'MemberSheet', 
							'MediaSheet',
							'Media',
							'NewMedia'])

	.config(function($routeProvider) {
		$routeProvider.otherwise({
			redirectTo: '/media'
		});
	});