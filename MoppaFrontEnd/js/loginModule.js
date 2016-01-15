var login = angular.module("loginUser", []);
var moppaLogin = angular.module("moppaLogin", ["loginUser"]);

login.controller("loginUser", function ($scope, $http, $interval, $window) {
    
    $scope.loginUser = function() {

 		$http({
        method: 'POST',
        url: 'http://192.168.85.131:8080/moppa/v1/user/loginUser',
		data: { 'userName' : $scope.userName , 'password' : $scope.password}
        }).
        success(function(data, status, headers, config) {
        	window.location ="moppa.html"
        }).
        error(function(data, status, headers, config) {	
        	$scope.error = "Invalid credentials. Please review your input and try again."


       });
    };
});


