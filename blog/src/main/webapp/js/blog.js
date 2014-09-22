var blogApp = angular.module('blog', [ 'ngRoute' ]);

blogApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/index', {
		templateUrl : 'partials/newsPaged.html',
		controller: 'PageNewsController'
	}).when('/page/:pageId', {
		templateUrl : 'partials/newsPaged.html',
		controller: 'PageNewsController'
	}).when('/news/:newsId', {
		templateUrl : 'partials/news.html',
		controller: 'NewsController'
	}).otherwise({
		redirectTo : '/index'
	});
} ]);

blogApp.controller('CategoryController', function ($scope, $http)  {
    $scope.categories = [];
    
    $http.get('services/category/all').success(function(data) {
    	$scope.categories = data;
     });
});


blogApp.controller('NewsController', ['$scope', '$http', '$routeParams', function ($scope, $http, $routeParams) {
	$scope.newsId = $routeParams.newsId;
	
	$http.get('services/news/'+$scope.newsId).success(function(data) {
		$scope.news = data;
	});
}]);

blogApp.controller('PageNewsController', ['$scope', '$http', '$routeParams', function ($scope, $http, $routeParams) {
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


blogApp.controller('UserController', function ($scope, $http)  {
    $scope.user;
    $scope.login;
    $scope.password;
    
    
    
    $scope.connect = function () {
    	$http.post('services/user/connect', {"username":$scope.login, "password":$scope.password}).success(function(data) {
        	$scope.user = data;
         });
    }
});
