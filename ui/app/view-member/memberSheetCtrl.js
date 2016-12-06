/* AUTHOR: Matthieu */

angular.module('MemberSheet', [])
	.controller('MemberSheetController', ['$http', '$routeParams', '$location', 'IpService', 'MemberSheetService', function($http, $routeParams, $location, IpService, MemberSheetService) {
		var mbshCtrl = this;
		mbshCtrl.medias;
		
		mbshCtrl.itemsPerPage = 10;
		
		/* Variables pour la fonction Collapse d'Ajout Emprunt */
		mbshCtrl.isNavCollapsed = true;
		mbshCtrl.isCollapsed = false;
		mbshCtrl.isCollapsedHorizontal = false;
		
		/* Récupération de la Fiche Adhérent en fonction de l'ID passé en paramètre dans la barre d'adresse */
		MemberSheetService.getSheet($routeParams.idMember).then(function(liste) {
			//console.log("La fiche du membre est: ");
			//console.log(liste);
			mbshCtrl.lastname = liste.nom;
			mbshCtrl.firstname = liste.prenom;
			mbshCtrl.birthdate = new Date(liste.date_naissance);
			mbshCtrl.email = liste.email;
			mbshCtrl.address = liste.adresse.ligne1 + " " + liste.adresse.ligne2;
			/* Not used in form */mbshCtrl.ligne1 = liste.adresse.ligne1;
			/* Not used in form */mbshCtrl.ligne2 = liste.adresse.ligne2;
			mbshCtrl.postalcode =  liste.adresse.codepostal;
			mbshCtrl.town =  liste.adresse.ville;
			mbshCtrl.age = liste.age;
			mbshCtrl.subscriptionAmount = liste.cotisation.montant;
			mbshCtrl.subscriptionDate = new Date(liste.cotisation.debut);
			mbshCtrl.loans = liste.emprunt;
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
			MemberSheetService.getMedias(mbshCtrl.title || "",mbshCtrl.author || "",mbshCtrl.type || "").then(function(listeMedia) {
				mbshCtrl.medias = listeMedia;
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
				nom: mbshCtrl.lastname,
				prenom: mbshCtrl.firstname,
				date_naissance: mbshCtrl.birthdate.toISOString().substring(0, 10),
				email: mbshCtrl.email,
				adresse: {ligne1: mbshCtrl.ligne1, ligne2: mbshCtrl.ligne2, codepostal: mbshCtrl.postalcode, ville: mbshCtrl.town}
			};
			
			//console.log("Data à envoyer: ");
			//console.log(data);
			
			/* Lancement de la requête */
			MemberSheetService.setSheet(data).then(function(response) {
				console.log("La Fiche de l'Adhérent a bien été modifiée.");
				//console.log("Response: ");
				//console.log(response);
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
		var url = 'http://' + IpService + ':8090/resource/';
		
		return {
			/* Récupère l'Adhérent en fonction de l'ID */
			getSheet : function(id) {
				var resource = "adherent.accession?id=";
				//console.log("ID: " + id);
				//console.log("URL + Resource + ID: " + url + resource + id);
				return $http.get(url + resource + id).then(function(response) {
					//console.log("Data: " + response.data);
					return response.data;
				});
			},
			
			/* Modifie un Adhérent avec les données passées en paramètres */
			setSheet : function(data) {
				var resource = "adherent.modification";
				if(data.id==undefined){
					var resource = "adherent.creation";
				}
				//console.log("URL + Resource: " + url + resource);
				return $http.post(url + resource, data).then(function(response) {
					//console.log("Data: " + response.data);
					return response.data;
				});
			},
			
			/* Ajout d'une Emprunt pour un Adhérent concernant un Media donné */
			setLoan : function(data) {
				var resource = "emprunt.ajout";
				//console.log("URL + Resource: " + url + resource);
				return $http.post(url + resource, data).then(function(response) {
					//console.log("Data: " + response.data);
					return response.data;
				});
			},
			
			/* Récupère les Medias en fonction du Titre, de l'Auteur et du Type */
			getMedias : function(title,author,type) {
				var resource = "media.recherche";
				var request = "?titre=" + title + "&auteur=" + author + "&type=" + type;
				//console.log("URL + Resource + Request: " + url + resource + request);
				return $http.get(url + resource + request).then(function(response) {
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