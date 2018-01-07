$(document).on('click', '#submitButton', function(e) {
    e.preventDefault();

    sendRegistrationData();
});


function sendRegistrationData() {
    var inputs = document.getElementsByTagName('input');

    var username = inputs[0].value;
    var password = inputs[1].value;
    var confpassword = inputs[2].value;
    var email = inputs[3].value;
    var name = inputs[4].value;
    var lastname = inputs[5].value;
    var city = inputs[6].value;
    var phonenumber = inputs[7].value;

    if(!passwordsMatch(password, confpassword)) {
        window.alert('Lozinke se ne poklapaju!');
        return;
    }

    if(!fieldsFilled()) {
        window.alert('Morate popuniti sva polja!');
        return;
    }

    window.alert('sve ok');
}

function passwordsMatch(first, second) {
    if(first === second) {
        return true;
    }
    return false;
}

function fieldsFilled() {
    var isValid = true;
    $('.form-field').each(function() {
        if ( $(this).text() === '' )
            isValid = false;
    });
    return isValid;
}