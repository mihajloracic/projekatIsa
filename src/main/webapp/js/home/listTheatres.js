var mapT = { 0 : "theatresByName", 1 : "theatresByCity", 2 : "theatres" }

function showTheatreList(appendTo, key) {
    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "/api/venues/" + mapT[key],
        "method": "GET",
        "headers": {
            "Cache-Control": "no-cache"
        }
    }

    $.ajax(settings).done(function (response) {
        appendVenueTo(appendTo, response);
    });
}
