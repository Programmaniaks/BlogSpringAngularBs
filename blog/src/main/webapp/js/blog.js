var blogApp = angular.module('blog', []);

blogApp.controller('CategoryController', function ($scope, $http)  {
        $scope.categories = [];
        
        $http.get('services/category/all').success(function(data) {
        	$scope.categories = data;
         });
});

