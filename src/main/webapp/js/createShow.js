window.onload = function () {

    authorities = getUserAuthorities();
    if(!authorities.includes("VENUEADMIN")){
        window.location = "/";

    }

    $(document).on('click', '#submitDetails', function (e) {
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
            "genre": genre,
            "director": director,
            "length": length,
        }
        $.ajax({
            "url": "/api/show/add"
            , "method": "POST"
            , contentType: 'application/json'
            , dataType: 'json'
            , data: JSON.stringify(show)
            , success: function (result) {
            }
        });

    });

};