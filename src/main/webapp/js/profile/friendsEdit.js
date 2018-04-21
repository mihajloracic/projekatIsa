$(document).ready(function () {
    //poziv za provjeru jel ima friend requestova
    //#user-list-container
    checkFriendRequestsWithNotif(true);
});


$(document).on('click', '.accept-request', function (e) {
    e.preventDefault();

    alert('prihvati');

    var initiatorUsername = getLoggedInUsername();
    var usernameToAccept = this.id.replace('-request', '');

    var data = JSON.stringify({
        "initiatorUsername" : initiatorUsername,
        "recieverUsername" : usernameToAccept
    });

    var settings = {
        "async": true,
        "url": "/api/friend/accept",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json"
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings)
        .done(function (response) {
            toastr.info('', 'Zahtev prihvaćen!');
            $('#user-' + usernameToAccept).remove();
        })
        .fail(function() {
            toastr.info('', 'Greska!');
        });

});


$(document).on('click', '.decline-request', function (e) {
    e.preventDefault();

    var initiatorUsername = getLoggedInUsername();
    var usernameToDecline = this.id.replace('-request', '');

    var data = JSON.stringify({
        "initiatorUsername" : initiatorUsername,
        "recieverUsername" : usernameToDecline
    });

    var settings = {
        "async": true,
        "url": "/api/friend/decline",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json"
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings)
        .done(function (response) {
            toastr.info('', 'Zahtev odbijen!');
            $('#user-' + usernameToDecline).remove();
        })
        .fail(function() {
            toastr.info('', 'Greska!');
        });
});

$(document).on('click', '.add-link', function (e) {
    e.preventDefault();

    var initiator = getLoggedInUsername();
    var usernameToAdd = this.id.replace('-id', '');

    var data = JSON.stringify({
        "initiatorUsername" : initiator,
        "recieverUsername" : usernameToAdd
    });

    var settings = {
        "async": true,
        "url": "/api/friend/add",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json"
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings).done(function (response) {
        toastr.info('', 'Zahtev poslat!');
        $('#user-' + usernameToAdd).remove();
    });
});

$(document).on('click', '.remove-link', function (e) {
    e.preventDefault();

    var usernameToRemove = this.id.replace('-id', '');

    var initiator = getLoggedInUsername();
    var usernameToRemove = this.id.replace('-id', '');

    var data = JSON.stringify({
        "initiatorUsername" : initiator,
        "recieverUsername" : usernameToRemove
    });

    var settings = {
        "async": true,
        "url": "/api/friend/delete",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json"
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings).done(function (response) {
        toastr.info('', 'Prijatelj uklonjen!');
        $('#user-' + usernameToRemove).remove();
    });
});

function checkFriendRequestsWithNotif(notif) {
    var user = getLoggedInUsername();
    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "/api/friends/getFriendRequests/" + user,
        "method": "GET",
        "headers": {
            "X-Auth-Token": window.sessionStorage.accessToken,
            "Cache-Control": "no-cache",
        }
    }

    $.ajax(settings)
        .done(function (data) {
            if($.trim(data)) {
                var num = data.length;
                if (notif) {
                    notify('Obaveštenje!', 'Imate ' + num + ' novih zahteva za prijateljstvo', data);
                } else {
                    listFriendRequests(data);
                }
            }
        });
}

function notify(title, text, data) {
    toastr.options = {
        "closeButton": true,
        "positionClass": "toast-bottom-right",
        "onclick" : function() { listFriendRequests(data) }
    }
    toastr.info(text, title);
}

function listFriendRequests(users) {
    var container = $('#user-list-container');
    container.empty();
    if (!users.length) {
        toastr.info('', 'Nema novih zahteva!');
    } else {
        $.each(users, function (i, user) {
            var html = getRequestHtml(user);
            container.append($(html));
        });
    }
}
function getRequestHtml(user) {
    var name = user.firstname + ' ' + user.lastname;
    var linkId = user.username + '-request';     //username-request
    var html = '<div id="user-' + user.username + '" class="shadow">\n' +
        '<div class="col-sm-12">\n' +
        '<div class="col-sm-2">\n' +
        '<img src="' + getIdenticonUrl(user.username)+ '" class="img-circle width="60px">\n' +
        '</div>\n' +
        '<div class="col-sm-7">\n' +
        '<h4><a href="#">' + name + '</a></h4>\n' +
        '</div>\n' +
        '<div class="col-sm-3">\n' +
        '<br>\n' +
        '<a id="'+ linkId +'" class="accept-request" href="#">Prihvati</a> | \n' +
        '<a id="'+ linkId +'" class="decline-request" href="#">Odbij</a>\n' +
        '</div>\n' +
        '</div>\n' +
        '<div class="clearfix"></div>\n' +
        '<hr/>\n' +
        '</div>';
    return html;
}