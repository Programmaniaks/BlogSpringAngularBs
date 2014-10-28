angular.module('blogApp').controller('GlobalController', ['$scope', '$log', function ($scope, $log)  {
    $scope.categories = [];
    
    $scope.isAdmin = true;
    $scope.leftIndex = true;
}]);