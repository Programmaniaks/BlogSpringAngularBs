angular.module('blogApp').controller('PageNewsController', ['$scope', '$window', '$routeParams', 'newsService', function ($scope, $window, $routeParams, newsService) {
	$scope.page = 0;
	$scope.pageSize = 5;
	$scope.news = [];
	$scope.tagSearch = null;
	
	if ($routeParams.pageId != null) {
		$scope.page = $routeParams.pageId;
	}
	
	if ($routeParams.tag != null) {
		$scope.tagSearch = $routeParams.tag;
	} else {
		$scope.tagSearch = null;
	}
	
	$scope.loadPage = function () {
		if ($scope.tagSearch == null) {
			newsService.loadNewsPaged($scope.page, $scope.pageSize).success(function(data) {
				$scope.news = data;
			});
		} else {
			newsService.searchNewsPaged($scope.tagSearch, $scope.page, $scope.pageSize).success(function(data) {
				$scope.news = data;
			});
		}
	};
	
	$scope.next = function() {
		$scope.page = parseInt($scope.page) +1;
		if ($scope.tagSearch == null) {
			$window.location.href =  '#/page/'+$scope.page;
		} else {
			$window.location.href =  '#/page/'+$scope.tagSearch+'/'+$scope.page;
		}
	}
	
	$scope.previous = function() {
		$scope.page = parseInt($scope.page) -1;
		if ($scope.tagSearch == null) {
			$window.location.href =  '#/page/'+$scope.page;
		} else {
			$window.location.href =  '#/page/'+$scope.tagSearch+'/'+$scope.page;
		}
	}
	
	$scope.loadPage();
	
	
}]);