'use strict';

angular.module('Media', [])

.config(function($routeProvider) {
	$routeProvider.when('/media', {
	    templateUrl: './view-media/media.html',
	    controller: 'MediaCtrl',
	    controllerAs: 'media'
	});
})

.controller('MediaCtrl', ['$location', '$http', '$rootScope', 'AuthenticationService', 'IpService',
						 function($location, $http, $rootScope, AuthenticationService, IpService) {
	var self = this;
	self.mediasList;
	
	self.itemsPerPage = 10;
	
	// Check if authenticated
	/*if (! AuthenticationService.isConnected()) {
		// Redirection toward login
		$location.url('/login');
	}*/

	// Page's title
	$rootScope.titre = 'Media';
	
	// Menu active
	$rootScope.mediaActive = 'active';
	$rootScope.memberActive = '';
	
	// Has the right to create a new media
	this.displayButtonAdd = true; //AuthenticationService.hasRightMediaCreation();
	
	// Redirection from button add a media to page newMedia
	this.goNewMedia = function() {
		$location.url('/newMedia');
	}
	// Redirection from detail icon to page 
	this.goMediaSheet = function(id) {
		$location.path('/mediaSheet/'+id);
	}
	
	// Loading list of medias
	this.mediasList = [];
	this.searchMedias = function() {
		var url = 'http://localhost:8080/mediatic/media/all';
		$http.get(url).then(function(response) {
			for (var i in response.data) {
				// If there's no borrower on the media, the line stays empty
				var aBorrower = '';
				if (response.data[i].emprunteur != undefined) {
					aBorrower = response.data[i].emprunteur.nom + ' ' + response.data[i].emprunteur.prenom;
				}
				// Push in the mediaList
				self.mediasList.push({id: response.data[i].id, 
									  title: response.data[i].titre, 
									  author: response.data[i].auteur,
									  type: response.data[i].type,
									  borrower: aBorrower,
									  returnDate: response.data[i].retour
								  	});
			}
		})
	};
	
	
}]);
	
