function getLoggedInUsername() {
    var ret = "";

    var settings = {
        "async": false,
        "crossDomain": true,
        "url": "http://localhost:8096/api/currentUser/username",
        "method": "GET",
        "headers": {
            "X-Auth-Token": window.sessionStorage.accessToken,
            "Cache-Control": "no-cache"
        }
    }

    $.ajax(settings)
        .done(function (response) {
            ret = response;
        })
        .fail(function () {
            ret = "";
        });
    return ret;
}

function getIdenticonUrl(username) {
    return "http://identicon.org/?t=" + username + "&s=64";
}