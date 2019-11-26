AOS.init({
    easing: "ease-in-sine"
});

$('.dropdown-menu').on('click', function (e) {
    e.stopPropagation();
    console.log(`${e.target.textContent} clicado!`);
});
$(document).ready(function () {
    $(window).resize(function () {
        var bodyheight = $(this).height();
        $(".banner-alto").height(bodyheight * 1.0);
    }).resize();
});
$(document).ready(function ()
{
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
        $(this).toggleClass('active');
    });
});
$(window).scroll(function () {
    if ($("#nav").offset().top > 80) {
        $("#nav").addClass("color-scroll");
    } else {
        $("#nav").removeClass("color-scroll");
    }
});
