angular.module('CreateMember', [])
	.controller('CreateMemberCtrl', ['MemberSheetService',  'AuthenticationService', '$rootScope', '$location', 
								function(MemberSheetService, AuthenticationService, $rootScope, $location) {
		var obj = this;
		
		// Check if authenticated
		if (! AuthenticationService.isConnected()) {
			// Redirection toward login
			$location.url('/login');
		}

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

			var data = {
                'nom': obj.lastname,
                'prenom': obj.firstname,
                'date_naissance': obj.birthdate,
                'email': obj.email,
                'adresse': {'ligne1': '', 'ligne2': '', 'codepostal': obj.postalcode, 'ville': obj.town},
                'cotisation': {'debut': new Date(Date.now()), 'fin': '', 'montant': obj.subscriptionAmount},
                'age': 0,
                'emprunt': [],
                'nombre_media': 0
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