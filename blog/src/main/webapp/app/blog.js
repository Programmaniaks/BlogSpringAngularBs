'use strict';

angular.module('blogApp', [ 'ngRoute']);


angular.module('blogApp').config([ '$routeProvider', function($routeProvider) {
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
