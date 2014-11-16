define(function () {
    return { 'CustomerService': ['$resource', function ($resource) {
        return $resource('/customer/:id', {userId: '@id'});
    }]}
});
