angular.module('MemberSheet', [])
	.controller('MemberSheetController', ['$http', '$routeParams', 'MemberSheetService', 'MediaListService', function($http, $routeParams, MemberSheetService, MediaListService) {
		var mbshCtrl = this;

		mbshCtrl.isNavCollapsed = true;
		mbshCtrl.isCollapsed = false;
		mbshCtrl.isCollapsedHorizontal = false;
		
		console.log($routeParams.idMember);
		
		console.log("Ici");
		MemberSheetService.getSheet($routeParams.idMember).then(function(liste) {
			console.log("La fiche du membre est: ");
			console.log(liste);
			mbshCtrl.lastname = liste.nom;
			mbshCtrl.firstname = liste.prenom;
			var pattern = "/(\d{4})\-(\d{2})\-(\d{2})/";
			mbshCtrl.birthdate = new Date(liste.date_naissance);
			mbshCtrl.email = liste.email;
			mbshCtrl.address = liste.adresse.ligne1 + " " + liste.adresse.ligne2;
			/**/mbshCtrl.ligne1 = liste.adresse.ligne1;
			/**/mbshCtrl.ligne2 = liste.adresse.ligne2;
			mbshCtrl.postalcode =  liste.adresse.codepostal;
			mbshCtrl.town =  liste.adresse.ville;
			mbshCtrl.age = liste.age;
			mbshCtrl.subscriptionAmount = liste.cotisation.montant;
			mbshCtrl.subscriptionDate = new Date(liste.cotisation.debut);
			/**/mbshCtrl.subscriptionEndDate = liste.cotisation.fin;
			mbshCtrl.loans = liste.emprunt;
			mbshCtrl.nbLoans = liste.nombre_media;
		});
		
		
		mbshCtrl.getMedias = function() {
			console.log("Titre: " + mbshCtrl.title);
			console.log("Author: " + mbshCtrl.author);
			console.log("Type: " + mbshCtrl.type);
			MediaListService.getList(mbshCtrl.title || "",mbshCtrl.author || "",mbshCtrl.type || "").then(function(listeMedia) {
				console.log("Liste des médias: ");
				console.log(listeMedia);
				mbshCtrl.medias = listeMedia;
			});
		};
		
		mbshCtrl.setMemberSheet = function() {
			console.log("ID: " + $routeParams.idMember);
			console.log("Lastname: " + mbshCtrl.lastname);
			console.log("Firstname: " + mbshCtrl.firstname);
			console.log("Birthdate: " + mbshCtrl.birthdate.toISOString().substring(0, 10));
			console.log("E-mail: " + mbshCtrl.email);
			console.log("Address: " + mbshCtrl.ligne1);
			console.log("Address: " + mbshCtrl.ligne2);
			console.log("Postal Code: " + mbshCtrl.postalcode);
			console.log("Town: " + mbshCtrl.town);
			console.log("Subscription Date: " + mbshCtrl.subscriptionDate.toISOString().substring(0, 10));
			console.log("Subscription End Date: " + mbshCtrl.subscriptionEndDate);
			console.log("Subscription Amount: " + mbshCtrl.subscriptionAmount);
			console.log("Age: " + mbshCtrl.age);
			console.log("Loans: " + mbshCtrl.loans);
			console.log("Number of Loans: " + mbshCtrl.nbLoans);
			
			var data = {
                id: parseInt($routeParams.idMember),
                nom: mbshCtrl.lastname,
                prenom: mbshCtrl.firstname,
                date_naissance: mbshCtrl.birthdate.toISOString().substring(0, 10),
                email: mbshCtrl.email,
                adresse: {ligne1: mbshCtrl.ligne1, ligne2: mbshCtrl.ligne2, codepostal: mbshCtrl.postalcode, ville: mbshCtrl.town},
                cotisation: {debut: mbshCtrl.subscriptionDate.toISOString().substring(0, 10), fin: mbshCtrl.subscriptionEndDate, montant: parseInt(mbshCtrl.subscriptionAmount)},
                age: mbshCtrl.age,
                emprunt: mbshCtrl.loans,
                nombre_media: mbshCtrl.nbLoans
            };
			
			console.log("Data à envoyer: ");
			console.log(data);
			
			MemberSheetService.setSheet(data).then(function(response) {
				console.log("Response: ");
				console.log(response);
			});
		};
		

		
		mbshCtrl.AddLoan = function() {
			console.log("ID Adhérent: " + $routeParams.idMember);
			console.log("ID Média: " + mbshCtrl.idMedia);
			console.log("Date: " + new Date().toISOString().substring(0, 10));
			
			var data = {
				id_adherent: parseInt($routeParams.idMember),
				id_media: parseInt(mbshCtrl.idMedia),
				depart: new Date().toISOString().substring(0, 10)
            };
			
			console.log("Data à envoyer: ");
			console.log(data);
			
			MemberSheetService.setLoan(data).then(function(response) {
				console.log("Response: ");
				console.log(response);
				
				MemberSheetService.getSheet($routeParams.idMember).then(function(liste) {
					mbshCtrl.loans = liste.emprunt;
					mbshCtrl.nbLoans = liste.nombre_media;
				});
			});
		};
	}])
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
	.factory('MemberSheetService', function($http) {
		var url = 'http://192.168.10.34:8090/resource/';
		
		return {
			getSheet : function(id) {
				var resource = "adherent.accession?id=";
				console.log("ID: " + id);
				console.log("URL + Resource + ID: " + url + resource + id);
				return $http.get(url + resource + id).then(function(response) {
					console.log("Data: " + response.data);
					return response.data;
				});
			},
			
			setSheet : function(data) {
				var resource = "adherent.modification";
				console.log("URL + Resource: " + url + resource);
				return $http.post(url + resource, data).then(function(response) {
					console.log("Data: " + response.data);
					return response.data;
				});
			},
			
			setLoan : function(data) {
				var resource = "emprunt.ajout";
				console.log("URL + Resource: " + url + resource);
				return $http.post(url + resource, data).then(function(response) {
					console.log("Data: " + response.data);
					return response.data;
				});
			}
		}
	})
	.factory('MediaListService', function($http) {
		var urlMedia = 'http://192.168.10.34:8090/resource/media.recherche';
		
		return {
			getList : function(title,author,type) {
				var request = "?titre=" + title + "&auteur=" + author + "&type=" + type;
				
				return $http.get(urlMedia + request).then(function(responseMedia) {
					console.log("Data: " + responseMedia.data);
					return responseMedia.data;
				});
			}
		}
	})
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
	.config(function($routeProvider) {
		$routeProvider.when('/member/:idMember', {
			templateUrl: '/view-member/memberSheet.html',
			controller: 'MemberSheetController',
			controllerAs: 'mbshCtrl'
		});
	})