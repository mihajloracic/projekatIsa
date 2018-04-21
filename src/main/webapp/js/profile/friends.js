$(document).on('click', '.user-search', function (e) {
    e.preventDefault();

    var mode = this.id.replace('-friend', '');
    showSearchBox(mode);
});

$(document).on('click', '#friend-requests', function (e) {
    e.preventDefault();

    checkFriendRequestsWithNotif(false);
});


$(document).on('click', '.search-button', function (e) {
    e.preventDefault();

    var mode = this.id.replace('search-button-', '');

    searchPeople(mode);
});


$(document).on('click', '#back-link', function (e) {
    e.preventDefault();
    $('#user-list-container').empty();

    restoreLinks();
});

function searchPeople(mode) {

    //if mode == 'add', izlistaj korisnike koji nisu prijatelji
    //if mode == 'find', izlistaj prijatelje

    var queryParam = $('#search-input').val();
    var username = getLoggedInUsername();
    var friends = (mode == 'find') ? true : false;

    var data = JSON.stringify({
        "username" : username,
        "queryParam" : queryParam,
        "friends" : friends
    });

    var settings = {
        "async": true,
        "url": "/api/friends/edit/add",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json"
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings).done(function (response) {
        appendUserList(mode, $('#user-list-container'), response);
    });
}


function showSearchBox(mode) {
    var addColumn = $('#add-friend');
    var findColumn = $('#find-friend');

    addColumn.attr('hidden', true);
    findColumn.attr('hidden', true);

    var searchHtmlStr = '' +
        '<form class="navbar-form navbar-right">\n' +
        '<div class="input-group">\n' +
        '<input type="text" id="search-input" class="form-control" placeholder="Pretraži">\n' +
        '<div class="input-group-btn">\n' +
        '<button id="search-button-'+ mode +'" class="btn btn-default search-button" type="submit">\n' +
        '<i class="glyphicon glyphicon-search"></i>\n' +
        '</button>\n' +
        '</div>\n' +
        '</div>\n' +
        '</form>';

    var backLinkStr = '<h4><a href="#" id="back-link">Nazad</a></h4>';

    var searchHtml = $(searchHtmlStr);
    var backHtml = $(backLinkStr);

    addColumn.parent().parent().append(searchHtml);
    findColumn.parent().parent().append(backHtml);

}

function restoreLinks() {
    var addColumn = $('#add-friend');
    var findColumn = $('#find-friend');

    addColumn.parent().parent().children().last().remove();
    findColumn.parent().parent().children().last().remove();

    addColumn.attr('hidden', false);
    findColumn.attr('hidden', false);
}

function appendUserList(mode, containter, users) {
    containter.empty();
    $.each(users, function(i, user) {
        var html = getUserHtml(mode, user);
        containter.append($(html));
    });
}

function getUserHtml(mode, user) {
    var name = user.firstname + ' ' + user.lastname;
    var linkClass = (mode == 'add') ? 'add-link' : 'remove-link';
    var linkId = user.username + '-id';     //username-id
    var action = (mode == 'add') ? 'Dodaj prijatelja' : 'Ukloni prijatelja';
    var html = '<div id="user-' + user.username + '" class="shadow">\n' +
        '<div class="col-sm-12">\n' +
        '<div class="col-sm-2">\n' +
        '<img src="' + getIdenticonUrl(user.username)+ '" class="img-circle width="60px">\n' +
        '</div>\n' +
        '<div class="col-sm-6">\n' +
        '<h4><a href="#">' + name + '</a></h4>\n' +
        '</div>\n' +
        '<div class="col-sm-4">\n' +
        '<br>\n' +
        '<a id="'+ linkId +'" class="' + linkClass + '" href="#">' + action + '</a>\n' +
        '</div>\n' +
        '</div>\n' +
        '<div class="clearfix"></div>\n' +
        '<hr/>\n' +
        '</div>';
    return html;
}