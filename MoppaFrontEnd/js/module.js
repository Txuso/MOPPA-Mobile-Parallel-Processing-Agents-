var createTask = angular.module("createTask", []);
var findTaskByUser = angular.module("findTaskByUser", []);
var findTaskByState = angular.module("findTaskByState", []);
var myApp = angular.module("myApp", ["createTask","findTaskByUser", "findTaskByState"]);

createTask.controller("createTask", function ($scope, $http, $interval, $window) {
    
    $scope.createTask = function() {
    	$scope.error = "Wait untill the petition is processed...";
    	
 		$http({
        method: 'POST',
        url: 'http://192.168.85.131:8080/moppa/v1/task/createTask',
		data: { 'userName' : $scope.userName , 'taskValue' : $scope.taskValue }
        }).
        success(function(data, status, headers, config) {
		$scope.error = "The task with the value " + $scope.taskValue + " has been sent to processing.";
		            }).
        error(function(data, status, headers, config) {	
		$scope.error = data;

       });
    };
});

findTaskByUser.controller("findTaskByUser", function($scope, $http, $interval, $window) {
	
	$scope.findTaskByUser = function() {
    	$scope.error2 = "Wait untill the petition is processed...";
    	
 		$http({
        method: 'POST',
        url: 'http://192.168.85.131:8080/moppa/v1/task/findTaskByUsername',
		data: { 'userName' : $scope.findUserName }
        }).
        success(function(data, status, headers, config) {
		$scope.error2 = "These are the tasks: ";
		$scope.results = data;		 

		            }).
        error(function(data, status, headers, config){	
		$scope.error2 = "System couldn't read your username. "
          + "Please contact the administrator.";
        	
       });
    };

});

findTaskByState.controller("findTaskByState", function($scope, $http, $interval, $window) {
	
	 $scope.checkboxSelection = 'Doing';
	    $scope.isCheckboxSelected = function(index) {
	        return index === $scope.checkboxSelection;
	    };
	    
	$scope.findTaskState = function() {
		
    	$scope.error3 = "Wait untill the petition is processed...";
 		$http({
        method: 'POST',
        url: 'http://192.168.85.131:8080/moppa/v1/task/findTaskByTaskState',
		data: { 'userName' : $scope.taskUserName, 'taskState' : $scope.checkboxSelection}
        }).
        success(function(data, status, headers, config) {
		$scope.error3 = "These are the tasks: ";
		$scope.results = data;
		            }).
        error(function(data, status, headers, config){	 
    			$scope.error3 = "There are no tasks assigned to " + $scope.taskUserName + 
    			" with the state: " + $scope.checkboxSelection;
        	 
       });
    };

});



	