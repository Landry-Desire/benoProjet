'use strict';

angular.module('News')
  .controller('NewsCtrl', ['$scope','$http' ,function ($scope,$http) {
    $scope.announcements = []
    
    $http.get('/ecommerce_api/announcements/all')
        .success(function(response){
            console.log('success ',response);
            $scope.announcements = response;
        })
        .error(function(response){
            console.log('failed ',response);
            $scope.announcements = response;
        });
    console.log('init News service');
  }]);