
var Props = [];
$(document).ready(function () {

    $.ajax(settings).done(function (response) {
        Props = response;
    });

    $("#jsGrid").jsGrid({
        width: "100%",
        height: "400px",

        inserting: true,
        editing: true,
        sorting: true,
        paging: true,

        data: Props,
        fields: [
            { name: "name", type: "text", width: 150 },
            { name: "description", type: "text", width: 250 },
            { name: "expirationDate", type: "date", width: 200 },
            { name: "imageUrl", type: "text",width:200 },
        ]
    });
});

var settings = {
    "async": false,
    "crossDomain": true,
    "url": "http://localhost:8096/api/props/admin",
    "method": "GET",
    "headers": {
        "x-auth-token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTUxNTE3MjcyNDIyMiwiZXhwIjoxNTE1Nzc3NTI0fQ.0tTNwcoOefwFdffEftRgLhkEe4JqzpnXkLt1EJVJW17nduu6zLFt4iVyshtvKb7PQG2g62Uta7BH6SXqRb3RKA",
        "content-type": "application/json",
    },
    "processData": false,
    "data": "{\n\t\"id\" : 1\n}"
}


