angular.module('CreateMember', [])
	.controller('CreateMemberCtrl', ['MemberSheetService',  function(MemberSheetService) {
		var obj = this;
		
		obj.setMemberSheet = function() {
			
			/*var currentDate = new Date();			
			var age = currentDate.getFullYear() - obj.birthdate.getFullYear();
			*/
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
		}
		
	}])
	.config(function($routeProvider) {
		$routeProvider.when('/createMember', {
			templateUrl : '/view-member/createMember.html',
			controller : 'CreateMemberCtrl',
			controllerAs : 'createMemberCtrl'})
	});