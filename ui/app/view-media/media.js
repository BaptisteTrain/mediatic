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
				var media = response.data[i];
				// If there's no borrower on the media, the line stays empty, idem for return date
				var aBorrower = '';
				var aPlannedReturnDate = '';
				var listLoan = media.loanList;
				if (listLoan != undefined) {
					// Seek the borrower
					var i = 0;
					while (i < media.loanList.length && media.loanList[i].returnDate != undefined) {
						i++;
					}
					if (i < media.loanList.length) {
						aBorrower = media.loanList[0].member.person.firstname + ' ' + media.loanList[0].member.person.lastname;
						aPlannedReturnDate = media.loanList[0].plannedReturnDate;
					}
				}
				// Push in the mediaList
				self.mediasList.push({id: media.id, 
									  title: media.title, 
									  author: media.author,
									  type: media.type,
									  borrower: aBorrower,
									  returnDate: aPlannedReturnDate
								  	});
			}
		})
	};
	
	
}]);
	
