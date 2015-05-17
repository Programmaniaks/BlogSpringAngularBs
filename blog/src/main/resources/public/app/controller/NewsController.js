angular.module('blogApp').controller('NewsController', ['$scope', '$http', '$routeParams', function ($scope, $http, $routeParams) {
	$scope.newsId = $routeParams.newsId;
	
	$http.get('services/news/'+$scope.newsId).success(function(data) {
		$scope.news = data;
	});
}]);