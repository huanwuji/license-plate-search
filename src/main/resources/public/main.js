requirejs.config(packages);
define(['angular', 'controller', 'user/controller', 'angular-resource', 'angular-route', 'css!bootstrap-css'],
    function (angular, controller, userCtrls) {
        var module = angular.module('index', ['ngRoute', 'ngAnimate']);
        module.config(['$routeProvider', '$locationProvider',
            function ($routeProvider, $locationProvider) {
                $routeProvider
                    .when('/user/:id', {
                        templateUrl: '/user/edit.html',
                        controller: 'userEditCtrl'
                    })
                    .when('/user', {
                        templateUrl: 'list.html',
                        controller: 'userListCtrl'
                    });
                $locationProvider.html5Mode(true);
            }]);
        angular.forEach(angular.extend({}, controller, userCtrls), function (value, key) {
            module.controller(key, value);
        });
    });