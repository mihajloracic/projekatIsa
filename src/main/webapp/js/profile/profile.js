$(document).ready(function () {
    var currentUser = getLoggedInUsername();
    loadHeader(currentUser);
    loadUserData(currentUser);

    $.ajax({
        "url": "/api/reservation/user/"+currentUser
        , "method": "GET"
        , success: function (result) {

            var arrayLength = result.length;
            var str = "";
            for (var i = 0; i < arrayLength; i++) {
                str+="<ul class=\"list-group\">\n" +
                    "            <li class=\"list-group-item\"><b>" +
                    result[i].event.show.name + " " + result[i].event.show.date +
                    "</b>  - Oceni ambijent:\n" +
                    "                <input type=\"number\"  id=\"venueGrade"+ result[i].id + "\" min=\"1\" max=\"5\" step=\"1\"/>Oceni predstavu:\n" +
                    "                <input type=\"number\"  id=\"showGrade"+ result[i].id + "\" min=\"1\" max=\"5\" step=\"1\"/>\n" +
                    "                <button type=\"button\" id=\"button"+ result[i].id + "\" class=\"btn btn-primary grade\">Oceni!</button>\n" +
                    "            </li>\n" +
                    "        </ul>"
            }
            $("#history")[0].innerHTML = str;

        }
    });

    $(document).on('click', '.grade', function (e)  {

        reservationId = e.target.id.slice(-1);
        venueGrade = $('#venueGrade'+reservationId).val();
        showGrade = $('#showGrade'+reservationId).val();
        if(venueGrade == "" || showGrade == "")
            return;

        var grades = {
            "showGrade": showGrade,
            "venueGrade": venueGrade,
            "reservationId": reservationId,
        }

        $.ajax({
            "url": "/api/grade/"
            , "method": "POST"
            , contentType: 'application/json'
            , dataType: 'json'
            , data: JSON.stringify(grades)
            , success: function (result) {

                window.location.reload();

            }
        });


    });

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