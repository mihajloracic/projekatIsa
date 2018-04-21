var sortParamVenues = ["Naziv", "Grad"];
var sortParamFriends = ["Ime", "Prezime"];

$(document).on('click', '.list-link', function (e) {
    e.preventDefault();

    var cb = $('#hide-cb');
    var comboboxParent = $('#ul-link');
    comboboxParent.empty();

    var titleEl = $('#list-container-name');
    var listType = this.id.replace('-link', '');

    var appendTo = $('#append-list-to');
    appendTo.empty();

    switch (listType) {
        case "theatres" :
            titleEl.text('Pozorišta');
            cb.attr('hidden', false);
            addComboboxParams(listType, comboboxParent);
            showTheatreList(appendTo, 2);
            break;
        case "cinemas" :
            titleEl.text('Bioskopi');
            cb.attr('hidden', false);
            addComboboxParams(listType, comboboxParent);
            showCinemaList(appendTo, 2);
            break;
        case "friends" :
            titleEl.text('Prijatelji');
            cb.attr('hidden', false);
            addComboboxParams(listType, comboboxParent);
            showFriendList(appendTo, 0);
            break;
        case "reservations" :
            titleEl.text('Rezervacije');
            cb.attr('hidden', true);
            showReservationList(appendTo);
            break;
        default :
            showTheatreList(appendTo, 2);
    }
});

function addComboboxParams(listType, comboboxParent) {
    var items;
    if (listType == "theatres" || listType == "cinemas") {
        items = sortParamVenues;
    } else {    //friend
        items = sortParamFriends;
    }
    $.each(items, function(i, item) {
        comboboxParent.append('<li><a id="' + listType + '-' + i +'" class="cb-param" href="#">'+ item +'</a></li>\n');
    });
}

$(document).on('click', '.cb-param', function (e) {
    e.preventDefault();

    var appendTo = $('#append-list-to');
    appendTo.empty();

    var parts = this.id.split('-');
    var type = parts[0];
    var param = parts[1];

    switch (type) {
        case "theatres" :
            showTheatreList(appendTo, param);
            break;
        case "cinemas" :
            showCinemaList(appendTo, param);
            break;
        case "friends" :
            showFriendList(appendTo, param);
            break;
        default: showTheatreList(appendTo, 2);
    }
});