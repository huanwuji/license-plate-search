define(function () {
    return {
        'userCreateCtrl': ['$scope', 'userService', '$routeParams', '$location', function ($scope, userService, $routeParams, $location) {
            $scope.user = {role: 'guest'};
            $scope.save = function () {
                userService.save($scope.user, function () {
                    $location.path("/user");
                });
            }
        }],
        'userEditCtrl': ['$scope', 'userService', '$routeParams', '$location', function ($scope, userService, $routeParams, $location) {
            var id = $routeParams.id;
            $scope.user = userService.get({id: id});
            $scope.save = function () {
                userService.save($scope.user, function () {
                    $location.path("/user");
                });
            }
        }],
        'userListCtrl': ['$scope', '$location', 'userService', function ($scope, $location, userService) {
            $scope.currentPage = 1;
            $scope.maxSize = 10;
            $scope.itemsPerPage = 20;
            getData();
            $scope.pageChanged = function () {
                getData();
            };
            var deleteId;
            $scope.ensureDel = function (id) {
                $scope.delAlert = true;
                deleteId = id;
            };
            $scope.delete = function () {
                userService.delete({id: deleteId}, function () {
                    $scope.delAlert = false;
                    deleteId = null;
                    getData();
                });
            }
            $scope.search = function () {
                getData({'s-username-like': $scope.searchText})
            }
            function getData(params) {
                userService.get(angular.extend({
                    page: $scope.currentPage - 1,
                    size: $scope.itemsPerPage,
                    sort: 'username'
                }, params), function (page) {
                    $scope.totalItems = page.totalElements;
                    $scope.numPages = page.totalPages;
                    $scope.users = page.content;
                });
            }
        }]
    }
});