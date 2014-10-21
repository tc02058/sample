/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var shopDetailsApp = angular.module('myApp.shopDetails', ['ngRoute']);

shopDetailsApp.config(function($routeProvider) {
    $routeProvider.
          
            when('/shopDetails/:shopId', {
                templateUrl: 'app/partials/shopDetails.html',
                controller: 'shopDetailsController'
            }).
             otherwise({
                redirectTo: '/'
            });
});



//----------------------------------------------------------------------------
shopDetailsApp.controller('shopDetailsController', function($scope, $http, $location, $routeParams, googleFactory, AuthService)
{


    $scope.discountDetails = {};
    $scope.shopValue = {};


    $scope.getDiscountDtls = function(urlVal)
    {

        var responsePromise = $http.get(urlVal);
        responsePromise.success(function(data, status, headers, config) {
            $scope.discountDetails.fromServer = data;

            /*  $scope.shopValue = data.filter(function(entry)
             {
             return entry.shopId.shopId == $routeParams.shopId;
             })[0]; */
        });
        responsePromise.error(function(data, status, headers, config) {
            alert("AJAX failed!");
        });
    };
    //----------------------------------------------------------------------------  

    $scope.getShopDetails = function(shopId)
    {
        if (AuthService.getUserStatus() === false)
        {
            $location.path('/login');
        }
        else
        {

            var responsePromise = $http.get("webresources/retailshopmaster/" + shopId.trim());
            responsePromise.success(function(data, status, headers, config) {
                $scope.shopValue = data;
                googleFactory.setShopDetails(data);
             //   console.log(data);
                $scope.getDiscountDtls("webresources/discountinfo/getShopDiscounts/" + shopId.trim());
            });
            responsePromise.error(function(data, status, headers, config) {
                alert("AJAX failed!");
            });

        }
    };
    //----------------------------------------------------------------------------   
    $scope.getShopDetails($routeParams.shopId);

});

