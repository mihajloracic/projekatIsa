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

    var id = getUrlParameter("id");
    var marker;
    $.ajax({"url": "/api/venues/"+id
        , "method": "GET"
        , success: function(result){
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
        }});



    $( "#submitVenueDetails" ).click(function(e) {
        e.preventDefault();
        name = $("#nameTextarea")[0].value;
        description = $("#descriptionTextArea")[0].value;
        address = $("#addressTextArea")[0].value;
        lat = marker.getPosition().lat();
        lng = marker.getPosition().lng();
        var venue = {
            "name" : name,
            "description" : description,
            "address" : address,
            "lat" : lat,
            "lng" : lng
        }

        var id = getUrlParameter("id");
        $.ajax({"url": "/api/venues/update/"+id
            , "method": "POST"
            ,contentType : 'application/json; charset=utf-8'
            ,dataType : 'json'
            ,data: JSON.stringify(venue)
            ,success: function(result){

                window.location = "/api/venue.html?id=" + id;

            }});
    });
};

