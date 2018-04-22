$(document).ready(function(){

    var id = getUrlParameter("venueId");

    $.ajax({
        "url": "/api/venues/" + id + "/halls"
        , "method": "GET"
        , success: function (result) {

            var arrayLength = result.length;
            var str = "";
            for (var i = 0; i < arrayLength; i++) {
                str+=" <option id=\" " +result[i].id+ "\" >" + result[i].name + "</option>"
            }
            $("#hall")[0].innerHTML = str;

        }
    });

    $.ajax({
        "url": "/api/show/"
        , "method": "GET"
        , success: function (result) {

            var arrayLength = result.length;
            var str = "";
            for (var i = 0; i < arrayLength; i++) {
                str+=" <option id=\" " +result[i].id+ "\" >" + result[i].name + "</option>"
            }
            $("#show")[0].innerHTML = str;

        }
    });


    $(document).on('click', '#createProjection', function (e) {

        date = $("#date")[0].value;
        time = $("#time")[0].value+":00";
        price = $("#price")[0].value;
        e = document.getElementById("show");
        var showId = e.options[e.selectedIndex].id;
        var e = document.getElementById("hall");
        var hallId = e.options[e.selectedIndex].id;

        var event = {
            "date": date,
            "time": time,
            "price": price,
            "hallId": hallId,
            "showId" : showId
        }
        $.ajax({
            "url": "/api/event/"
            , "method": "POST"
            , contentType: 'application/json'
            , dataType: 'json'
            , data: JSON.stringify(event)
            , success: function (result) {

                window.location.reload();

            }
        });

    });

});