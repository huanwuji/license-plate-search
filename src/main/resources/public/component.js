define(function () {
    return function (module) {
        module.controller('indexCtrl', ['$scope', function ($scope) {
            $scope.data = 'just for test';
        }])
    }
});