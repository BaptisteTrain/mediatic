angular.module('CreateMember', [])
	.controller('CreateMemberCtrl', ['$http', 'MemberSheetService',  function($http, MemberSheetService) {
		var obj = this;
		
		obj.setMemberSheet = function() {
			console.log("Lastname: " + obj.lastname);
			console.log("Firstname: " + obj.firstname);
			console.log("Birthdate: " + obj.birthdate);
			console.log("E-mail: " + obj.email);
			console.log("Address: " + obj.ligne1);
			console.log("Address: " + obj.ligne2);
			console.log("Postal Code: " + obj.postalcode);
			console.log("Town: " + obj.town);
			console.log("Age: " + obj.age);
			console.log("Subscription Amount: " + obj.subscriptionAmount);
			console.log("Subscription Date: " + obj.subscriptionDate);
			console.log("Loans: " + obj.loans);
			
			var data = {
                nom: obj.lastname,
                prenom: obj.firstname,
                date_naissance: obj.birthdate,
                email: obj.email,
                adresse: {ligne1: obj.ligne1, ligne2: obj.ligne2, codepostal: obj.postalcode, ville: obj.town},
                cotisation: {debut: obj.subscriptionDate, fin: obj.subscriptionEndDate, montant: obj.subscriptionAmount},
                age: obj.age,
                emprunt: obj.loans,
                nombre_media: obj.nbLoans
            };
			
			MemberSheetService.setSheet(data).then(function(response) {
				console.log("L'adhérent a été correctement enregistré");
			}, function(reason) {
				console.log("L'adhérent n'a pas pu être enregistré");
			});
			
			console.log("Data à envoyer: ");
			console.log(data);
		}
		
	}])
	.config(function($routeProvider) {
		$routeProvider.when('/createMember', {
			templateUrl : '/view-member/createMember.html',
			controller : 'CreateMemberCtrl',
			controllerAs : 'createMemberCtrl'})
	});