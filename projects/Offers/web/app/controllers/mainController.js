var app = angular.module("myApp", ['ngRoute',
    'myApp.discountDetails',
    'myApp.login',
    'myApp.signup',
    'myApp.shopsummary',
    'myApp.shopDetails',
    'myApp.shopEntry',
    'myApp.discountEntry',
    'myApp.productEntry'
]);
app.config(function($routeProvider) {
    $routeProvider.
            when('/', {
                templateUrl: 'app/partials/discountSearchSummary.html',
                controller: 'discountSSController'
            }).
            otherwise({
                redirectTo: '/'
            });
});

app.factory('cityFactory', function()
{
    var cityList = {};
    var cityName = null;
    var cityId = 0;
    var currentCity = {};

    return {
        setCityId: function(cityIdVal)
        {
            cityId = cityIdVal;
        },
        getCityId: function()
        {
            return cityId;
        },
        setCities: function(cityListVal)
        {
            cityList = cityListVal;
        },
        getCities: function()
        {
            return cityList;
        },
        setCityName: function(cityNameVal)
        {
            cityName = cityNameVal;
        },
        getCityName: function()
        {
            return cityName;
        },
        setCurrentCity: function(cityObj)
        {
            currentCity = cityObj;
        },
        getCurrentCity: function()
        {
            return currentCity;
        }

    };

});

app.factory('googleFactory', function()
{
    //  var cityName = null;
    //   var stateName = null;
    var googleAddr = null;
    //  var locality = null;
    var shopDetail = {};
    return {
        setGoogleAddr: function(googleAddress)
        {
            googleAddr = googleAddress;
        },
        getGoogleAddr: function()
        {
            return googleAddr;
        },
        setShopDetails: function(shopDet)
        {
            shopDetail = shopDet;
        },
        getShopDetails: function()
        {
            return shopDetail;
        }
        /*      setLocality: function(localityVal)
         {
         locality = localityVal;
         },
         getLocality: function()
         {
         return locality;
         } */
    };

});

    



