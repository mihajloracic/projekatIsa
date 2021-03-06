var app = angular.module("myApp", []);
app.controller("myCtrl", function($scope, $http) {
    config = { headers : {
        "x-auth-token": window.sessionStorage.accessToken,

    }};
    $scope.records = $http.get("/api/props/admin?cinemaId="+getUrlVars()["cinemaId"],config).then(function (response) {
        $scope.records = response.data;
    });
    var settings = {
        url     : '/api/props/admin',
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
function getUrlVars()
{
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}