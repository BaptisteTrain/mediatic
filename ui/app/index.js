'use strict';

angular
	.module('MediaticApp', ['ngRoute', 
							'MemberSheet', 
							'MediaSheet',
							'Media',
							'NewMedia'])
	.config(function($routeProvider) {
		$routeProvider.otherwise({
			redirectTo: '/media'
		});
	});