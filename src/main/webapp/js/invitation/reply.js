var resId;

function populatePage(reservation) {
    $('#inv-title').text(reservation.event.show.name + ' od ' + reservation.reservationOwner.firstname);
    $('#inv-body').html('<hr>Datum : ' + reservation.event.date + '<br>Vreme : ' + reservation.event.time +'<hr>');

}

$(document).on('click', '#inv-accept', function(e) {
    e.preventDefault();

    alert('Prihvaeno!');
    window.location = "/api/";
});

$(document).on('click', '#inv-decline', function(e) {
    e.preventDefault();

    var data = JSON.stringify({
        "id": Number(resId)
    });

    var settings = {
        "async": true,
        "url": "http://localhost:8096/api/invitation/decline",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json"
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings)
        .done(function (response) {
            alert('Poziv odbijen');
            window.location = "/api/";
        })
        .fail(function (xhr, status, code) {
            alert('Greska!');
        });
});


$(document).ready(function () {

    var url = new URL(window.location.href);
    resId = url.searchParams.get("resId");

    var data = JSON.stringify({
        "id": Number(resId)
    });

    var settings = {
        "async": true,
        "url": "http://localhost:8096/api/reservation/findById",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json"
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings)
        .done(function (response) {
            populatePage(response);
        })
        .fail(function (xhr, status, code) {
            alert('Link ne postoji!');
        });
});