'use strict';
angular.module('Inscription')
  .controller('InscriptionCtrl', ['$scope', '$http','$window','$location'
    ,function ($scope, $http, $window,$location) {
	 
	  $scope.inscrit = function(){
	      console.log('inscription');
	      var cb = function(r){
	          alert(r);
	          $window.location.path('/login');
	      }
	  	if($scope.mdp != $scope.cmdp){
	          cb("error pwd");
	          return;
	      }
	
       $http.post('/benoProjet/users/addUser',{
            "nom": $scope.nom,
            "prenom": $scope.prenom,
            "email":$scope.email,
            "hierachie":$scope.hierachie,
            "mdp":$scope.mdp,
            "valide":$scope.valide
          }).success(function(response){
            console.log('success ',response);
          })
            .error(function(response){
              console.log('failed ',response);
            });
	  	}
  }]);

//angular.module('Inscription')
//.controller('InscriptionCtrl', ['$scope','$http', '$location' ,function ($scope,$http,$location) {
//  $http.post('/ecommerce_api/users/addUser',{
//          "email": $scope.email,
//          "mdp": $scope.pwd
//      }).success(function(response){
//          console.log('success ',response);
//          cb("Inscription Effectué");
//      }).error(function(response){
//          console.log('failed ',response);
//          cb("Inscription Echoué");
//      });
//  }
//}]);