var discountDetailsApp = angular.module('myApp.discountDetails', ['ngRoute']);
discountDetailsApp.config(function($routeProvider) {
    $routeProvider.
            when('/discountDetails/:shopId', {
                templateUrl: 'app/partials/discountDetails.html',
                controller: 'discountDetailsController'
            }).
            otherwise({
                redirectTo: '/'
            });
});

discountDetailsApp.controller('discountDetailsController', function($scope, $routeParams, $http)
{
    $scope.discountDetails = {};
    $scope.shopValue = {};

    //----------------------------------------------------------------------------   
    $scope.getShopDiscount = function(shopId)
    {

        var responsePromise = $http.get("webresources/discountinfo/getShopDiscounts/" + shopId.trim());
        responsePromise.success(function(data, status, headers, config) {
            $scope.discountDetails.fromServer = data;

            $scope.shopValue = data.filter(function(entry)
            {
                return entry.shopId.shopId == $routeParams.shopId;
            })[0];
        });
        responsePromise.error(function(data, status, headers, config) {
            alert("AJAX failed!");
        });
    };
    //----------------------------------------------------------------------------   
    $scope.getShopDiscount($routeParams.shopId);
});
