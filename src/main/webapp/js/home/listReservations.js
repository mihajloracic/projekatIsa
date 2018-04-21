function showReservationList(appendTo) {

}

function appendReservationTo(cont, reservations) {
    $.each(reservations, function(i, reservation) {
        var html = getReservationHtml(venue);
        cont.append($(html));
    });
}

function getReservationHtml(venue) {
    return '<hr/>\n' +
        '<div class="shadow">\n' +
        '<div class="col-sm-8">\n' +
        '<h4><a href="#">' + venue.name + '</a></h4>\n' +
        '</div>\n' +
        '<div class="clearfix"></div>\n' +
        '</div>';
}