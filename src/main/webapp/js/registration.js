$(document).on('click', '#submit', function(e) {
    e.preventDefault();

    sendRegistrationData();
});


function sendRegistrationData() {
    var inputs = document.getElementsByTagName('input');

    var username = inputs[0].value;
    var password = inputs[1].value;
    var confpassword = inputs[2].value;
    var email = inputs[3].value;
    var firstname = inputs[4].value;
    var lastname = inputs[5].value;
    var city = inputs[6].value;
    var phonenumber = inputs[7].value;

    var data = JSON.stringify({
        "username" : username,
        "password" : password,
        "email" : email,
        "firstname" : firstname,
        "lastname" : lastname,
        "city" : city,
        "phonenumber" : phonenumber
    })

    if(!passwordsMatch(password, confpassword)) {
        window.alert('Lozinke se ne poklapaju!');
        return;
    }

    if(!fieldsFilled()) {
        window.alert('Morate popuniti sva polja!');
        return;
    }

    var settings = {
        "async": true,
        "url": "/api/registration",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json"
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings)
        .done(function (response) {
                window.alert("Poslato!");
                inform();
        })
        .fail(function (xhr, status, error) {
            if(xhr.status == 409) {
                window.alert('Postoji korisnik sa istim koriscnickim imenom / emailom');
                //handleIntegrityException(settings.data);
            }
        });

}

function passwordsMatch(first, second) {
    if(first === second) {
        return true;
    }
    return false;
}

function fieldsFilled() {
    var inputs = $('.form-field');
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].value === "") {
            return false;
        }
    }
    return true;
}

function inform() {
    $('#mainContainer').hide();
    $('#mainContainer').parent().append('<h3>Poslat mail sa linkom za potvrdu registracije.</h3>' +
                        '<a href="/api/">početna</a><br><a href="/api/login.html">uloguj se</a>');
}