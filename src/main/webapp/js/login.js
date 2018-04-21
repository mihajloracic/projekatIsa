// $(document).on('click', '#submit', function(e) {
//     e.preventDefault();
//
//     window.alert('klik na link');
// });

$(document).on('click', '#submit', function(e) {
    e.preventDefault();

    sendLoginData();
});

function sendLoginData() {
    var inputs = document.getElementsByTagName('input');

    var username = inputs[0].value;
    var password = inputs[1].value;

    var data = JSON.stringify({ "username" : username, "password" : password});

    var settings = {
        "async": true,
        "url": "/api/auth",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json"
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings).done(function (response) {
        window.sessionStorage.accessToken = response.token;
        window.location = "/api/userProfile.html";
        localStorage.setItem("x-auth-token",response.token);
    });

}

