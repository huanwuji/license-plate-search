define(function () {
    return {
        'customerService': ['$resource', function ($resource) {
            return $resource('/customers/:id', {userId: '@id'});
        }]
    }
});
