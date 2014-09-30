angular.module('blogApp').controller('CategoryController', ['$scope', '$http', '$log', 'categoryService', function ($scope, $http, $log, categoryService)  {
    $scope.categories = [];
    
    categoryService.loadAllCategories()
    	.success(function(data) {
    		$scope.categories = data;
	     }).error(function(data) {
	    	 $log.error('Error while calling CatgeoryService');
	     });
}]);