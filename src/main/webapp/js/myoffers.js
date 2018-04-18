var app = angular.module("myApp", []);
app.controller("myCtrl", function($scope, $http) {
    config = { headers : {
        "x-auth-token": window.sessionStorage.accessToken,
    }};
    $scope.records = $http.get("/api/propsoffer/user",config).then(function (response) {
        $scope.records = response.data;
        console.log(response.data)
    });
    $scope.makeOffer = function (props,value) {
        var data = JSON.stringify({ "propsId" : props.id, "offerValue" : value});
        $http({
            method  : 'POST',
            url     : '/api/propsoffer',
            data    : data,
            headers: {
                "x-auth-token": window.sessionStorage.accessToken,
                "content-type": "application/json",
            },
        }).then(function(data) {
            window.alert("offer changed")
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