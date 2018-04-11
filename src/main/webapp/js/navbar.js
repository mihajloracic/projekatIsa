$(document).ready(function () {
    var navbar = $('#navbar-id');
    navbar.append($(getNavbarHtml()));

    var loggedIn = getLoggedInUsername();
    if (loggedIn == "") {
        //set first-link da je registruj se, drugi prijavi se, hrefove isto setuj
        $('#home-link').attr('href', 'http://localhost:8096/api/index.html');
        $('#first-link').text('Registruj se');
        $('#first-link').attr('href', 'http://localhost:8096/api/register.html');
        $('#second-link').text('Prijavi se');
        $('#second-link').attr('href', 'http://localhost:8096/api/login.html');
        if ($('#second-span').hasClass('glyphicon-log-out')) {
            $('#second-span').removeClass('glyphicon-log-out');
            $('#second-span').addClass('glyphicon-log-in');
        }
    } else {
        //set first-link da je link za profil, drugi logout
        // if ($('#first-span').hasClass('')) {}
        $('#home-link').attr('href', 'http://localhost:8096/api/home.html');
        $('#first-link').text(loggedIn);
        $('#first-link').attr('href', 'http://localhost:8096/api/userProfile.html');
        $('#second-link').text('Odjavi se');
        $('#second-link').addClass('logout');
        if ($('#second-span').hasClass('glyphicon-log-in')) {
            $('#second-span').removeClass('glyphicon-log-in');
            $('#second-span').addClass('glyphicon-log-out');
        }
    }
});
$(document).on('click', '#second-link', function (e) {
    e.preventDefault();

    if ($('#second-link').hasClass('logout')) {
        sessionStorage.removeItem('accessToken');
        $('#second-link').removeClass('logout');
        window.location = 'http://localhost:8096/api/index.html';
    } else {
        window.location = 'http://localhost:8096/api/login.html';
    }
})


function getNavbarHtml() {
    var html = '<nav class="navbar navbar-inverse">' +
        '<div class="container-fluid">' +
        '<ul class="nav navbar-nav">\n' +
        '<li><a id="home-link" href="#">Početna</a></li>\n' +
        '</ul>\n' +
        '<ul class="nav navbar-nav navbar-right">' +
        '<li><a id="first-link" href="#"><span id="first-span" class="glyphicon glyphicon-user"></span></a></li>' +
        '<li><a id="second-link" href="#"><span id="second-span" class="glyphicon"></span></a></li>' +
        '</ul></div></nav>';
    return html;
}