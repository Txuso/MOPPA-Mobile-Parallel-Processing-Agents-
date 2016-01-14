var login = angular.module("login", []);
var moppaLogin = angular.module("moppaLogin", ["login"]);

login.controller("login", function ($scope, $http, $interval, $window) {
    
    $scope.login = function() {

 		$http({
        method: 'POST',
        url: 'http://192.168.85.131:8080/moppa/v1/user/login',
		data: { 'userName' : $scope.userName , 'password' : $scope.password}
        }).
        success(function(data, status, headers, config) {
        	window.location ="moppa.html"
        }).
        error(function(data, status, headers, config) {	
        	window.location ="moppa.html"


       });
    };
});


