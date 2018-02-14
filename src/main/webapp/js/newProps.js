
var postApp = angular.module('postApp', []);
postApp.controller('postController', function($scope, $http) {
    $scope.fileSelected = function (element) {
        var myFileSelected = element.files[0];
    };
    this.submitForm = function(file, uploadUrl){
        var form = new FormData();
        form.append('file', file);
        form.append("name", $scope.name);
        form.append("description", $scope.description);
        form.append("date", $scope.expirationDate);


        $http.post("/api/props/", form, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined,
                "x-auth-token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTUxNjgzOTQxODMwNywiZXhwIjoxNTE3NDQ0MjE4fQ.7_JbBQDusHTJbDgqtg8YB9hPg7qpQIzYD2lXjaR8SG-lJkbjNg6IeQBhwbC62YqKKrAIHoQYOyImtxE2yy0ekg"}
        }).success(function(){
        }).error(function(data){
            console.log(data);
        });
    }

});