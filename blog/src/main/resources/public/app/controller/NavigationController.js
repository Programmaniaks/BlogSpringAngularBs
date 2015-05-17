angular.module('blogApp').controller('NavigationController', ['$scope', '$http', '$log', 'categoryService', function ($scope, $http, $log, categoryService)  {
	$scope.componentPath = 'composite/navbar.html';
    $scope.categories = [];
    
    categoryService.loadAllCategories()
    	.success(function(data) {
    		$scope.categories = data;
	     }).error(function(data) {
	    	 $log.error('Error while calling CatgeoryService');
	     });
    
}]);