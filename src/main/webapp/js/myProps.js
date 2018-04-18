var app = angular.module("myApp", []);
app.controller("myCtrl", function($scope, $http) {
    config = { headers : {
        "x-auth-token": window.sessionStorage.accessToken,

    }};
    $scope.records = $http.get("/api/props/mypProps",config).then(function (response) {
        $scope.records = response.data;
    });
    var settings = {
        url     : '/api/props/admin',
        headers: {
            "x-auth-token": window.sessionStorage.accessToken,
            "content-type": "application/json"
        }
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