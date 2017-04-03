/*
 * Application object for AboutBio
 */
'use strict';

var aboutBioApp = angular.module('aboutBioApp', [ ]);

aboutBioApp.controller('viewBio', ['$scope', function($scope) {
    $scope.bioInfo = {
        name: "David Thompson",
        email: "david@travelmagic.aa",
        emailLink: "mailto:david@travelmagic.aa",
        phone: "(501) 555-1234",
        phoneLink: "tel:5015551234"
    }
}]);
