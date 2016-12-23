angular.module('SearchMember', [])
	.controller('SearchMemberCtrl', ['SearchMemberService', 'Memorisation', '$location', 'AuthenticationService', '$rootScope', 
							function(SearchMemberService, Memorisation, $location, AuthenticationService, $rootScope) {
		var obj = this;
		
		// Check if authenticated
		/*if (! AuthenticationService.isConnected()) {
			// Redirection toward login
			$location.url('/login');
		}*/

		// Page's title
		$rootScope.titre = 'Adherents';
		
		// Menu active
		$rootScope.mediaActive = '';
		$rootScope.memberActive = 'active';
		
		// Has the right to create a new member
		obj.displayButtonAdd = true; // AuthenticationService.hasRightMemberCreation();
		
		
		obj.adherents;
		
		obj.itemsPerPage = 10;

		obj.element = Memorisation.searchMemberCtrl;
		
		obj.search = function() {

			SearchMemberService.getMembers(obj.element).then(function(response) {
				obj.adherents = response;
			}, function(reason) {
				console.log('Les adherents n\'ont pas pu être récupérés');
			})
		}
		obj.addNewMember = function() {
			Memorisation.memberSearchCtrl = {'lastname' : obj.element.lastname, 'firstname' : obj.element.firstname}
			$location.path('/createMember');
		}
		obj.callMemberSheet = function(id){
			$location.path('/member/'+id);
		}
  
	}])
	
	.value('Memorisation', {
		'searchMemberCtrl' : {}
	})
	
	.service('SearchMemberService', ['$http', 'IpService', function($http, IpService) {
		this.getMembers = function(element) {
			var http;
			http = $http;
			
			var url = 'http://'+IpService+':8090/resource/adherent.recherche?';
			
			if (element != undefined) {
				if ('id' in element && element.id != undefined && element.id != null && element.id != '') {
					url += "id=" + element.id + "&";
				}
				if ('lastname' in element && element.lastname != undefined && element.lastname != null && element.lastname != '') {
					url += "nom=" + element.lastname + "&";
				}
				if ('firstname' in element && element.firstname != undefined && element.firstname != null && element.firstname != '') {
					url += "prenom=" + element.firstname;
				}
			}
			
			return http.get(url).then(function(response) {
				return response.data;
			});
		}
	}])
	
	.config(function($routeProvider) {
		$routeProvider.when('/searchMember', {
			templateUrl : '/view-member/searchMember.html',
			controller : 'SearchMemberCtrl',
			controllerAs : 'searchMemberCtrl'
		})
	});