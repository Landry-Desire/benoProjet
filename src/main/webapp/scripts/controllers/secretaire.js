'use strict';

angular.module('Secretaire')
  .controller('SecretaireCtrl', ['$scope', '$http','$window','$cookies' 
    ,function ($scope, $http, $window ,$cookies) {
	  $scope.listeEtudiant ='';
	  if($cookies.globals === undefined){
	        $window.location.href="#/login";
	        return ;
	      }
	      $scope.id = $cookies.globals.currentUser.id;
	      console.log('scope.id',$scope.id);
      $scope.getA = function(){
	          $http.get('/benoProjet/users/AllUser')
	          .success(function(response){
	        	  $scope.listeEtudiant = response;
	        	  console.log('success 1',$scope.listeEtudiant);
	          }).error(function(response){
	            console.log('failed ',response);
	          })  
	        }
	      
      $scope.valider = function(){
        $http.post('/benoProjet/users/addUser',{
            "nom": $scope.nom,
            "prenom": $scope.prenom,
            "groupeId":$scope.groupeId,
            "email":$scope.email,
            "hierachie":$scope.hierachie,
            "mdp":$scope.mdp
          }).success(function(response){
            console.log('success ',response);
          })
            .error(function(response){
              console.log('failed ',response);
            })
      }
      
      $scope.getA();
  }]);