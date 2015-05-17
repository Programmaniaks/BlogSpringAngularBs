angular.module('blogApp').controller('UserController', function ($scope, $http)  {
    $scope.user;
    $scope.login;
    $scope.password;
    
    
    
    $scope.connect = function () {
    	$http.post('services/user/connect', {"username":$scope.login, "password":$scope.password}).success(function(data) {
        	$scope.user = data;
         });
    }
});