angular.module('CreateMember', [])
	.controller('CreateMemberCtrl', function() {
		var obj = this;
		
		createMemberCtrl.setMemberSheet = function() {
			console.log("Lastname: " + createMemberCtrl.lastname);
			console.log("Firstname: " + createMemberCtrl.firstname);
			console.log("Birthdate: " + createMemberCtrl.birthdate);
			console.log("E-mail: " + createMemberCtrl.email);
			console.log("Address: " + createMemberCtrl.ligne1);
			console.log("Address: " + createMemberCtrl.ligne2);
			console.log("Postal Code: " + createMemberCtrl.postalcode);
			console.log("Town: " + createMemberCtrl.town);
			console.log("Age: " + createMemberCtrl.age);
			console.log("Subscription Amount: " + createMemberCtrl.subscriptionAmount);
			console.log("Subscription Date: " + createMemberCtrl.subscriptionDate);
			console.log("Loans: " + createMemberCtrl.loans);
			
			var data = {
                id: parseInt($routeParams.idMember),
                nom: createMemberCtrl.lastname,
                prenom: createMemberCtrl.firstname,
                date_naissance: createMemberCtrl.birthdate,
                email: createMemberCtrl.email,
                adresse: {ligne1: createMemberCtrl.ligne1, ligne2: createMemberCtrl.ligne2, codepostal: createMemberCtrl.postalcode, ville: createMemberCtrl.town},
                cotisation: {debut: createMemberCtrl.subscriptionDate, fin: createMemberCtrl.subscriptionEndDate, montant: createMemberCtrl.subscriptionAmount},
                age: createMemberCtrl.age,
                emprunt: createMemberCtrl.loans,
                nombre_media: createMemberCtrl.nbLoans
            };
			
			console.log("Data à envoyer: ");
			console.log(data);
		}
		
	})
	.config(function($routeProvider) {
		$routeProvider.when('/createMember', {
			templateUrl : '/view-member/createMember.html',
			controller : 'CreateMemberCtrl',
			controllerAs : 'createMemberCtrl'})
	});