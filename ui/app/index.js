'use strict';

angular.module('app', ['ngRoute', 'MemberSheetApp'])
.config(function($routeProvider) {
	$routeProvider.otherwise({
		redirectTo: '/mediatic'
	});
})
