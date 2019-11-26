$(document).ready(function ()
{
    $('#sidebarCollapse').on('click', function () {
        $('.modulos').toggleClass('d-block');
        $(this).toggleClass('d-block');
    });
});