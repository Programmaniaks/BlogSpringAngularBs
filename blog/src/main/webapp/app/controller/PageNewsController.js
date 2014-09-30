angular.module('blogApp').controller('PageNewsController', ['$scope', '$http', '$routeParams', function ($scope, $http, $routeParams) {
	$scope.page = 0;
	if ($routeParams.pageId != null) {
		$scope.page = $routeParams.pageId;
	}
	$scope.pageSize = 5;
	$scope.news = [];
	
	$scope.loadPage = function () {
		$http.get('services/news/findByPage/'+$scope.page+":"+$scope.pageSize).success(function(data) {
			$scope.news = data;
		});
	};
	
	$scope.next = function() {
		$scope.page = $scope.page +1;
		$scope.loadPage();
	}
	
	$scope.previous = function() {
		$scope.page = $scope.page -1;
		$scope.loadPage();
	}
	
	$scope.loadPage();
}]);