'use strict';

angular.module('Media', [])

.config(function($routeProvider) {
	$routeProvider.when('/media', {
	    templateUrl: './view-media/media.html',
	    controller: 'MediaCtrl',
	    controllerAs: 'media'
	});
})

.controller('MediaCtrl', ['$location', '$http', '$sce', '$rootScope',
						 function($location, $http, $sce, $rootScope) {
	var self = this;

	// Page's title
	$rootScope.titre = "Media";
	
	// Redirection from button add a media to page newMedia
	this.goNewMedia = function() {
		$location.url('/newMedia');
	}
	
	// Loading liste de medias
	this.mediasList = [];
	this.searchMedias = function() {
		var url = 'http://192.168.10.34:8090/resource/media.recherche';
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
	
