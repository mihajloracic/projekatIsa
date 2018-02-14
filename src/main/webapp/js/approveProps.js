var app = angular.module("myApp", []);
app.controller("myCtrl", function($scope, $http) {
    config = { headers : {
        "x-auth-token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTUxNjgzOTQxODMwNywiZXhwIjoxNTE3NDQ0MjE4fQ.7_JbBQDusHTJbDgqtg8YB9hPg7qpQIzYD2lXjaR8SG-lJkbjNg6IeQBhwbC62YqKKrAIHoQYOyImtxE2yy0ekg",

    }};
    $scope.records = $http.get("/api/props/admin",config).then(function (response) {
        $scope.records = response.data;
    });
    var settings = {
        url     : '/api/props/admin',
        headers: {
            "x-auth-token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTUxNjgzOTQxODMwNywiZXhwIjoxNTE3NDQ0MjE4fQ.7_JbBQDusHTJbDgqtg8YB9hPg7qpQIzYD2lXjaR8SG-lJkbjNg6IeQBhwbC62YqKKrAIHoQYOyImtxE2yy0ekg",
            "content-type": "application/json"
        }
    }
    $scope.approveProps = function (props) {
        $http({
            method  : 'POST',
            url     : '/api/props/admin',
            data    : props,
            headers: {
                "x-auth-token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTUxNjgzOTQxODMwNywiZXhwIjoxNTE3NDQ0MjE4fQ.7_JbBQDusHTJbDgqtg8YB9hPg7qpQIzYD2lXjaR8SG-lJkbjNg6IeQBhwbC62YqKKrAIHoQYOyImtxE2yy0ekg",
                "content-type": "application/json",
            },
        }).then(function(data) {
            props.approved = true;
        },function (error){

        });
    }
    $scope.deleteProps = function (props) {
        props.approved = true;
    }
});