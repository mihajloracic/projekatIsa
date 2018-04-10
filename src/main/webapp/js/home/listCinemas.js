var mapC = { 0 : "cinemasByName", 1 : "cinemasByCity", 2 : "cinemas" }


function showCinemaList(appendTo, key) {
    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "http://localhost:8096/api/venues/" + mapC[key],
        "method": "GET",
        "headers": {
            "Cache-Control": "no-cache"
        }
    }

    $.ajax(settings).done(function (response) {
        appendVenueTo(appendTo, response);
    });
}

function appendVenueTo(cont, venues) {
    $.each(venues, function(i, venue) {
        var html = getVenueHtml(venue);
        cont.append($(html));
    });
}

function getVenueHtml(venue) {
    return '<hr/>\n' +
        '<div class="shadow">\n' +
        '<div class="col-sm-8">\n' +
        '<h4><a href="#">' + venue.name + '</a></h4>\n' +
        '</div>\n' +
        '<div class="clearfix"></div>\n' +
        '</div>';
}