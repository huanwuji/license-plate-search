define(function () {
    return function (module) {
        module.factory('customerService', ['$resource', function ($resource) {
            return $resource('/customers/:id', {userId: '@id'});
        }]).factory('customerSearchService', ['$resource', function ($resource) {
            return $resource('/customers/search', {userId: '@id'});
        }]).controller(
            'customerCreateCtrl', ['$scope', 'customerService', '$routeParams', '$location', function ($scope, customerService, $routeParams, $location) {
                $scope.save = function () {
                    customerService.save($scope.customer, function () {
                        $location.path("/customer");
                    });
                }
            }]).controller('customerEditCtrl', ['$scope', 'customerService', '$routeParams', '$location', function ($scope, customerService, $routeParams, $location) {
                var id = $routeParams.id;
                $scope.customer = customerService.get({id: id});
                $scope.save = function () {
                    customerService.save($scope.customer, function () {
                        $location.path("/customer");
                    });
                }
            }]).controller('customerListCtrl', ['$scope', '$location', 'customerService', 'customerSearchService', function ($scope, $location, customerService, customerSearchService) {
                $scope.currentPage = 1;
                $scope.maxSize = 10;
                $scope.itemsPerPage = 20;
                getData();
                $scope.pageChanged = function () {
                    getData();
                };
                var deleteId;
                $scope.ensureDel = function (id) {
                    $scope.show = true;
                    deleteId = id;
                };
                $scope.delete = function () {
                    customerService.delete({id: deleteId}, function () {
                        $scope.show = false;
                        deleteId = null;
                        getData();
                    });
                };
                $scope.search = function () {
                    getData({'searchText': $scope.searchText})
                };
                function getData(params) {
                    customerSearchService.get(angular.extend({
                        page: $scope.currentPage - 1,
                        size: $scope.itemsPerPage,
                        sort: 'num'
                    }, params), function (page) {
                        $scope.totalItems = page.totalElements;
                        $scope.numPages = page.totalPages;
                        $scope.customers = page.content;
                    });
                }
            }])
    }
});