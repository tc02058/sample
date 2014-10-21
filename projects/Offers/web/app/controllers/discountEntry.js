/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var discountEntryApp = angular.module('myApp.discountEntry', ['ngRoute']);

discountEntryApp.config(function($routeProvider) {
    $routeProvider.
            when('/discountEntry', {
                templateUrl: 'app/partials/discountEntry.html',
                controller: 'discountEntryController'
            }).
            otherwise({
                redirectTo: '/'
            });
});


//------------------------------------------------------------------------------------------------
discountEntryApp.controller('discountEntryController', function($scope, $http, $location, googleFactory, AuthService)
{

    $scope.getDatetime = new Date;
    $scope.searchData = {};
    $scope.currentUser = AuthService.getUserId();
    $scope.shopId = googleFactory.getShopDetails().shopId;
    
    $scope.offerData = {productId: "", shopId: "", categoryId: "", cityId: "", price: "", discPrice: "",
        discountPercentage: "", DiscountDescription: "", validStartDate: $scope.getDatetime,
        validEndDate: $scope.getDatetime,
        createdDate: $scope.getDatetime, updatedDate: $scope.getDatetime,
        createdUser: $scope.currentUser, updatedUser: $scope.currentUser};

    $scope.item = {productName: ""};
    $scope.getSearchLst = function(item)
    {
        //  alert(item);
        //console.log(item);
        if (item.productName.trim().length !== 0)
        {
            $scope.search("webresources/productmaster/getProductLst/" + item.productName.trim());
        }
    };

    /* $scope.calculatePercentage = function()
     {
     
     var diff = $scope.price -$scope.discPrice;
     $scope.discountPercentage = ($scope.price)/diff;
     console.log("dharma");
     console.log(diff);
     
     }; */
    //----------------------------------------------------------------------------   
    $scope.search = function(urlVal)
    {
        var responsePromise = $http.get(urlVal);
        responsePromise.success(function(data, status, headers, config) {
            $scope.searchData = data;
//            console.log($scope.searchData.categoryId);

        });
        //  responsePromise.error(function(data, status, headers, config) {
        //      alert("AJAX failed!");
        //   });

        //console.log("search calaled");

    };
    //----------------------------------------------------------------------------   
    $scope.createOffer = function(item)
    {


        $scope.selectedProduct = $scope.searchData.filter(function(entry) {
            return entry.productName === item.productName;
        })[0];

        $scope.offerData.productId = $scope.selectedProduct.productId;
        $scope.offerData.categoryId = $scope.selectedProduct.categoryId.categoryId;
        $scope.offerData.shopId = googleFactory.getShopDetails().shopId;
        $scope.offerData.cityId = googleFactory.getShopDetails().city.cityId;



        var responsePromise = $http.post("webresources/discountinfo/createOffer", $scope.offerData);
        responsePromise.success(function(data, status, headers, config) {
            alert("Successfully created");

            $location.path('/shopDetails/' + googleFactory.getShopDetails().shopId);
        });
        responsePromise.error(function(data, status, headers, config) {
            alert("failed in Offer creation!");
        });


        console.log($scope.offerData);
    };



});
