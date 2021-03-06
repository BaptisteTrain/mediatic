angular.module('Pagination', [])
	.controller('PaginationCtrl', function() {
		var obj = this;
		
		obj.maxSize = 5;
		
		obj.currentPage = 1;
		
		obj.setPage = function (newPage) {
			obj.currentPage = newPage;
		};
	})
	.filter('paginationFilter', function() {
		return function(input, currentPage, itemsPerPage) {
			var result = [];

			if (input != undefined && input != null && input != '') {
				
				var start = (currentPage - 1) * itemsPerPage;
				var end;

				if ((input.length - start) < itemsPerPage) {
					end = start + (input.length - start);
				} else {
					end = start + itemsPerPage;
				}
				for (var i = start;i < end;i++) {
					result.push(input[i]);
				}
			}

			return result;
		}
	});