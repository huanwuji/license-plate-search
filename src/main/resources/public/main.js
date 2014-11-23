requirejs.config(packages);
require(['angular', 'controller', 'user/service', 'user/controller', 'css!bootstrap-css'],
    function (angular, controller, userServices, userCtrls) {
        require(['angular-resource', 'angular-route', 'angular-animate', 'ui-bootstrap-tpls'], function () {
            var module = angular.module('indexModule', ['ngRoute', 'ngResource', 'ngAnimate', 'ui.bootstrap']);
            module.config(['$routeProvider', '$locationProvider',
                function ($routeProvider, $locationProvider) {
                    $routeProvider
                        .when('/user/create', {
                            templateUrl: '/user/edit.html',
                            controller: 'userCreateCtrl'
                        })
                        .when('/user/:id', {
                            templateUrl: '/user/edit.html',
                            controller: 'userEditCtrl'
                        })
                        .when('/user', {
                            templateUrl: '/user/list.html',
                            controller: 'userListCtrl'
                        });
                    $locationProvider.html5Mode(true).hashPrefix('!');
                }]);
            angular.forEach(angular.extend({}, userServices), function (value, key) {
                module.factory(key, value);
            });
            angular.forEach(angular.extend({}, controller, userCtrls), function (value, key) {
                module.controller(key, value);
            });
            angular.bootstrap(document, ['indexModule'])
        });
    });