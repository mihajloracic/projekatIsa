$(document).on('click', '.find-venue', function (e) {
    e.preventDefault();
    $('#venue-list-container').empty();
    $('#date-container').empty();
    $('#searchbox-container').empty();
    var venueType = this.id.replace('find-', '');

    addSearchBox(venueType);
    findVenues(venueType);
});

$(document).on('click', '#back-link', function (e) {
    e.preventDefault();
    $('#searchbox-container').empty();

    $('#venue-list-container').empty();

});

$(document).on('click', '.search-button', function (e) {
    e.preventDefault();

    $('#venue-list-container').empty();
    var venueType = this.id.replace('search-button-', '');  //theatre, cinema
    var type = (venueType == "cinema") ? "CINEMA" : "THEATRE";
    var searchParam = $('#search-input').val();

    var data = JSON.stringify({
        "venueType" : type,
        "name" : searchParam
    });

    var settings = {
        "async": true,
        "url": "/api/venues/getByTypeAndName",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json"
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings)
        .done(function (response) {
            appendVenueList($('#venue-list-container'), response);
        });
});


function findVenues(venueType) {
    listAllVenues(venueType);
}


function listAllVenues(venueType) {

    var searchParam = (venueType.toLowerCase() == "cinema") ? "cinemas" : "theatres";

    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "/api/venues/" + searchParam,
        "method": "GET",
        "headers": {
            "Cache-Control": "no-cache"
        }
    }

    $.ajax(settings).done(function (response) {
        appendVenueList($('#venue-list-container'), response);
    });
}

function appendVenueList(containter, venues) {
    $.each(venues, function(i, venue) {
        var html = getVenueHtml(venue);
        containter.append($(html));
    });
}

function getVenueHtml(venue) {
    return '<hr/>\n' +
        '<div class="shadow">\n' +
        '<div class="col-sm-12">\n' +
        '<div class="col-sm-8">\n' +
        '<h4><a href="#">' + venue.name + '</a></h4>\n' +
        '</div>\n' +
        '<div class="col-sm-4">\n' +
        '<br>\n' +
        '<a id="venue-' + venue.id + '" class="load-events" href="#">Pregledaj događaje</a>\n' +
        '</div>\n' +
        '</div>\n' +
        '<div class="clearfix"></div>\n' +
        '</div>';
}

function addSearchBox(venueType) {
    var appendTo = $('#searchbox-container');
    var vt = (venueType == "cinema") ? "bioskope" : "pozorišta";
    var row = $('<div class="row">' +
        '<div class="col-sm-12">' +
        '<div id="search-box" class="col-sm-6"></div>\n' +
        '<div id="back-link" class="col-sm-6"></div>\n' +
        '</div>' +
        '</div>');
    appendTo.append(row);
    var searchHtmlStr = '' +
        '<form class="navbar-form navbar-right">\n' +
        '<div class="input-group">\n' +
        '<input type="text" id="search-input" class="form-control" placeholder="Pretraži '+ vt +'">\n' +
        '<div class="input-group-btn">\n' +
        '<button id="search-button-'+ venueType +'" class="btn btn-default search-button" type="submit">\n' +
        '<i class="glyphicon glyphicon-search"></i>\n' +
        '</button>\n' +
        '</div>\n' +
        '</div>\n' +
        '</form>';

    var backLinkStr = '<h4><a id="back-action" href="#" id="back-link">Nazad</a></h4>';

    var searchHtml = $(searchHtmlStr);
    var backHtml = $(backLinkStr);
    $('#search-box').append(searchHtml);
    $('#back-link').append(backHtml);
}

