define(function () {
    return {
        'customerCreateCtrl': ['$scope', 'customerService', '$routeParams', '$location', function ($scope, customerService, $routeParams, $location) {
            $scope.save = function () {
                customerService.save($scope.customer, function () {
                    $location.path("/customer");
                });
            }
        }],
        'customerEditCtrl': ['$scope', 'customerService', '$routeParams', '$location', function ($scope, customerService, $routeParams, $location) {
            var id = $routeParams.id;
            $scope.customer = customerService.get({id: id});
            $scope.save = function () {
                customerService.save($scope.customer, function () {
                    $location.path("/customer");
                });
            }
        }],
        'customerListCtrl': ['$scope', '$location', 'customerService', function ($scope, $location, customerService) {
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
            function getData() {
                customerService.get({
                    page: $scope.currentPage - 1,
                    size: $scope.itemsPerPage,
                    sort: 'num'
                }, function (page) {
                    $scope.totalItems = page.totalElements;
                    $scope.numPages = page.totalPages;
                    $scope.customers = page.content;
                });
            }
        }]
    }
});