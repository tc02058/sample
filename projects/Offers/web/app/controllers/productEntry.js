/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var productEntryApp = angular.module('myApp.productEntry', ['ngRoute']);


productEntryApp.config(function($routeProvider) {
    $routeProvider.
            when('/productEntry', {
                templateUrl: 'app/partials/productEntry.html',
                controller: 'productEntryController'
            }).
            otherwise({
                redirectTo: '/'
            });
});

//----------------------------------------------------------------------------  
productEntryApp.controller('productEntryController', function($scope, $http,$location, AuthService)
{
    $scope.getDatetime = new Date;
    $scope.currentUser = AuthService.getUserId();
    $scope.categories = {};
    $scope.productData = {productName: "", categoryId: "",
        createdDate: $scope.getDatetime, updatedDate: $scope.getDatetime,
        createdUser: $scope.currentUser, updatedUser: $scope.currentUser};

    $scope.getCategories = function()
    {

        var responsePromise = $http.get("webresources/productcategorymaster");
        responsePromise.success(function(data, status, headers, config) {
            $scope.categories = data;
            $scope.productData.categoryId = 1;
              

        });
        responsePromise.error(function(data, status, headers, config) {
            alert("AJAX failed!");
        });
    };
    //----------------------------------------------------------------------------    
    $scope.createProduct = function()
    {
        console.log($scope.productData);

        var responsePromise = $http.post("webresources/productmaster/createProduct", $scope.productData);
        responsePromise.success(function(data, status, headers, config) {
            alert("Successfully created");
            $location.path('/discountEntry');
        });
        responsePromise.error(function(data, status, headers, config) {
            alert("failed in product creation");
        });
    };

    $scope.getCategories();

});
