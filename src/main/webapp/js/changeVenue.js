window.onload = function () {

    // authorities = getUserAuthorities();
    // if(!authorities.includes("ADMIN")){
    //     var elements = document.getElementsByClassName("ADMIN")
    //
    //     for (var i = 0; i < elements.length; i++){
    //         elements[i].style.display = "block";
    //     }
    //
    // }

    //INICIJALIZACIJA JUMBOTRONA I MAPE
    var id = getUrlParameter("id");
    var marker;

    $.ajax({
        "url": "/api/venues/" + id
        , "method": "GET"
        , success: function (result) {
            //alert(result.name + result.description)
            $("#name")[0].innerHTML = $("#nameTextarea")[0].innerHTML = result.name;
            $("#description")[0].innerHTML = $("#descriptionTextArea")[0].innerHTML = result.description;
            $("#addressTextArea")[0].innerHTML = result.address;


            var mapOptions = {
                zoom: 15,
                center: new google.maps.LatLng(result.lat, result.lng)
            };

            map = new google.maps.Map(document.getElementById('map'), mapOptions);

            marker = new google.maps.Marker({
                map: map,
                position: {lat: result.lat, lng: result.lng},
                draggable: true
            });
        }
    });

    //SALE
    $.ajax({
        "url": "/api/venues/" + id + "/halls"
        , "method": "GET"
        , success: function (result) {

            var arrayLength = result.length;
            var str = "";
            for (var i = 0; i < arrayLength; i++) {
                str += "<div class=\"col-sm-5\">\n" +
                    "        <ul class=\"list-group\">\n" +
                    "        <li class=\"list-group-item\">" +
                    result[i].name +
                    "</li>\n" +
                    "        </ul>\n" +
                    "        </div>\n" +
                    "        <div class=\"col-sm-1\">\n" +
                    "        <button type=\"button\"" +
                    "id=" + result[i].id +
                    " data-toggle=\"modal\" onclick=\"open_modal(" + result[i].id + ")\"" + "    \" " +
                    " class=\"changeHall btn btn-warning\">Izmeni</button>\n" +
                    "        </div>"
                a = result[i];
                //Do something
            }
            $("#halls")[0].innerHTML = str;

        }
    });

    //PROJEKCIJE
    $.ajax({
        "url": "/api/event/venue/" + id
        , "method": "GET"
        , success: function (result) {

            var arrayLength = result.length;
            var str = "";
            for (var i = 0; i < arrayLength; i++) {
                str += "<div class=\"col-sm-5\">\n" +
                    "        <ul class=\"list-group\">\n" +
                    "        <li class=\"list-group-item\">" +
                    result[i].show.name + " " + result[i].time + " " + result[i].date +
                    "</li>\n" +
                    "        </ul>\n" +
                    "        </div>\n" +
                    "        <div class=\"col-sm-1\">\n" +
                    "        <button type=\"button\"" +
                    "id=" + result[i].id +
                    " onclick=\"window.location.href='/api/changeProjection.html?id=" + result[i].id + "'\"" +
                    " data-toggle=\"modal\" class=\"changeProjection btn btn-warning\" >Izmeni</button>\n" +
                    "        </div>"
                a = result[i];
                //Do something
            }
            $("#projection")[0].innerHTML = str;

        }
    });


    $("#addProjectionButton").click(function (e) {
         window.location = "createProjection.html?venueId="+id;
    });
    $("#submitVenueDetails").click(function (e) {
        e.preventDefault();
        name = $("#nameTextarea")[0].value;
        description = $("#descriptionTextArea")[0].value;
        address = $("#addressTextArea")[0].value;
        lat = marker.getPosition().lat();
        lng = marker.getPosition().lng();
        var venue = {
            "name": name,
            "description": description,
            "address": address,
            "lat": lat,
            "lng": lng
        }

        var id = getUrlParameter("id");
        $.ajax({
            "url": "/api/venues/update/" + id
            , "method": "POST"
            , contentType: 'application/json; charset=utf-8'
            , dataType: 'json'
            , data: JSON.stringify(venue)
            , success: function (result) {

                window.location = "/api/venue.html?id=" + id;

            }
        });
    });

    $(document).on('click', '#changeSeats', function (e) {
        e.preventDefault();

        var hall = {
            "nRows": $("#nRows")[0].value,
            "nCols": $("#nCols")[0].value
        }

        $.ajax({
            "url": "/api/venues/hall/update/" + selectedHall
            , "method": "POST"
            , contentType: 'application/json; charset=utf-8'
            , dataType: 'json'
            , data: JSON.stringify(hall)
            , success: function (result) {


            }
        });
    });


};
var selectedHall;

function open_modal(hallId) {
    selectedHall = hallId;

    $.ajax({
        "url": "/api/venues/hall/" + hallId
        , "method": "GET"
        , success: function (result) {

            $("#nRows")[0].value = result.nRows;
            $("#nCols")[0].value = result.nCols;

        }
    });
    $("#seatModal").modal("show");
}


