'use strict';

angular
	.module('MediaticApp', ['ngRoute', 
							'MemberSheet', 
							'MediaSheet',
							'Media',
							'ui.bootstrap'])
	.config(function($routeProvider) {
		$routeProvider.otherwise({
			redirectTo: '/media'
		});
	});