'use strict';

angular
	.module('MediaticApp', ['ngRoute', 
							'MemberSheet', 
							'MediaSheet',
							'Media'])
	.config(function($routeProvider) {
		$routeProvider.otherwise({
			redirectTo: '/media'
		});
	});