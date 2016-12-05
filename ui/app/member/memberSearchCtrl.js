angular.module('MemberSearch', [])
	.controller('MemberSearchCtrl', ['MemberSearchService', 'Memorisation', '$location', function(MemberSearchService, Memorisation, $location) {
		var obj = this;
		obj.adherents;
		
		obj.totalItems;
		obj.itemsPerPage = 2;
		obj.maxSize = 5;
		
		obj.currentPage = 1;
		
		obj.setPage = function (newPage) {
			obj.currentPage = newPage;
		};

		obj.element = Memorisation.memberSearchCtrl;
		
		obj.search = function() {

			MemberSearchService.getMembers(obj.element).then(function(response) {
				obj.adherents = response;
				obj.totalItems = response.length;
			}, function(reason) {
				console.log('Les adherent n\'ont pas pas être récupéré');
			})
		}
		obj.addNewMember = function() {
			Memorisation.memberSearchCtrl = {'lastname' : obj.element.lastname, 'firstname' : obj.element.firstname}
			$location.path('/member/new');
		}
		obj.callMemberSheet = function(id){
			$location.path('/member/'+id);
		}
  
	}])
	.filter('paginationMembersFilter', function() {
		return function(input, currentPage, itemsPerPage) {
			var result = [];
			
			if (input != undefined && input != null && input != '') {
				
				var start = (currentPage - 1) * itemsPerPage;
				var end = start + itemsPerPage;

				for (var i = start;i < end;i++) {
					result.push(input[i]);
				}
			}
			
			return result;
		}
	})
	.value('Memorisation', {
		'memberSearchCtrl' : {}
	})
	.service('MemberSearchService', ['$http', function($http) {
		this.getMembers = function(element) {
			var http;
			http = $http;
			
			var url = 'http://192.168.1.93:8090/resource/adherent.recherche?';
			
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
		$routeProvider.when('/search_member', {
			templateUrl : '/member/search_member.html',
			controller : 'MemberSearchCtrl',
			controllerAs : 'memberSearchCtrl'
		})
	});