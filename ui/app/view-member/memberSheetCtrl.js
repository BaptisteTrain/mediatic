/* AUTHOR: Matthieu */

angular.module('MemberSheet', [])
	.controller('MemberSheetController', ['$http', '$rootScope', '$routeParams', '$location', '$scope', '$timeout', 'IpService', 'MemberSheetService', 'AuthenticationService',
		function($http, $rootScope, $routeParams, $location, $scope, $timeout, IpService, MemberSheetService, AuthenticationService) {
		var mbshCtrl = this;
		
		mbshCtrl.itemsPerPage = 5;
		
		// Check if authenticated
		/*if (! AuthenticationService.isConnected()) {
			// Redirection toward login
			$location.url('/login');
		}*/
		
		// Menu active
		$rootScope.mediaActive = '';
		$rootScope.memberActive = 'active';
		
		
		//mbshCtrl.medias;
		
		mbshCtrl.itemsPerPage = 10;
		
		/* Variables pour la fonction Collapse d'Ajout Emprunt */
		mbshCtrl.isNavCollapsed = true;
		mbshCtrl.isCollapsed = false;
		mbshCtrl.isCollapsedHorizontal = false;
		
		/* Récupération de la Fiche Adhérent en fonction de l'ID passé en paramètre dans la barre d'adresse */
		MemberSheetService.getSheet($routeParams.idMember).then(function(liste) {
			//console.log("La fiche du membre est: ");
			//console.log(liste);
			mbshCtrl.identifier = liste.identifier;
			mbshCtrl.lastname = liste.lastname;
			mbshCtrl.firstname = liste.firstname;
			mbshCtrl.email = liste.email;
			mbshCtrl.birthdate = new Date(liste.birthdate);
			mbshCtrl.numberOfLoans = liste.numberOfLoans;
			mbshCtrl.gender = liste.gender;
			mbshCtrl.address = liste.address;
			mbshCtrl.postalcode =  liste.postalcode;
			mbshCtrl.town = liste.city;
			mbshCtrl.age = new Date().getYear() - mbshCtrl.birthdate.getYear();
			mbshCtrl.subscription = liste.subscription;
			mbshCtrl.subscriptionAmount = liste.subscription.amount;
			mbshCtrl.subscriptionDate = new Date(liste.subscription.paymentDate);
			mbshCtrl.subscriptionEndDate = new Date(liste.subscription.subscriptionEndDate);
			
			// Page's title
			$rootScope.titre = "Member page: " + mbshCtrl.lastname + " " + mbshCtrl.firstname;
			
			MemberSheetService.getLoan($routeParams.idMember).then(function(loans) {
				console.log("Emprunts: ");
				console.log(loans);
				mbshCtrl.loans = loans;
			});
		},
		function(response) {
			console.log("Cet Adhérent n'existe pas. Redirection sur la page d'Ajout d'un nouvel adhérent.");
			$location.path('/createMember'); //Si l'Adhérent n'existe pas on effectue une redirection vers la page d'Ajout d'un nouveau Membre.
		});
		
		/* Récupérer la Liste des Médias en fonction des paramètres du formulaire (MemberLoanSearchForm) */
		mbshCtrl.getMedias = function() {
			//console.log("Titre: " + mbshCtrl.title);
			//console.log("Author: " + mbshCtrl.author);
			//console.log("Type: " + mbshCtrl.type);
			MemberSheetService.getMedias().then(function(listeMedia) {
				mbshCtrl.medias = listeMedia;
				console.log(mbshCtrl.medias);
			});
		};
		
		/* Envoi du contenu de la Fiche Adhérent vers la BDD */
		mbshCtrl.setMemberSheet = function() {
			//console.log("ID: " + $routeParams.idMember);
			//console.log("Lastname: " + mbshCtrl.lastname);
			//console.log("Firstname: " + mbshCtrl.firstname);
			//console.log("Birthdate: " + mbshCtrl.birthdate.toISOString().substring(0, 10));
			//console.log("E-mail: " + mbshCtrl.email);
			//console.log("Address: " + mbshCtrl.ligne1);
			//console.log("Address: " + mbshCtrl.ligne2);
			//console.log("Postal Code: " + mbshCtrl.postalcode);
			//console.log("Town: " + mbshCtrl.town);
			
			/* Tableau contenant les données à envoyer à la BDD */
			var data = {
				id: parseInt($routeParams.idMember),
				identifier: mbshCtrl.identifier,
				lastname: mbshCtrl.lastname,
				firstname: mbshCtrl.firstname,
				email: mbshCtrl.email,
				birthdate: mbshCtrl.birthdate.toISOString().substring(0, 10),
				numberOfLoans: mbshCtrl.loans.length,
				gender: mbshCtrl.gender,
				address: mbshCtrl.address,
				postalcode: mbshCtrl.postalcode,
				city: mbshCtrl.town,
				subscription: mbshCtrl.subscription
			};
			
			//console.log("Data à envoyer: ");
			//console.log(data);
			
			/* Lancement de la requête */
			MemberSheetService.setSheet(data).then(function(response) {
				console.log("La Fiche de l'Adhérent a bien été modifiée.");
				mbshCtrl.varFct();
				//console.log("Response: ");
				//console.log(response);
			}, function(response) {
				$scope.$emit('editMediaError');
			});
		};
		
		/* Ajouter un Emprunt à l'Adhérent actuel en fonction du Media sélectionné (MemberLoanAddForm)*/
		mbshCtrl.AddLoan = function() {
			//console.log("ID Adhérent: " + $routeParams.idMember);
			//console.log("ID Média: " + mbshCtrl.idMedia);
			//console.log("Date: " + new Date().toISOString().substring(0, 10));
			
			/* Tableau contenant les données à envoyer à la BDD */
			var data = {
				id_adherent: parseInt($routeParams.idMember),
				id_media: parseInt(mbshCtrl.idMedia),
				depart: new Date().toISOString().substring(0, 10)
            };
			
			//console.log("Data à envoyer: ");
			//console.log(data);
			
			/* Lancement de la requête */
			MemberSheetService.setLoan(data).then(function(response) {
				console.log("Le Média a été ajouté à la Liste d'Emprunts de l'Adhérent.");
				//console.log("Response: ");
				//console.log(response);
				
				/* Requête pour mettre à jour la Liste des Emprunts affichée */
				MemberSheetService.getSheet($routeParams.idMember).then(function(liste) {
					mbshCtrl.loans = liste.emprunt;
					mbshCtrl.nbLoans = liste.nombre_media;
				});
			});
		};
		
		mbshCtrl.returnToSearch = function() {
			$location.path('/searchMember');
		};
		
		mbshCtrl.varTest = false;
		mbshCtrl.doFade = false;
		
		mbshCtrl.varFct = function() {
			mbshCtrl.varTest = true;
			mbshCtrl.doFade = true;
			
			$timeout(function(){
		    	mbshCtrl.varTest = false;
		    }, 5000);

			mbshCtrl.doFade = false;
		};
		
		/*$scope.$on('addLoan', function(event, data){
		    $scope.doFade = false;
		    $scope.showSuccess = true;
		    
		    $timeout(function(){
		    	$scope.doFade = true;
				$scope.showSuccess = false;
		    }, 3000);
		});
		
		$scope.$on('errorLoan', function(event, data){
		    $scope.doFade = false;
		    $scope.showError = true;
		    
		    $timeout(function(){
		    	$scope.doFade = true;
				$scope.showError = false;
		    }, 3000);
		});
		
		$scope.$on('editMediaError', function(event, data){
		    $scope.doFade = false;
		    $scope.showErrorEdit = true;
		    
		    $timeout(function(){
		    	$scope.doFade = true;
				$scope.showErrorEdit = false;
		    }, 3000);
		});
		
		$scope.$on('editMediaSuccess', function(event, data){
		    $scope.doFade = false;
		    $scope.showSuccessEdit = true;
		    
		    $timeout(function(){
		    	$scope.doFade = true;
				$scope.showSuccessEdit = false;
		    }, 3000);
		});*/
		
		
		
	}])
	/* Filtre qui calcule le nombre de jours séparant la Date du jour et la Date sur laquelle le filtre est appliqué */
	.filter('remainingDays', function() {
		return function(input) {
			if(input == undefined) {
				return 0;
			}
			
			var today = new Date().getTime();
			var date = new Date(input).getTime();
			var result = Math.trunc((date - today) / 1000 / 60 / 60 / 24);
			
			if(result > 0) {
				return result;
			}
			else {
				return 0;
			}
		}
	})
	/* Services permettant de communiquer avec la BDD */
	.factory('MemberSheetService', function($http, IpService) {
		//var url = 'http://' + IpService + ':8090/resource/';
		var url = 'http://localhost:8080/mediatic/';
		
		return {
			/* Récupère l'Adhérent en fonction de l'ID */
			getSheet : function(id) {
				//var resource = "adherent.accession?id=";
				var resource = "api/member/";
				//console.log("ID: " + id);
				//console.log("URL + Resource + ID: " + url + resource + id);
				return $http.get(url + resource + id).then(function(response) {
					//console.log("Data: " + response.data);
					return response.data;
				});
			},
			
			/* Modifie un Adhérent avec les données passées en paramètres */
			setSheet : function(data) {
				//var resource = "adherent.modification";
				var resource = "api/member/update";
				
				if(data.id==undefined){
					//var resource = "adherent.creation";
					var resource = "api/member/create";
				}
				//console.log("URL + Resource: " + url + resource);
				return $http.post(url + resource, data).then(function(response) {
					//console.log("Data: " + response.data);
					return response.data;
				});
			},
			
			getLoan : function(id) {
				var resource = "api/member/loans/";
				return $http.get(url + resource + id).then(function(response) {
					return response.data;
				});
			},
			
			/* Ajout d'une Emprunt pour un Adhérent concernant un Media donné */
			setLoan : function(data) {
				var resource = "loan/new";
				//console.log("URL + Resource: " + url + resource);
				return $http.post(url + resource, data).then(function(response) {
					//console.log("Data: " + response.data);
					return response.data;
				});
			},
			
			/* Récupère les Medias en fonction du Titre, de l'Auteur et du Type */
			getMedias : function() {
				var resource = "media/all";
				return $http.get(url + resource).then(function(response) {
					//console.log("Data: " + response.data);
					return response.data;
				});
			}
		}
	})
	/* Attribut date permettnat de formater une date selon le pattern passé en paramètre */ /* N'est plus utilisé pour l'instant. */
	.directive('date', function (dateFilter) {
	    return {
	        require:'ngModel',
	        link:function (scope, elm, attrs, ctrl) {

	            var dateFormat = attrs['date'] || 'yyyy-MM-dd';
	           
	            ctrl.$formatters.unshift(function (modelValue) {
	                return dateFilter(modelValue, dateFormat);
	            });
	        }
	    };
	})
	/* Configuration de la Route */
	.config(function($routeProvider) {
		$routeProvider.when('/member/:idMember', {
			templateUrl: '/view-member/memberSheet.html',
			controller: 'MemberSheetController',
			controllerAs: 'mbshCtrl'
		});
	})