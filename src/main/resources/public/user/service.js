define(function () {
    return {
        'userService': ['$resource', function ($resource) {
            return $resource('rest/users/:id', {userId: '@id'});
        }]
    }
});