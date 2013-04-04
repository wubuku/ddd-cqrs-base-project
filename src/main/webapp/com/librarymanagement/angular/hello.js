/**
 * Created with IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 28/3/13
 * Time: 11:25 PM
 * To change this template use File | Settings | File Templates.
 */
var angularDemo = angular.module('HelloAngular',[]);

angularDemo.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/hello', {templateUrl: 'hello.html',controller:AngularDemoController}).
        when('/bye', {templateUrl: 'bye.html',controller:AngularDemoController}).
        otherwise({redirectTo: '/hello.html'});
}])

var AngularDemoController = function AngularDemoController($scope,$http) {

    $scope.serverData = "Data hard coded in the client"
    $scope.isServer = true
    $scope.label = "Server"

    callServer()

    $scope.toggleClientServer = function toggleClientServer(){
        console.log("toggleClientServer")
            if($scope.isServer){
                $scope.isServer = false
                $scope.label = "Client"
            }else{
                callServer()
                $scope.isServer = true
                $scope.label = "Server"
            }
    }


    function callServer(){
        $http({method: 'GET', url: '/MultiTenantBaseProject/mvc/test/'}).
            success(function(data, status, headers, config) {
                $scope.serverData= "date " + data.date + " datetime " + data.dateTime;
            }).
            error(function(data, status, headers, config) {

            });
    }



}