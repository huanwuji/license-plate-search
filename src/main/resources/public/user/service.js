define(function () {
    return {
        'userService': ['$resource', function ($resource) {
            return $resource('/users/:id', {userId: '@id'});
        }]
    }
});