
$(document).on('click', '#submit', function(e) {
    e.preventDefault();

    sendLoginData();
});

function sendLoginData() {
    var inputs = document.getElementsByTagName('input');

    var name = inputs[0].value;
    var description = inputs[1].value;
    var date = inputs[2].value;
    var cinemaId = getUrlVars()["cinemaId"];
    var data = JSON.stringify({ "name" : name, "description" : description, "expirationDate" : date, "cinemaId" : cinemaId});

    var settings = {
        "async": true,
        "url": "/api/props",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json",
            "x-auth-token": window.sessionStorage.accessToken
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings).done(function (response) {
        window.alert('uspesno dodat oglas, ceka odborenje');
    });

}
function getUrlVars()
{
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}