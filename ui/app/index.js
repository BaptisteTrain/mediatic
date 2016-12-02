'use strict';

angular
	.module('MyApp', ['ngRoute', 'MemberSheetApp'])
	.config(function($routeProvider) {
		$routeProvider.otherwise({
			redirectTo: '/mediatic'
		});
	});