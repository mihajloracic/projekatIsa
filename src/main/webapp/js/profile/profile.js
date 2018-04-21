$(document).ready(function () {
    var currentUser = getLoggedInUsername();
    loadHeader(currentUser);
    loadUserData(currentUser);
});

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
        });
    return ret;
}

function loadHeader(username) {
    var container = $('#profile-identicon');
    loadIdenticon(container, username);
    $('#profile-username').text(username);
}


function loadIdenticon(container, username) {
    var identiconUrl = "http://identicon.org/?t=" + username + "&s=128";
    container.attr("src", identiconUrl);
}

function getIdenticonUrl(username) {
    return "http://identicon.org/?t=" + username + "&s=64";
}


function loadUserData(username) {

    var settings = {
        "async": false,
        "crossDomain": true,
        "url": "/api/user/getByUsername?username=" + username,
        "method": "GET",
        "headers": {
            "X-Auth-Token": window.sessionStorage.accessToken,
            "Cache-Control": "no-cache",
        }
    }

    $.ajax(settings)
        .done(function (data) {
            populateUserData(data);
        });
}

function populateUserData(data) {
    $('#profile-name-header').text(data.firstname + " " + data.lastname);

    document.getElementById('profile-firstname').value = data.firstname;
    document.getElementById('profile-lastname').value = data.lastname;
    document.getElementById('profile-email').value = data.email;
    document.getElementById('profile-city').value = data.city;
    document.getElementById('profile-phonenumber').value = data.phonenumber;
}