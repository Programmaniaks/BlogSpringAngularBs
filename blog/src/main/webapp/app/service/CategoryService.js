angular.module('blogApp').factory('categoryService', ['$http', function($http) {
	
	var loadAllCategoriesRequest = function() { 
		return $http.get('services/category/all'); 
	};
	
	return {
		loadAllCategories : function() { 
			return loadAllCategoriesRequest();
		}
	}
}]);