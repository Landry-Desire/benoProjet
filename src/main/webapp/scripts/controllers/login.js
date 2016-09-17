'use strict';
/**
 * Login en fonction du user de la hierachie et du mot de passe 
 **/
angular.module('Login')
  .controller('LoginCtrl', ['$scope', '$window','$location', 'LoginService', function ($scope, $window,$location, LoginService) {
    $scope.email = "enzi@u-bordeaux.xxx";
    $scope.mdp = "dydybtlc";
    $scope.hierachie="Secretaire"
    $scope.signin  = function(){
        LoginService.login($scope.email,$scope.mdp,$scope.hierachie,function(response){
             if(response.success){
                  console.log("response.message" + response.message);
                }else{
                  console.log("response.message else " + response.message);
                  if($scope.hierachie == "Secretaire"){
                        $window.location.href="#/secretaire"
                    }else if($scope.hierachie=="Etudiant"){
                        $window.location.href="#/etudiant"
                    }else if($scope.hierachie=="Prof"){
                        $window.location.href="#/prof"
                    }
                }
        });
    }
  }])
.factory('LoginService',['$http' ,'$rootScope' ,'$cookies' ,'$cookieStore'
    ,function($http, $rootScope, $cookies, $cookieStore){
        var service = {};
        console.log("$cookies",$cookies);
        service.login = function(email, mdp, hierachie, cb){
            console.log("LoginService" + email , mdp , hierachie);  
            $http.post('/benoProjet/users/login',{
                "email": email,
                "mdp":mdp,
                "hierachie":hierachie
            }).success(function(response){
                console.log('success ',response);
		            if(response.error === "not found")
		                return alert('User Not in DB');
		                service.setSession(response)
		                cb("response.error -------->",response);
		            }).error(function(response){
		                console.log('failed ',response);
		                alert('User Not in DB');
		            });
            }

        service.setSession = function(user){
            $rootScope.globals = {
                currentUser: user
            };
            /*$cookies.put("globals $cookies",$rootScope.globals)
            $cookieStore.put("globals $cookieStore",$rootScope.globals)*/
            $cookies.globals = $rootScope.globals;              
//            $cookies.globals = $rootScope.globals;
            console.log("$cookies.globals.currentUser",$cookies.globals.currentUser);
        }
        
        service.destroySession = function(){
            $rootScope.globals= {}
            $cookieStore.remove('globals');
            $cookie.remove('globals');
        }
        console.log('Init Service Login');
        return service;

  }]);


