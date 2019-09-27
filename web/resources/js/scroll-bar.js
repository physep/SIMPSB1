$(window).scroll(function () {
    if ($("#nav").offset().top > 80) {
        $("#nav").addClass("color-scroll");
    } else {
        $("#nav").removeClass("color-scroll");
    }
});
