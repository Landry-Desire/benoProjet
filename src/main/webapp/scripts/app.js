'use strict';

angular.module('News',[]);
angular.module('Secretaire',[]);
angular.module('Login',['ngCookies']);
angular.module('Inscription',[]);

angular
  .module('benoApp', [
    'ngRoute',
    'ngCookies',
    'News',
    'Secretaire',
    'Login',
    'Inscription'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/news',{
        templateUrl: 'views/news.html',
        controller:  'NewsCtrl'
      })
      .when('/secretaire',{
        templateUrl: 'views/secretaire.html',
        controller:  'SecretaireCtrl'
      })
      .when('/login',{
        templateUrl: 'views/login.html',
        controller:  'LoginCtrl'
      })
	  .when('/inscription', {
		templateUrl : 'views/inscription.html',
		controller : 'InscriptionCtrl'
	  })
      .otherwise({templateUrl: '404.html'})
  })
  .run(['$rootScope', '$location', '$cookies', '$http','$window',
 	function ($rootScope, $location, $cookies, $window) {
        // keep user logged in after page refresh
        /*$rootScope.globals = $cookies.globals || {};*/
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
        	console.log('locationChanged',$location.path()!="/signup");
        	$window.location.href="#/secretaire"
        	if (($window.location.href != "/login" && $window.location.href != "/signup")) {
        		console.log('/login or /signup');
        		if($cookies.get("globals")==undefined)
        			$window.location.href('/login');
        	}
        });
    }]);