requirejs.config(packages);
require(['angular', 'controller', 'user/service', 'user/controller', 'customer/service', 'customer/controller', 'css!bootstrap-css'],
    function (angular, controller, userServices, userCtrls, customerServices, customerCtrls) {
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
                        })
                        .when('/customer/create', {
                            templateUrl: '/customer/edit.html',
                            controller: 'customerCreateCtrl'
                        })
                        .when('/customer/:id', {
                            templateUrl: '/customer/edit.html',
                            controller: 'customerEditCtrl'
                        })
                        .when('/customer', {
                            templateUrl: '/customer/list.html',
                            controller: 'customerListCtrl'
                        });
                    $locationProvider.html5Mode(true).hashPrefix('!');
                }]);
            angular.forEach(angular.extend({}, userServices, customerServices), function (value, key) {
                module.factory(key, value);
            });
            angular.forEach(angular.extend({}, controller, userCtrls, customerCtrls), function (value, key) {
                module.controller(key, value);
            });
            angular.bootstrap(document, ['indexModule'])
        });
    });