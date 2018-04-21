window.onload = function () {
    var id = getUrlParameter("id");

    $("#editButton").click (function(){
        window.location = "/api/changeVenue.html?id=" + id;
    })

    $.ajax({"url": "/api/venues/"+id
        , "method": "GET"
        , success: function(result){
            //alert(result.name + result.description)
            $("#name")[0].innerHTML = result.name;
            $("#description")[0].innerHTML = result.description;

            var mapOptions = {
                zoom: 15,
                center: new google.maps.LatLng(result.lat, result.lng)
            };

            map = new google.maps.Map(document.getElementById('map'), mapOptions);

            var marker = new google.maps.Marker({
                map: map,
                position: {lat: result.lat, lng: result.lng}
            });
    }});

    authorities = getUserAuthorities();
    if(authorities.includes("ADMIN")){
        var elements = document.getElementsByClassName("ADMIN")

        for (var i = 0; i < elements.length; i++){
            elements[i].style.display = "block";
        }

    }

    $.ajax({
        "url": "/api/venues/"+id+"/discounts"
        , "method": "GET"
        , success: function (result) {

            var arrayLength = result.length;
            var str = "";
            for (var i = 0; i < arrayLength; i++) {
                str += "<div class=\"col-sm-5\">\n" +
                    "        <ul class=\"list-group\">\n" +
                    "        <li class=\"list-group-item\">" +
                    result[i].event.show.name + " " + result[i].event.time + " " + result[i].event.date +
                    "</li>\n" +
                    "        </ul>\n" +
                    "        </div>\n" +
                    "        <div class=\"col-sm-1\">\n" +
                    "        <button type=\"button\"" +
                    "id="+result[i].id+
                    " class=\"reserveDiscount btn btn-success\" >Rezerviši</button>\n" +
                    "</div>"
                //Do something
            }
            $("#discounts")[0].innerHTML = str;

        }
    });

    $(document).on('click', '.reserveDiscount', function (e)  {
        e.preventDefault();

        alert(event.target.id)


        var data = {
            "discountEventId" : event.target.id,
            "username" : getLoggedInUsername()
        }

        $.ajax({
            "url": "/api/reservation/discountReservation"
            , "method": "POST"
            , contentType: 'application/json;'
            , dataType: 'json'
            , data: JSON.stringify(data)
            , success: function (result) {


            }
        });
    });

};



