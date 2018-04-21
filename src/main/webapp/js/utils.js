function getLoggedInUsername() {
    var ret = "";

    var settings = {
        "async": false,
        "crossDomain": true,
        "url": "/api/currentUser/username",
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

function getUserAuthorities() {
    var ret = "";

    var settings = {
        "async": false,
        "crossDomain": true,
        "url": "/api/user/userDetails",
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

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

function getIdenticonUrl(username) {
    return "http://identicon.org/?t=" + username + "&s=64";
}