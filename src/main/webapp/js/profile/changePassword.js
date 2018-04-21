$(document).on('click', '#submit', function(e) {
    e.preventDefault();

    var map = formFieldsToObject();
    var data = JSON.stringify(map);

    var settings = {
        "async": true,
        "url": "/api/",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json"
        },
        "processData": false,
        "data": data
    }

    $.ajax(settings)
        .done(function (response) {
        })
        .fail(function (xhr, status, error) {

        });
});

//sledeci put kad zatreba stavi u novi fajl pa dodaj da se id forme proslijedjuje
function formFieldsToObject() {
    var fields = {};
    $("#form-edit").find(":input").each(function() {
        // The selector will match buttons; if you want to filter
        // them out, check `this.tagName` and `this.type`; see
        // below
        fields[this.name] = $(this).val();
    });
    return fields;
}