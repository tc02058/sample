/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var shopEntryApp = angular.module('myApp.shopEntry', ['ngRoute']);

shopEntryApp.config(function($routeProvider) {
    $routeProvider.
            when('/shopEntry', {
                templateUrl: 'app/partials/shopEntry.html',
                controller: 'ShopEntryController'
            }).
            otherwise({
                redirectTo: '/'
            });
});



shopEntryApp.directive('googlePlaces', function(googleFactory) {
    return {
        restrict: 'E',
        replace: true,
        // transclude:true,
        scope: {location: '='},
        template: '<input id="google_places_ac" ng-model="shopInfo.address" name="google_places_ac" type="text" class="form-control"/>',
        link: function($scope, elm, attrs) {
            var autocomplete = new google.maps.places.Autocomplete($("#google_places_ac")[0], {});
            google.maps.event.addListener(autocomplete, 'place_changed', function() {
                var place = autocomplete.getPlace();
                googleFactory.setGoogleAddr(place);


                $scope.location = place.geometry.location.lat() + ',' + place.geometry.location.lng();
                $scope.$apply();
            });
        }
    };
});

//----------------------------------------------------------------------------
shopEntryApp.controller('ShopEntryController', function($scope, $http, $location,
        cityFactory, AuthService, googleFactory)
{
    $scope.getDatetime = new Date;
    //   $scope.selectedCity={};
    $scope.stateName = {state: ""};
    $scope.street = null;
    $scope.completeAddress = null;
    $scope.currentUser = AuthService.getUserId();

    $scope.shopInfo = {shopName: "", address: "", locality: "", city: "", state: "",
        phone1: "", phone2: "", website: "", parentShopId: "0", googleAddress: "",
        createdDate: $scope.getDatetime, updatedDate: $scope.getDatetime,
        createdUser: $scope.currentUser, updatedUser: $scope.currentUser};


    $scope.cities = cityFactory.getCities();
    $scope.selectedCityId = cityFactory.getCityId();
    $scope.stateName.state = cityFactory.getCurrentCity().stateId.stateName;
    $scope.shopInfo.city = $scope.selectedCityId;
    $scope.shopInfo.state = cityFactory.getCurrentCity().stateId.stateId;

    $scope.createShop = function()
    {

        console.log($scope.shopInfo);

        var responsePromise = $http.post("webresources/retailshopmaster/createShop", $scope.shopInfo);
        responsePromise.success(function(data, status, headers, config) {
            alert("Successfully created");
            // $scope.shopData.fromServer = data;
            // alert("success");
            $location.path('/shopsummary');

        });
        responsePromise.error(function(data, status, headers, config) {
            alert(" failed in Shop creation!");
        });

    };
//----------------------------------------------------------------------------    
    $scope.selectedCity = {};

    $scope.assignShopInfo = function()
    {

        var address = "";


        for (var i = 0; i < googleFactory.getGoogleAddr().address_components.length; i++) {
            var addressType = googleFactory.getGoogleAddr().address_components[i].types[0];

            var val = googleFactory.getGoogleAddr().address_components[i]['long_name'];

            if (addressType === 'route')
            {
                address = val + ",";
            }
            if (addressType === 'sublocality_level_3')
            {
                address = address + val + ",";
            }
            if (addressType === 'sublocality_level_2')
            {
                address = address + val + ",";
            }
            if (addressType === 'sublocality_level_1')
            {
                $scope.shopInfo.locality = val;
            }
            if (addressType === 'locality')
            {
                $scope.shopInfo.city = val;
            }
            else if (addressType === 'administrative_area_level_1')
            {
                $scope.shopInfo.state = val;
            }

        }


        $scope.shopInfo.googleAddress = googleFactory.getGoogleAddr().formatted_address;
        console.log("dharma in Shop Entry");
        console.log($scope.shopInfo.city);
        
        $scope.selectedCity = $scope.cities.filter(function(entry) {
            return entry.cityName === $scope.shopInfo.city;
        })[0];

        if ($scope.selectedCity === undefined)
        {

            $scope.selectedCity = $scope.cities.filter(function(entry) {
                return entry.altCityName === $scope.shopInfo.city;
            })[0];
              
        }
       
        console.log($scope.selectedCity);
        $scope.shopInfo.address = $scope.street + "," + address;
        $scope.completeAddress = $scope.street + "," + $scope.shopInfo.googleAddress;
        $scope.shopInfo.city = $scope.selectedCity.cityId;
        $scope.shopInfo.state = $scope.selectedCity.stateId.stateId;

    };
});
