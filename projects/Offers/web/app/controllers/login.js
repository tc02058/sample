var loginApp = angular.module('myApp.login', ['ngRoute']);
loginApp.config(function($routeProvider) {
    $routeProvider.
            when('/login', {
                templateUrl: 'app/partials/login.html',
                controller: 'loginController'
            }).
            otherwise({
                redirectTo: '/'
            });
});
//---------------------------------------------------------------------------- 
loginApp.factory('AuthService', function($http)
{
    var userStatus = false;
    var userId = null;
    var retailerId = null;
    return {
        login: function(credentials)
        {
            return $http.post("webresources/retailermaster/validateUser", credentials);

        },
        logout: function()
        {

        },
        setUserStatus: function(status)
        {
            userStatus = status;
        },
        getUserStatus: function()
        {
            return userStatus;
        },
        setUserId: function(user)
        {
            userId = user;
        },
        getUserId: function()
        {
            return userId;
        },
        setRetailerId: function(retailer)
        {
            retailerId = retailer;
        },
        getRetailerId: function()
        {
            return retailerId;
        }
    };

});
//---------------------------------------------------------------------------- 
loginApp.controller('loginController', function($scope, $location, AuthService)
{
    $scope.credentials = {email: "", password: ""};

    $scope.isLoggedIn = function()
    {
      
        if (AuthService.getUserStatus() === true)
        {
            $location.path('/shopsummary');
        
        }
    };

    $scope.login = function()
    {

        AuthService.login($scope.credentials).success(function(data)
        {
            AuthService.setUserStatus(true);
            AuthService.setUserId($scope.credentials.email);
            AuthService.setRetailerId(data.retailerId);
            $location.path('/shopsummary');
        });
        AuthService.login($scope.credentials).error(function()
        {
            alert("Please check the credentials");
        });
    };

 $scope.isLoggedIn();

});
