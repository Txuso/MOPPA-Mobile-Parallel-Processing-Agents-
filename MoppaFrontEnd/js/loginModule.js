var login = angular.module("login", []);
var register = angular.module("register", []);
var moppaLogin = angular.module("moppaLogin", ["login","register"]);

login.controller("login", function ($scope, $http, $interval, $window) {
    
    $scope.login = function() {

 		$http({
        method: 'POST',
        url: 'localhost:8080/moppa/v1/user/login',
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

register.controller("register", function ($scope, $http, $interval, $window) {
    
    $scope.login = function() {
    	
 		$http({
        method: 'POST',
        url: 'localhost:8080/moppa/v1/user/register',
		data: { 'userName' : $scope.newUserName , 'password' : $scope.newPassword}
        }).
        success(function(data, status, headers, config) {
        	
        }).
        error(function(data, status, headers, config) {	
        	window.location ="#/moppa.html"

       });
    };
});

