window.onload = function () {

    var id = getUrlParameter("id");

    $.ajax({
        "url": "/api/event/" + id
        , "method": "GET"
        , success: function (result) {
            //alert(result.name + result.description)
            $("#jumbotronName")[0].innerHTML  = result.show.name;
            $("#jumbotronDate")[0].innerHTML =  result.date + " " + result.time;
            $("#date")[0].value = result.date;
            $("#time")[0].value = result.time;
            $("#price")[0].value = result.price;
            $("#nameTextarea")[0].value = result.show.name;
            $("#actors")[0].value = result.show.actors;
            $("#description")[0].value = result.show.description;
            $("#director")[0].value = result.show.director;
            $("#length")[0].value = result.show.length;
        }

    });


    $(document).on('click', '#submitDiscount', function (e) {
        e.preventDefault();
        var price = $("#newPrice").val();
        if(price=="")
            return;
        var data = {
            "price" : price,
            "eventId" : id
        }
        $.ajax({
            "url": "/api/event/discount/"
            , "method": "POST"
            , contentType: 'application/json'
            , dataType: 'json'
            , data: JSON.stringify(data)
            , success: function (result) {
                window.location.reload();
            }
        });


    });

    $(document).on('click', '#submitDetails', function (e)  {
        e.preventDefault();
        date = $("#date")[0].value;
        time = $("#time")[0].value+":00";
        price = $("#price")[0].value;
        var event = {
            "date": date,
            "time": time,
            "price": price
        }

        var id = getUrlParameter("id");
        $.ajax({
            "url": "/api/event/update/" + id
            , "method": "POST"
            , contentType: 'application/json'
            , dataType: 'json'
            , data: JSON.stringify(event)
            , success: function (result) {

                window.location.reload();

            }
        });

        var id = getUrlParameter("id");
        $.ajax({
            "url": "/api/event/update/" + id
            , "method": "POST"
            , contentType: 'application/json'
            , dataType: 'json'
            , data: JSON.stringify(event)
            , success: function (result) {

                window.location.reload();

            }
        });

        name = $("#nameTextarea")[0].value;
        description = $("#description")[0].value;
        actors = $("#actors")[0].value;
        genre = $("#genre")[0].value;
        director = $("#director")[0].value;
        length = $("#length")[0].value;

        var show = {
            "name": name,
            "description": description,
            "actors": actors,
            "genre" : genre,
            "director" : director,
            "length" : length,
        }
        $.ajax({
            "url": "/api/show/updateByProjectionId/" + id
            , "method": "POST"
            , contentType: 'application/json'
            , dataType: 'json'
            , data: JSON.stringify(show)
            , success: function (result) {
                window.location.reload();
            }
        });
    });
};