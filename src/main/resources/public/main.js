requirejs.config(packages);
require(['angular',
        'component',
        "user/component",
        "customer/component"],
    function (angular) {
        var components = angular.copy(arguments, []).slice(1);
        require(['angular-resource', 'angular-route', 'angular-animate', 'ui-bootstrap-tpls', 'css!bootstrap-css'], function () {
            var module = angular.module('indexModule', ['ngRoute', 'ngResource', 'ngAnimate', 'ui.bootstrap']);
            angular.forEach(components, function (component) {
                component(module);
            });
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
                        })
                        .otherwise({
                            redirectTo: '/'
                        });
                    $locationProvider.html5Mode(true).hashPrefix('!');
                }]);
            angular.bootstrap(document, ['indexModule'])
        });
    });