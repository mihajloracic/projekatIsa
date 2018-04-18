var app = angular.module("myApp", []);
app.controller("myCtrl", function($scope, $http) {
    config = { headers : {
        "x-auth-token": window.sessionStorage.accessToken,
        "content-type" : "application/json"
    }};
    $scope.records = $http.get("/api/notification",config).then(function (response) {
        $scope.records = response.data;
        console.log(response.data)
    });

});
