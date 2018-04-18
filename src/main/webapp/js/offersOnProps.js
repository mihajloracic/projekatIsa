var app = angular.module("myApp", []);
app.controller("myCtrl", function($scope, $http) {
    config = { headers : {
        "x-auth-token": window.sessionStorage.accessToken,
    }};
    $scope.records = $http.get("/api/propsoffer/byprops?propsId="+getUrlVars()["propsId"],config).then(function (response) {
        $scope.records = response.data;
        console.log(response.data)
    });
    $scope.props = $http.get("/api/props/propsbyid?propsId="+getUrlVars()["propsId"],config).then(function (response) {
        $scope.props = response.data;
        console.log(response.data)
    });
    $scope.acceptOffer = function (offer) {
        var data = JSON.stringify({ "propsId" : offer.id, "offerValue" : offer.value});
        $http({
            method  : 'POST',
            url     : '/api/propsoffer/accept',
            data    : data,
            headers: {
                "x-auth-token": window.sessionStorage.accessToken,
                "content-type": "application/json",
            },
        }).then(function(data) {
            window.alert("Item succesfully sold")
        },function (error){
            window.alert("Offer has been changed. Try again")
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