angular.module('MemberSearch', [])
	.controller('MemberSearchCtrl', ['MemberSearchService', function(MemberSearchService, $location) {
		var obj = this;
		obj.adherents;
		obj.totalAdherents;
		
		obj.currentPage = 1;
		obj.maxSize = 5;
		
		obj.setPage = function (newPage) {
			obj.currentPage = newPage;
		};
		
		obj.search = function() {
			MemberSearchService.getMembers(obj.element).then(function(response) {
				obj.adherents = response;
				obj.totalAdherents = response.length;
			}, function(reason) {
				console.log('Les adherent n\'ont pas pas être récupéré');
			})
		}
		obj.callMemberSheet = function(id){
			$location.path('/member/'+id);
		}
  
	}])
	.service('MemberSearchService', ['$http', function($http) {
		this.getMembers = function(element) {
			var http;
			http = $http;
			
			var url = 'http://192.168.1.93:8090/resource/adherent.recherche?';
			
			if ('id' in element && element.id != undefined && element.id != null && element.id != '') {
				url += "id=" + element.id + "&";
			}
			if ('lastname' in element && element.lastname != undefined && element.lastname != null && element.lastname != '') {
				url += "nom=" + element.lastname + "&";
			}
			if ('firstname' in element && element.firstname != undefined && element.firstname != null && element.firstname != '') {
				url += "prenom=" + element.firstname;
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