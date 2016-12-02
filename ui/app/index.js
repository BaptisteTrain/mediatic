'use strict';

angular.module('MediaticApp', ['ngRoute', 'MemberSheetApp'])
.config(function($routeProvider) {
	$routeProvider.otherwise({
		redirectTo: '/mediatic'
	});
})
