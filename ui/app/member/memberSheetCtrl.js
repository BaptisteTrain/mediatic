angular.module('MemberSheet', [])
	.controller('MemberSheetController', ['$http', '$routeParams', 'MemberSheetService', 'MediaListService', function($http, $routeParams, MemberSheetService, MediaListService) {
		var mbshCtrl = this;
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
			mbshCtrl.postalcode =  liste.adresse.codepostal;
			mbshCtrl.town =  liste.adresse.ville;
			mbshCtrl.age = liste.age;
			mbshCtrl.subscriptionAmount = liste.cotisation.montant;
			mbshCtrl.subscriptionDate = new Date(liste.cotisation.debut);
			mbshCtrl.loans = liste.emprunt;
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
		
		
		
		/*mbshCtrl.loans =
			[
				{
					reference: '123456798',
					type: 'cd',
					title: 'Star Wars',
					loandate: '02/11/2016'
				},
				
				{
					reference: '123456789',
					type: 'book',
					title: 'Le livre de la jungle',
					loandate: '03/11/2016'
				},
				
				{
					reference: '543216798',
					type: 'dvd',
					title: 'Suits: avocats sur mesure',
					loandate: '08/11/2016'
				}
			];*/
		
		/*mbshCtrl.medias =
			[
				{
					title: 'Java pour les Ploucs',
					author: 'Nadir ALLAM',
					reference: '978-2075074209',
					type: 'book'
				},
				
				{
					title: 'L\'informatique est un long fleuve tranquille',
					author: 'Fanny COUTURIER',
					reference: 'B01K2WUR9E',
					type: 'dvd'
				},
				
				{
					title: 'CSS Dance Party Vol. 5',
					author: 'Matthieu LACLAU',
					reference: 'B01GO3JWPK',
					type: 'cd'
				},
				
				{
					title: 'Sopra, si près',
					author: 'Baptiste TRAIN',
					reference: '978-3155058290',
					type: 'book'
				},
				
				{
					title: 'La mélancolie du Javascript',
					author: 'Fanny COUTURIER',
					reference: '978-5135666210',
					type: 'book'
				},
				
				{
					title: 'La Bible de Jquery',
					author: 'Matthieu LACLAU',
					reference: '978-5554867824',
					type: 'book'
				},
				
				{
					title: 'Sport et Informatique: Comment se muscler le cerveau',
					author: 'Nadir ALLAM',
					reference: 'B01L3XTC9E',
					type: 'dvd'
				}
			];*/
		
		mbshCtrl.isNavCollapsed = true;
		mbshCtrl.isCollapsed = false;
		mbshCtrl.isCollapsedHorizontal = false;
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
		var url = 'http://192.168.10.34:8090/resource/adherent.accession?id=';
		
		return {
			getSheet : function(id) {
				console.log("ID: " + id);
				console.log("URL+ID: " + url + id);
				return $http.get(url + id).then(function(response) {
					console.log("Data: " + response.data);
					return response.data;
				});
			}
		}
	}).factory('MediaListService', function($http) {
		var urlMedia = 'http://192.168.10.34:8090/resource/media.recherche';
		
		return {
			getList : function(title,author,type) {
				var request = "?titre=" + title + "&auteur=" + author + "&type=";
				
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
			templateUrl: '/member/memberSheet.html',
			controller: 'MemberSheetController',
			controllerAs: 'mbshCtrl'
		});
	})