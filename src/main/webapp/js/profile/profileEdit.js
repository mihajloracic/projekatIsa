$(document).on('click', '#profile-edit', function(e) {
    e.preventDefault();

    //dodaj dugme sacuvaj ispod broja telefona
    var appendSaveButtonTo = $('#profile-phonenumber').parent().next().next();
    addSaveButton(appendSaveButtonTo);
    enableEdit();
});

$(document).on('click', '#save-changes-btn', function(e) {
    e.preventDefault();

    saveChanges();
    disableEdit();
    removeSaveButton();
});


function disableEdit() {
    $('.editable').each(function (i, inputEl) {
            $(inputEl).attr('readonly', true);
    });
}

function saveChanges() {

    var changed = [];

    $('.editable').each(function (i, inputEl) {
        changed.push(inputEl.value);
    });

    var currentUser = getLoggedInUsername();

    var data = JSON.stringify({
        "username" : currentUser,
        "firstname" : changed[0],
        "lastname" : changed[1],
        "email" : changed[2],
        "city" : changed[3],
        "phonenumber" : changed[4]
    });

    var settings = {
        "async": true,
        "url": "/api/user/editUserInfo",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json",
            "X-Auth-Token": window.sessionStorage.accessToken
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings)
        .done(function (response) {
            populateUserData(response);
        })
        .fail(function (xhr, status, error) {
            if(xhr.status == 409) {
                window.alert('Postoji korisnik sa unetim emailom!');
            }
        });

}

function addSaveButton(appendSaveButtonTo) {
    var html =
        '<div class="col-sm-8 col-xs-6 tital " ></div>' +
        '<div id="save-edit" class="col-sm-4">' +
        '<a href="#" id="save-changes-btn" class="btn btn-primary btn-sm">Sačuvaj izmene<i class="fa fa-sign-in"></i></a>' +
        '</div>' +
        '<div class="clearfix"></div>' +
        '<div class="bot-border"></div>\n';

    appendSaveButtonTo.after(html);
}

function removeSaveButton() {
    var prevEl = $('#profile-phonenumber').parent().next().next();
    prevEl.next().remove();
    prevEl.next().remove();
    prevEl.next().remove();

}

function enableEdit() {
    $('.editable').each(function (i, inputEl) {
        $(inputEl).attr('readonly', false);
    });
    $('.editable')[0].focus();

}