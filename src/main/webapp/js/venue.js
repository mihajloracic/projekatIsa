$(document).ready(function () {
    var id = getUrlParameter("id");

    $.ajax({"url": "/api/venues/"+id
        , "method": "GET"
        , success: function(result){
            //alert(result.name + result.description)
            $("#name")[0].innerHTML = result.name;
            $("#description")[0].innerHTML = result.description;
    }});


});

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};