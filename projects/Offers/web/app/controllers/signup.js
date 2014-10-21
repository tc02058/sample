/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var signupApp = angular.module('myApp.signup', ['ngRoute']);

signupApp.config(function($routeProvider) {
    $routeProvider.
               when('/signup', {
                templateUrl: 'app/partials/signup.html',
                controller: 'signupController'
            }).
            otherwise({
                redirectTo: '/'
            });
});

//---------------------------------------------------------------------------- 

signupApp.controller('signupController', function($scope, $http,$location)
{
    
   //  console.log("dharma in singupController");
     
    $scope.getDatetime = new Date;
    $scope.newRetailer = {firstName: "", lastName: "",email: "", mobile: "",password:"", 
                            managerId:"0",createdUser:"admin",updatedUser:"admin",
                        createdDate:$scope.getDatetime,updatedDate:$scope.getDatetime};
                    
                   
                    
    $scope.create = function()
    {
        console.log($scope.newRetailer);
        var responsePromise = $http.post("webresources/retailermaster",$scope.newRetailer);
        responsePromise.success(function(status, headers, config) {
            alert("Successfully Created");
            $location.path('/login');
        });
          responsePromise.error(function(data, status, headers, config) {
              alert("AJAX failed!");
          }); 
    }; 

});