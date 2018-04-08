
$(document).on('change', '#d-date', function (e) {
    var currentDate = $('#d-date').val();
    $('#venue-list-container').empty();
    var events = localStorage.getItem("eventsOfShow");
    if (events == null) {
        alert('Neka greska!');
    }
    showHallsAndTime(events, currentDate);
});

//reserve-seats
$(document).on('click', '.reserve-seats', function (e) {
    e.preventDefault();

    var eventId = this.id.replace('event-', '');
    alert('event id' + eventId);

    localStorage.removeItem("eventsOfShow");
    localStorage.setItem("eventID", eventId);
    location = "/api/seatPicker.html";
});


$(document).on('click', '.show-events', function (e) {
    e.preventDefault();

    var id = this.id;

    var parts = this.id.split(':');
    var showId = parts[0].replace('show-', '');
    var venueId = parts[1].replace('venue-', '');

    var data = JSON.stringify({
        "venueId" : venueId,
        "showId" : showId
    });

    var settings = {
        "async": true,
        "url": "http://localhost:8096/api/event/findByVenueShow",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json"
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings)
        .done(function (response) {
            var dates = response.map(function (e, idx) {
                var date = new Date(e.date);
                var ret = $.datepicker.formatDate( 'yy-mm-dd', date);
                return ret;
            });

            localStorage.setItem("eventsOfShow", JSON.stringify(response));

            $('#venue-list-container').empty();
            showDateHtml(dates, response);
        });
});


$(document).on('click', '.load-events', function (e) {
    e.preventDefault();
    $('#venue-list-container').empty();
    $('#searchbox-container').empty();
    var venueId = this.id.replace('venue-', '');

    var data = JSON.stringify({
        "id" : venueId
    });

    var settings = {
        "async": true,
        "url": "http://localhost:8096/api/event/findByVenueDistinct",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json"
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings)
        .done(function (response) {
            appendShowList($('#venue-list-container'), response);
        });
});

//"2018-02-14"
//NAKON KLIKA, DATUM IZABRAN
function showDateHtml(datesReturned, events) {
    var dateHtml =
        '<div>\n' +
        '<input type="text" name="date" id="d-date" placeholder="Izaberite datum">\n' +
        '</div>';

    $('#date-container').append($(dateHtml));

    $("#d-date").datepicker({
        beforeShowDay : function(date) {
            var string = $.datepicker.formatDate('yy-mm-dd', date);
            return [ !(datesReturned.indexOf(string) == -1)]
        }
    });
}

function getValidEventsByDate(events, currDate) {
    // var pickedDate = $('#d-date').val();
    events = JSON.parse(events);

    if(currDate == "") {
        return [];
    }
    var pickedDateF = $.datepicker.formatDate('yy-mm-dd', new Date(currDate));
    var ret = []

    $.each(events, function (i, event) {
        var eDate = $.datepicker.formatDate('yy-mm-dd', new Date(event.date));
        if (eDate == pickedDateF) {
            ret.push(event);
        }
    });

    return ret;
}

//uzmi datum i prikazi vremena i sale
function showHallsAndTime(events, currDate) {
    var container = $('#venue-list-container');
    var eventsOnDate = getValidEventsByDate(events, currDate);
    // $('venue-list-container')
    $.each(eventsOnDate, function(i, event) {
        var html = getHallTimeHtml(event);
        container.append($(html));
    });
}

function getHallTimeHtml(event) {
    var hallsHtml =
        '<hr>' +
        '<div class="card w-75">\n' +
        '<div class="card-body">\n' +
        '<h5 class="card-title"> ' + event.hall.name + '</h5>\n' +
        '<p class="card-text">Vreme projekcije: '+ event.time +'</p>\n' +
        '<p class="card-text">Cena: </p>\n' +
        '<a href="#" id="event-'+  event.id +'" class="btn btn-primary reserve-seats">Rezervišite mesta</a>\n' +
        '</div>\n' +
        '</div>';
    return hallsHtml;
}


function appendShowList(containter, events) {
    $.each(events, function(i, event) {
        var html = getShowHtml(event);
        containter.append($(html));
    });
}

function getShowHtml(e) {
    var idVal = 'show-' + e.show.id + ':venue-' + e.venue.id;
    var html =
        '<hr>' +
        '<div class="shadow">\n' +
        '<div class="col-sm-12">\n' +
        '<div class="col-sm-8">\n' +
        '<h4><a href="#">' + e.show.name + '</a></h4>\n' +
        '</div>\n' +
        '<div class="col-sm-4">\n' +
        '<br>\n' +
        '<a id="' + idVal + '" class="show-events" href="#">Vidi datume</a>\n' +
        // '<input type="text" name="date" id="show-date-" class="date-class" placeholder="izaberite datum">' +
        '</div>\n' +
        '</div>\n' +
        '<div class="clearfix"></div>\n' +
        '</div>';
    return html;
}