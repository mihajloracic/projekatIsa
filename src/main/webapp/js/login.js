$(document).on('click', '#submitButton', function(e) {
    e.preventDefault();

    sendLoginData();
});

function sendLoginData() {
    window.alert('alo');
    var inputs = document.getElementsByTagName('input');

    var username = inputs[0].value;
    var password = inputs[1].value;

    var data = JSON.stringify({ "username" : username, "password" : password});

    var settings = {
        "async": true,
        "url": "http://localhost:8096/api/auth",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json"
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings).done(function (response) {
        window.alert(response.token);
        window.sessionStorage.accessToken = response.token;
    });

}

