var mapF = { 0 : "getFriendsOrderedByFirstname", 1 : "getFriendsOrderedByLastname" }


function showFriendList(appendTo, key) {

    var loggedIn = getLoggedInUsername();

    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "/api/friends/" + mapF[key] + "/" + loggedIn,
        "method": "GET",
        "headers": {
            "Cache-Control": "no-cache"
        }
    }

    $.ajax(settings).done(function (response) {
        appendFriendTo(appendTo, response);
    });
}

function appendFriendTo(cont, friends) {
    $.each(friends, function(i, friend) {
        var html = getFriendHtml(friend);
        cont.append($(html));
    });
}

function getFriendHtml(user) {
    var name = user.firstname + ' ' + user.lastname;
    var linkClass = 'remove-link';
    var linkId = user.username + '-id';     //username-id
    var action = 'Ukloni prijatelja';
    return '<div id="user-' + user.username + '" class="shadow">\n' +
        '<div class="col-sm-12">\n' +
        '<div class="col-sm-2">\n' +
        '<img src="' + getIdenticonUrl(user.username) + '" class="img-circle width="60px">\n' +
        '</div>\n' +
        '<div class="col-sm-6">\n' +
        '<h4><a href="#">' + name + '</a></h4>\n' +
        '</div>\n' +
        '<div class="col-sm-4">\n' +
        '<br>\n' +
        '<a id="' + linkId + '" class="' + linkClass + '" href="#">' + action + '</a>\n' +
        '</div>\n' +
        '</div>\n' +
        '<div class="clearfix"></div>\n' +
        '<hr/>\n' +
        '</div>';
}