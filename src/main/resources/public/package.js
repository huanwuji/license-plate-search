var packages = (function () {
    var webjars = {
        versions: {
            'bootstrap': '3.2.0',
            'angularjs': '1.3.0-rc.4',
            'angular-ui-bootstrap': '0.11.2',
            'jquery': '1.11.1',
            'require-css': '0.1.4-1'
        },
        path: function (webJarId, path) {
            return ['/webjars/' + webJarId + '/' + webjars.versions[webJarId] + '/' + path];
        },
        map: {
            '*': {
                'css': 'require-css/css'
            }
        }
    };
    var packages = {
        paths: {
            'bootstrap': webjars.path('bootstrap', 'js/bootstrap'),
            'bootstrap-css': webjars.path('bootstrap', 'css/bootstrap'),
            'jquery': webjars.path('jquery', 'jquery'),
            'ui-bootstrap': webjars.path('angular-ui-bootstrap', 'ui-bootstrap'),
            'ui-bootstrap-tpls': webjars.path('angular-ui-bootstrap', 'ui-bootstrap-tpls'),
            'angular': webjars.path('angularjs', 'angular'),
            'css': webjars.path('require-css', 'css')
        },
        shim: {
            'jquery': {'exports': '$'},
            'bootstrap': ['jquery'],
            'ui-bootstrap': ['angular'],
            'ui-bootstrap-tpls': ['angular'],
            'angular': {'exports': 'angular'}
        }
    };
    var webjarsAngularjsChildren = [
        'angular-animate',
        'angular-cookies',
        'angular-loader',
        'angular-mocks',
        'angular-resource',
        'angular-route',
        'angular-sanitize',
        'angular-scenario',
        'angular-touch'
    ];
    var webjarsAngularjsLocales = [
        'angular-locale_zh-cn'
    ];
    webjarsAngularjsChildren.forEach(function (child) {
        packages.paths[child] = webjars.path('angularjs', child);
        //packages.shim[child] = ['angular', 'webjars!angular.js'];
    });
    webjarsAngularjsLocales.forEach(function (child) {
        packages.paths[child] = webjars.path('angularjs', 'i18n/' + child);
        //packages.shim[child] = ['angular', 'webjars!angular.js'];
    });
    return packages;
})();