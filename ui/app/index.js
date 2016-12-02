'use strict';

angular
	.module('MediaticApp', ['ngRoute', 'MemberSheetApp', 'MediaSheetApp'])
	.config(function($routeProvider) {
		$routeProvider.otherwise({
			redirectTo: '/mediatic'
		});
	});