$(document).ready(function() {
    $('img.animated').hover(
        function() {
            $(this).addClass('swing');
        },
        function() {
            $(this).removeClass('swing');
        }
    )})