angular.module('blogApp').controller('PageNewsController', ['$scope', '$http', '$window', '$routeParams', function ($scope, $http, $window, $routeParams) {
	$scope.page = 0;
	$scope.pageSize = 5;
	$scope.news = [];
	
	if ($routeParams.pageId != null) {
		$scope.page = $routeParams.pageId;
	}
	
	$scope.loadPage = function () {
		$http.get('services/news/findByPage/'+$scope.page+":"+$scope.pageSize).success(function(data) {
			$scope.news = data;
		});
	};
	
	$scope.next = function() {
		$scope.page = parseInt($scope.page) +1;
		$window.location.href =  '#/page/'+$scope.page;
	}
	
	$scope.previous = function() {
		$scope.page = parseInt($scope.page) -1;
		$window.location.href =  '#/page/'+$scope.page;
	}
	
	$scope.loadPage();
	
	
}]);