//----------------------------------------------------------------------------
app.controller('discountSSController', function($scope, $http, cityFactory) {

    //var currentDistrict = "";  
//----------------------------------------------------------------------------

//input variables
    $scope.cities = {};
    $scope.selectedCityId = null;
 
 

    //  $scope.currentCity = {};
    // $scope.position = {};
    // var citySelected = null;


    $scope.geoLoc = function()
    {
        if (cityFactory.getCityId() === 0)
        {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function(position) {
                    $scope.$apply(function() {
                        var latlon = position.coords.latitude + "," + position.coords.longitude;
                        var urlValue = "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + latlon + "&sensor=true";
                        var responsePromise = $http.get(urlValue);
                        responsePromise.success(function(data, status, headers, config) {
                            cityFactory.setCityName(data.results[0].address_components[3].long_name);
                            $scope.getCities();
                        });
                        responsePromise.error(function(data, status, headers, config) {
                            alert("failed in getLoc()");
                        });
                    });
                });
            }
        }
        else
        {
            $scope.cities.fromServer = cityFactory.getCities();
            $scope.selectedCityId = cityFactory.getCityId();
            $scope.getDiscountSummary();
        }
    };


    //----------------------------------------------------------------------------

    $scope.getCities = function()
    {
        var responsePromise = $http.get("webresources/citymaster");
        responsePromise.success(function(data, status, headers, config) {
            $scope.cities.fromServer = data;
            cityFactory.setCities(data);
            console.log(cityFactory.getCityName());
            $scope.selectedCity = data.filter(function(entry) {
                return entry.cityName === cityFactory.getCityName();
            })[0];
           
            
            $scope.selectedCityId = $scope.selectedCity.cityId;
            cityFactory.setCityId($scope.selectedCityId);
            cityFactory.setCityName($scope.selectedCity.cityName);
            cityFactory.setCurrentCity($scope.selectedCity);
            $scope.getDiscountSummary();
        });
        responsePromise.error(function(data, status, headers, config) {
            alert("failed getCities()");
        });

    };
    //---------------------------------------------------------------------------- 

    $scope.discountData = {};
    $scope.getDiscountSummary = function()
    {

        $scope.getDiscount("webresources/discountinfo/getCityDiscount/" + $scope.selectedCityId + "/" + $scope.prev);
        $scope.getCount("webresources/discountinfo/getCityCount/" + $scope.selectedCityId);

    };
    //----------------------------------------------------------------------------
    $scope.item = {searchId: ""};
    $scope.searchDiscount = function(item, pageNo)
    {
        cityFactory.setCityId($scope.selectedCityId);
        $scope.cities.fromServer = cityFactory.getCities();
        $scope.selectedCity = $scope.cities.fromServer.filter(function(entry) {
            return entry.cityId == $scope.selectedCityId;
        })[0];
        cityFactory.setCityName($scope.selectedCity.CityName);


        if (item.searchId.length === 0)
        {
            $scope.getDiscount("webresources/discountinfo/getCityDiscount/" + $scope.selectedCityId + "/" + pageNo);
            $scope.getCount("webresources/discountinfo/getCityCount/" + $scope.selectedCityId);

        }
        else
        {
            $scope.getDiscount("webresources/discountinfo/searchDiscount/" + item.searchId.trim() + "/" + $scope.selectedCityId + "/" + pageNo);
            $scope.getCount("webresources/discountinfo/getSearchDiscountCount/" + item.searchId.trim() + "/" + $scope.selectedCityId);
        }

    };
    //----------------------------------------------------------------------------
    $scope.navigateDiscount = function(item, pageNo)
    {
        if (item.searchId.length === 0)
        {
            $scope.getDiscount("webresources/discountinfo/getCityDiscount/" + $scope.selectedCityId + "/" + pageNo);
        }
        else
        {
            $scope.getDiscount("webresources/discountinfo/searchDiscount/" + item.searchId.trim() + "/" + $scope.selectedCityId + "/" + pageNo);
        }

    };
    //----------------------------------------------------------------------------  

    $scope.getDiscount = function(urlVal)
    {
        var responsePromise = $http.get(urlVal);
        responsePromise.success(function(data, status, headers, config) {
            $scope.discountData.fromServer = data;


        });
        responsePromise.error(function(data, status, headers, config) {
            alert("Discount failed!");
        });

    };
    //----------------------------------------------------------------------------  
    $scope.searchData = {};
    $scope.getSearchLst = function(item)
    {
        if (item.searchId.trim().length !== 0)
        {
            var responsePromise = $http.get("webresources/searchtable/getSearchList/" + item.searchId.trim() + "/" + $scope.selectedCityId);
            responsePromise.success(function(data, status, headers, config) {
                $scope.searchData.fromServer = data;

            });
        }
       
        // $scope.search("webresources/searchtable/getSearchList/" + item.searchId.trim() + "/" + $scope.selectedCityId);
    };
    //---------------------------------------------------------------------------- 

    $scope.changeCity = function()
    {
        $scope.item = {searchId: ""};

    };
    //----------------------------------------------------------------------------   
    $scope.getCount = function(url)
    {
        //   alert(url);
        var responsePromise = $http.get(url);
        responsePromise.success(function(data, status, headers, config) {
            $scope.count = data;
            $scope.count = Math.ceil($scope.count / 3) + 1;

        });
        responsePromise.error(function(data, status, headers, config) {
            alert(" failed in getCount() !");
        });

    };
    //----------------------------------------------------------------------------
    $scope.prev = 1;
    $scope.next = 2;
    $scope.count = 0;
    $scope.prevPage = function(pageNo)
    {
        if ($scope.prev !== 1)
        {
            $scope.prev = $scope.prev - 1;
            $scope.next = $scope.next - 1;
            $scope.navigateDiscount($scope.item, pageNo - 1);
        }
    };
    //----------------------------------------------------------------------------          
    $scope.nextPage = function(pageNo)
    {
        if (pageNo !== $scope.count)
        {
            $scope.navigateDiscount($scope.item, pageNo);
            $scope.prev = $scope.prev + 1;
            $scope.next = $scope.next + 1;
        }
    };
    //----------------------------------------------------------------------------   

    $scope.geoLoc();

    //  $scope.getValidation("webresources/o4retailermaster/validateUser");
    // $scope.getCities(citySelected);

});