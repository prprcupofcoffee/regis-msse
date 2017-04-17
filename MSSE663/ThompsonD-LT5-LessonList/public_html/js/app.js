/* 
    Created on : Apr 9, 2017, 10:54:58 AM
    Author     : david
*/
'use strict';

var lessonsListApp = angular.module('lessonsListApp', []);

lessonsListApp.controller('viewLessons', ['$scope', function($scope) {
    $scope.filterHistory = [];
    $scope.resetFilter = function() {
        $scope.filterHistory.push($scope.filterText);
        $scope.filterText = "";
    };
    
    $scope.setFilter = function(newFilterText) {
        $scope.filterText = newFilterText;
    }
    
    $scope.resetFilter();
    $scope.filterHistory = [];
    
    $scope.lessons = [{
        order: 1,
        title: 'jQuery - Getting Started',
        url: 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475980/View'
    }, {
        order: 2,
        title: 'jQuery - AJAX',
        url: 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475981/View'
    }, {
        order: 3,
        title: 'Angular JS - Introduction',
        url: 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475982/View'
    }, {
        order: 4,
        title: 'Angular JS - Views and Templates',
        url: 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475983/View'
    }, {
        order: 5,
        title: 'Angular JS - Routing, Events, REST, and HTTP',
        url: 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475984/View'
    }, {
        order: 6,
        title: 'Angular JS - Content Sliders',
        url: 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475985/View'
    }, {
        order: 7,
        title: 'Google Maps API',
        url: 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475986/View'
    }, {
        order: 8,
        title: 'Google Places API',
        url: 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475987/View'
    }]    
}]);