/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var shopsummaryApp = angular.module('myApp.shopsummary', ['ngRoute']);

shopsummaryApp.config(function($routeProvider) {
    $routeProvider.
            when('/shopsummary', {
                templateUrl: 'app/partials/shopsummary.html',
                controller: 'shopSummaryController'
            }).
            otherwise({
                redirectTo: '/'
            });
});

//---------------------------------------------------------------------------- 
shopsummaryApp.controller('shopSummaryController', function($scope, $http, $location, AuthService)
{
    $scope.shopData = {};
    // $scope.shopInfo = {};
    $scope.validateUser = function()
    {
        if (AuthService.getUserStatus() === false)
        {
            $location.path('/login');
        }
        else
        {
            var responsePromise = $http.get("webresources/shopaccessor/getShops/" + AuthService.getRetailerId());
            responsePromise.success(function(data, status, headers, config) {
                $scope.shopData.fromServer = data;

            });
            responsePromise.error(function(data, status, headers, config) {
                alert(" failed in shopSummaryController !");
            });
        }
    };

    $scope.shopEntry = function()
    {
        $location.path('/shopEntry');
    };

    $scope.validateUser();
});
