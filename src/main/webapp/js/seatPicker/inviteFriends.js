var username;
var invitation;

$(document).ready(function() {
    username = getLoggedInUsername();
    invitation = new Invitation(username);
});


$(document).on('click', '.invite-link', function(e) {
    e.preventDefault();

    var userToInv = this.id.replace('-invite-id', '');
    invitation.addFriendInv(userToInv);
    $('#user-invite-'+userToInv).remove();
    var invitedHtml = getUserHtmlInv(JSON.parse(localStorage.getItem(userToInv)), 'remove');
    $('#invited-container').append($(invitedHtml));
});


$(document).on('click', '.remove-link', function(e) {
    e.preventDefault();

    var userToRemove = this.id.replace('-remove-id', '');
    invitation.removeFriendInv(userToRemove);
    $('#user-remove-'+userToRemove).remove();
    var inviteHtml = getUserHtmlInv(JSON.parse(localStorage.getItem(userToRemove)), 'invite');
    $('#user-list-container').append($(inviteHtml));
});


$(document).on('click', '#search-button', function(e) {
    e.preventDefault();

    var queryParam = $('#search-input').val();
    showFriendsInvite(queryParam);
});

//invite-link
function showFriendsInvite(queryParam) {
    var data = JSON.stringify({
        "username" : username,
        "queryParam" : queryParam,
        "friends" : true
    });

    var settings = {
        "async": false,
        "url": "http://localhost:8096/api/friends/edit/add",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json"
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings).done(function (response) {
        appendUserListInvite($('#user-list-container'), response);
    });
}

function saveToLS(user) {
    var obj = {
        "username" : user.username,
        "firstname" : user.firstname,
        "lastname" : user.lastname
    }
    localStorage.setItem(user.username, JSON.stringify(obj));
}

function appendUserListInvite(containter, users) {
    containter.empty();
    $.each(users, function(i, user) {
        saveToLS(user);
        var uname = user.username;
        if(!invitation.containsFriend(uname)) {
            var html = getUserHtmlInv(user, 'invite');
            containter.append($(html));
        }
    });
}

function getUserHtmlInv(user, mode) {
    var name = user.firstname + ' ' + user.lastname;
    var linkClass = mode +'-link';
    var linkId = user.username + '-'+ mode +'-id';     //username-id
    var text = (mode == 'invite') ? "Pozovi" : "Ukloni";
    var html = '<div id="user-' + mode + '-' + user.username + '" class="shadow">\n' +
        '<div class="col-sm-12">\n' +
        '<div class="col-sm-2">\n' +
        '<img src="' + getIdenticonUrlInv(user.username, '64') + '" class="img-circle width="60px">\n' +
        '</div>\n' +
        '<div class="col-sm-6">\n' +
        '<h4><a href="#">' + name + '</a></h4>\n' +
        '</div>\n' +
        '<div class="col-sm-4">\n' +
        '<br>\n' +
        '<a id="' + linkId + '" class="' + linkClass + '" href="#">' + text+ '</a>\n' +
        '</div>\n' +
        '</div>\n' +
        '<div class="clearfix"></div>\n' +
        '<hr/>\n' +
        '</div>';
    return html;
}

function getIdenticonUrlInv(username, size) {
    return "http://identicon.org/?t=" + username + "&s="+size;
}

$(document).on('click', '#seat-res-btn', function(e) {
    e.preventDefault();
    var data = getReservationData();
    // sacuvaj rezervaciju u ls
    localStorage.setItem("reservationDetails", JSON.stringify(data));
    window.location = '/api/invite.html';
});

//finish-res
$(document).on('click', '#finish-res', function(e) {
    e.preventDefault();

    var resData = JSON.parse(localStorage.getItem("reservationDetails"));
    localStorage.removeItem("reservationDetails");
    resData["friends"] = invitation.getInvitedFriends();

    var settings = {
        "async": false,
        "url": "http://localhost:8096/api/reservation/addReservation",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json"
        },
        "processData": false,
        "data": JSON.stringify(resData)
    }

    $.ajax(settings)
        .done(function (response) {
            alert('Uspijesna rezervacija!');
            window.location = '/api/index.html';
        })
        .fail(function (xhr, status, code) {
            alert('Neki fail!');
        });
    //send res data to server
});

//vraca objekat
function getReservationData() {
    var seatMap = reservation.getSeats();
    var seatList = [];

    seatMap.forEach(function (value, key, map) {
        var obj = {
            "row": value[1] + 1,
            "col": value[0] + 1
        };
        seatList.push(obj);
    });

    var data = {
        "eventId": reservation.getEventId(),
        "username": reservation.getUsername(),
        "seats": seatList
    };

    return data;
}