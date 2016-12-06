'use strict';

angular
	.module('MediaticApp', ['ngRoute',
							'ui.bootstrap',
							'tableSort',
							'Pagination',
							'Login',
							'SearchMember',
							'MemberSheet',
							'CreateMember',
							'MediaSheet',
							'Media',
							'NewMedia'])

	.config(function($routeProvider) {
		$routeProvider.otherwise({
			redirectTo: '/login'
		});
	});