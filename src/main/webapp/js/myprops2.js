var app = angular.module("myApp", []);
app.controller("myCtrl", function($scope, $http) {
    config = { headers : {
        "x-auth-token": window.sessionStorage.accessToken,

    }};
    $scope.records = $http.get("/api/props/myProps",config).then(function (response) {
        $scope.records = response.data;
    });
    var settings = {
        url     : '/api/props/myProps',
        headers: {
            "x-auth-token": window.sessionStorage.accessToken,
            "content-type": "application/json"
        }
    }
    $scope.approveProps = function (props) {
        $http({
            method  : 'POST',
            url     : '/api/props/admin',
            data    : props,
            headers: {
                "x-auth-token": window.sessionStorage.accessToken,
                "content-type": "application/json",
            },
        }).then(function(data) {
            props.approved = true;
        },function (error){
            console.log(error)
        });
    }
    $scope.deleteProps = function (props) {
        props.approved = true;
        $http({
            method  : 'DELETE',
            url     : '/api/props/admin',
            data    : props,
            headers: {
                "x-auth-token": window.sessionStorage.accessToken,
                "content-type": "application/json",
            },
        }).then(function(data) {
            props.approved = true;
        },function (error){
            console.log(error)
        });
    }
});