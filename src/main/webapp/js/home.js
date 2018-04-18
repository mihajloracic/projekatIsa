
$(document).ready(function () {

    var settings = {
        "async": true,
        "url": "/api/venues",
        "method": "GET",
        success: function(data){

            $.each(data, function(index, itemData) {
                element = $( ".list-group" )[0];
                element.innerHTML += " <a href=\"venue.html?id="+itemData.id+"\" class=\"list-group-item list-group-item-action\"><h2>"+itemData.name+"<br/></h2>"+itemData.description+"</a>";
            });
        }
    }

    $.ajax(settings)
        .fail(function (xhr, status, code) {
            alert('Link ne postoji!');
        });

});