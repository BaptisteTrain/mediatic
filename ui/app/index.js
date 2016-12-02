'use strict';

angular
	.module('MediaticApp', ['ngRoute',
							'ui.bootstrap',
							'tableSort',
							'MemberSheet', 
							'MediaSheet',
							'Media',
							'NewMedia'])

	.config(function($routeProvider) {
		$routeProvider.otherwise({
			redirectTo: '/media'
		});
	});