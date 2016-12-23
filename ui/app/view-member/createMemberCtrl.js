angular.module('CreateMember', [])
	.controller('CreateMemberCtrl', ['MemberSheetService',  'AuthenticationService', '$rootScope', '$location', 
								function(MemberSheetService, AuthenticationService, $rootScope, $location) {
		var obj = this;
		
		// Check if authenticated
		/*if (! AuthenticationService.isConnected()) {
			// Redirection toward login
			$location.url('/login');
		}*/

		// Page's title
		$rootScope.titre = 'Nouveau Membre';
		
		// Menu active
		$rootScope.mediaActive = '';
		$rootScope.memberActive = 'active';
		
		// Subscription date set to today
		obj.subscriptionDate = new Date(Date.now());
		
		// Age to set
		obj.getAge = function() {
			if (obj.birthdate != undefined) {
				var currentDate = new Date(Date.now());			
				obj.age = currentDate.getFullYear() - obj.birthdate.getFullYear();
			}
		}
		
		obj.setMemberSheet = function() {
			
			var currentDate = new Date();			
			var age = currentDate.getFullYear() - obj.birthdate.getFullYear();
			
			obj.age = age;
			
			var endDate = new Date();
			endDate.setFullYear(new Date().getFullYear() + 1);
			
			obj.subscriptionDate = new Date();
			
			var data = {
                identifier: obj.lastname + obj.firstname,
				lastname: obj.lastname,
				firstname: obj.firstname,
				email: obj.email,
				birthdate: obj.birthdate.toISOString().substring(0, 10),
				numberOfLoans: 0,
				gender: obj.gender,
				address: obj.address,
				postalcode: obj.postalcode,
				city: obj.town,
				subscription: {
					amount: obj.subscriptionAmount,
					paymentDate: obj.subscriptionDate,
					subscriptionEndDate: endDate
				}
            };

			MemberSheetService.setSheet(data).then(function(response) {
				console.log("L'adhérent a été correctement enregistré");
			}, function(reason) {
				console.log("L'adhérent n'a pas pu être enregistré: " + reason);
			});
		};
		
		obj.returnToSearch = function() {
			$location.path('/searchMember');
		};
		
	}])
	
	.config(function($routeProvider) {
		$routeProvider.when('/createMember', {
			templateUrl : '/view-member/createMember.html',
			controller : 'CreateMemberCtrl',
			controllerAs : 'createMemberCtrl'})
	});