var kutijaApp = angular.module('kutijaApp.routes',['ngRoute']);

kutijaApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
	.when('/', {
		templateUrl: 'static/html/home.html'
	})
	.when('/fotke',{
		templateUrl: 'static/html/fotke.html',
		controller: 'FotkaController'
	})
	.when('/fotke/:id',{
		templateUrl: 'static/html/fotke.html',
		controller: 'FotkaController'
	})
		.when('/sveFotke',{
		templateUrl: 'static/html/sveFotke.html',
		controller: 'FotkaController'
	})
	.when('/dodajNovuFotku',{
		templateUrl: 'static/html/AddEditFotka.html',
		controller: 'FotkaController'
	})
		.when('/uploadtest',{
		templateUrl: 'static/html/upload.html',
		controller: 'FotkaController'
	})
    .otherwise({
        redirectTo: '/'
    });
}]);