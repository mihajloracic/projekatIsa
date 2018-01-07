
$(document).ready(function () {
});
$("#submit").click(function () {

    data = formToJSON($("#props_name").val(),$("#props_description").val(),$("#props_expiration_date").val());
    settings.data = data;
    $.ajax(settings).done(function (response) {
        console.log(response);
    });
});
var settings = {
    "async": true,
    "crossDomain": true,
    "url": "http://localhost:8096/api/props/",
    "method": "POST",
    "headers": {
        "x-auth-token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTUxNTE3MjcyNDIyMiwiZXhwIjoxNTE1Nzc3NTI0fQ.0tTNwcoOefwFdffEftRgLhkEe4JqzpnXkLt1EJVJW17nduu6zLFt4iVyshtvKb7PQG2g62Uta7BH6SXqRb3RKA",
        "content-type": "application/json",
    },
    "processData": false,
    "data": data
}
function formToJSON(name,description,date) {
    return JSON.stringify({
        "name" : name,

    });
}