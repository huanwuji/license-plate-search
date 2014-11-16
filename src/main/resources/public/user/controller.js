define(function () {
    return {
        'userEditCtrl': function ($scope, userService, $routeParams, $location) {
            var id = $routeParams.id;
            if (id != null) {
                $scope.user = userService.get({id: id});
            }
            $scope.save = function () {
                userService.save($scope.user, function () {
                    $location.path("/user");
                });
            }
        },
        'userListCtrl': function ($scope, userService) {
            $scope.list = userService.get();
        }
    }
});