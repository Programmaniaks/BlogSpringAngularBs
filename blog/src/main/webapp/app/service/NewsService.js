angular.module('blogApp').factory('newsService', ['$http', function($http) {
	
	var loadNewsPaged = function(page, size) { 
		return $http.get('services/news/findByPage?page='+page+'&pageSize='+size); 
	};
	
	var searchNewsPaged = function(tag, page, size) { 
		return $http.get('services/news/search?searchTag='+tag+'&page='+page+'&pageSize='+size); 
	};
	
	return {
		loadNewsPaged : function(page, size) { 
			return loadNewsPaged(page, size);
		},
		searchNewsPaged : function(tag, page, size) {
			return searchNewsPaged(tag, page, size);
		}
		
	}
}]);