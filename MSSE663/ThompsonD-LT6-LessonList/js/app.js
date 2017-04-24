/* 
    Created on : Apr 9, 2017, 10:54:58 AM
    Author     : david
*/
'use strict';

var lessonsListApp = angular.module('lessonsListApp', ['ngRoute']);

lessonsListApp.config(['$locationProvider', '$routeProvider',
    function ($locationProvider, $routeProvider) {
        $locationProvider.html5mode = true;

        $routeProvider.
                when('/lessons', {
                    templateUrl: 'partials/lessons-list.html',
                    controller: 'viewLessons'
                }).
                when('/lessons/:lessonID', {
                    templateUrl: 'partials/lesson-details.html',
                    controller: 'viewLessonDetails'
                }).
                otherwise({
                    redirectTo: '/lessons'
                });
    }]);

lessonsListApp.controller('viewLessons', ['$scope', '$location', '$http', function($scope, $location, $http) {
    $scope.filterHistory = [];
    $scope.resetFilter = function() {
        $scope.filterHistory.push($scope.filterText);
        $scope.filterText = "";
    };
    
    $scope.setFilter = function(newFilterText) {
        $scope.filterText = newFilterText;
    };
    
    $scope.showLesson = function(lessonID) {
        $location.path("/lessons/" + lessonID);
    };
    
    $scope.resetFilter();
    $scope.filterHistory = [];
    
    $http.get('/services/lessonsService.php')
            .then(function(response) {
                $scope.lessons = response.data;
                $('.flexslider').flexslider();
            },
            function errorCallback(response) {
                $scope.error = response.data;
            });
}]);

lessonsListApp.controller('viewLessonDetails', ['$scope', '$routeParams', '$http', function($scope, $routeParams, $http) {
    var lessonID = $routeParams.lessonID;
    
    $http.get('/services/lessonsService.php', { params: { "id" : lessonID }})
            .then(function(response) {
                $scope.lesson = response.data;
            },
            function errorCallback(response) {
                $scope.error = response.data;
            });
}]);
