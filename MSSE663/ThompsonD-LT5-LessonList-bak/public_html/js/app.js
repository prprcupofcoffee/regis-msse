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

lessonsListApp.controller('viewLessons', ['$scope', '$location', function($scope, $location) {
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
    
    $scope.lessons = [{
        id: 1,
        title: 'jQuery - Getting Started',
        url: 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475980/View'
    }, {
        id: 2,
        title: 'jQuery - AJAX',
        url: 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475981/View'
    }, {
        id: 3,
        title: 'Angular JS - Introduction',
        url: 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475982/View'
    }, {
        id: 4,
        title: 'Angular JS - Views and Templates',
        url: 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475983/View'
    }, {
        id: 5,
        title: 'Angular JS - Routing, Events, REST, and HTTP',
        url: 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475984/View'
    }, {
        id: 6,
        title: 'Angular JS - Content Sliders',
        url: 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475985/View'
    }, {
        id: 7,
        title: 'Google Maps API',
        url: 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475986/View'
    }, {
        id: 8,
        title: 'Google Places API',
        url: 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475987/View'
    }]    
}]);

lessonsListApp.controller('viewLessonDetails', ['$scope', '$routeParams', function($scope, $routeParams) {
    var lessonID = $routeParams.lessonID;
    $scope.lessonID = lessonID;
}]);
