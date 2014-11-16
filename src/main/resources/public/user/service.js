define(function () {
    return {
        'userService': ['$resource', function ($resource) {
            return $resource('/user/:id', {userId: '@id'});
        }]
    }
});