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
		this.mediasList = [];
		var url = 'http://localhost:8080/mediatic/media/all';
		$http.get(url).then(function(response) {
			for (var i in response.data) {
				var media = response.data[i];
				// If there's no borrower on the media, the line stays empty, idem for return date
				var aBorrower = '';
				var aPlannedReturnDate = '';
				var listLoan = media.loanList;
				if (listLoan != undefined) {
					// Seek the borrower
					var j = 0;
					while (j < media.loanList.length && media.loanList[j].returnDate != undefined) {
						j++;
					}
					if (j < media.loanList.length) {
						aBorrower = media.loanList[j].member.firstname + ' ' + media.loanList[j].member.lastname;
						aPlannedReturnDate = media.loanList[j].plannedReturnDate;
					}
				}
				// Change the name of the type (yes it's not pretty)
				var typeM = media.type;
				if (typeM == 'BOOK') {
					typeM = 'Livre';
				}
				// Push in the mediaList
				self.mediasList.push({id: media.id, 
									  title: media.title, 
									  author: media.author,
									  type: typeM,
									  borrower: aBorrower,
									  returnDate: aPlannedReturnDate
								  	});
			}
		})
	};
	
	
}]);
	
