var reservation;
var canvas;
rows = 1;
cols = 1;
w = 35;
var grid;


function markSeats(eventId) {

    var data = JSON.stringify({
        "id" : eventId
    });

    var settings = {
        "async": false,
        "url": "http://localhost:8096/api/reservationSeat/findByEvent",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json"
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings)
        .done(function (response) {
            disableReserved(response);
        });
}

function disableReserved(seats) {
    $.each(seats, function(i, seat){
        var r = seat.row - 1;
        var c = seat.col - 1;
        grid[c][r].reserved = true;
    });
}

function fillEventData(event) {
    $('#event-name').text(event.show.name);
    $('#event-name').after('<p class="card-text">' + event.date + '<br>' + event.time + ' </p>');
}

$(document).ready(function() {
    var eventId = localStorage.getItem("eventID");
    var loggedInUser = getLoggedInUsername();

    var event = getEvent(eventId);
    fillEventData(event);
    var hall = event.hall;

    rows = hall.nRows;
    cols = hall.nCols;

    reservation = new Reservation(eventId, loggedInUser);    //dodaj event ili sta vec
});


function getEvent(eventId) {

    var event;
    var data = JSON.stringify({
        "id" : eventId
    });

    var settings = {
        "async": false,
        "url": "http://localhost:8096/api/event/findById",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json"
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings)
        .done(function (response) {
            event = response;
        });
    return event;
}


function createArray(cols, rows) {
    var arr = new Array(cols);
    for(var i = 0; i < arr.length; i++) {
        arr[i] = new Array(rows);
    }
    return arr;
}

function initGrid() {
    for (var i = 0; i < cols; i++) {
        for (var j = 0; j < rows; j++) {
            grid[i][j] = new Cell(i * w, j * w, w, j, i);
        }
    }
    markSeats(localStorage.getItem("eventID"));
}

function setup() {
    canvas = createCanvas(cols * w + 1, rows * w + 1);
    canvas.parent('canvas-holder');
    grid = createArray(cols, rows);

    initGrid();
}

function showSeatDetails(col, row) {
    $('#seat-num').text('Izabrano sedišta: ' + (reservation.getReservedSeatNum()));
    $('#seat-row').text('Red: ' + row);
    $('#seat-col').text('Kolona: ' + col);
}

function updateButton() {
    if (reservation.getReservedSeatNum()) {
        if(!$('#add-btn').find("#seat-res-btn").length) {
            $('#add-btn').append('<a id="seat-res-btn" href="#" class="btn btn-primary">Rezerviši</a>\n');
        }
    } else {
        $('#seat-res-btn').remove();
    }
}

function mousePressed() {
    for (var i = 0; i < cols; i++) {
        for (var j = 0; j < rows; j++) {
            if (grid[i][j].containsPoint(mouseX, mouseY)) {
                var isBooked = grid[i][j].book();
                if (isBooked) {
                    reservation.addSeat(i, j);
                } else {
                    reservation.removeSeat(i, j);
                }
                updateButton();
                showSeatDetails(i+1, j+1);
            }

        }
    }   
}

function draw() {
    background(240);

    for (var i = 0; i < cols; i++) {
        for (var j = 0; j < rows; j++) {
          grid[i][j].show();
        }
    }
}
