/**
 * Created by roysez on 12.05.2017.
 */

   function showArticleForm(){
       if($('.article-form').css('display')=='none') {
           $('.article-form').css('display', 'inline');
       } else $('.article-form').css('display','none');
}

$(function () {
    $('a[href="#search"]').on('click', function(event) {
        event.preventDefault();
        $('#search').addClass('open');
        $('#search > form > input[type="search"]').focus();
    });

    $('#search, #search button.close').on('click keyup', function(event) {
        if (event.target == this || event.target.className == 'close' || event.keyCode == 27) {
            $(this).removeClass('open');
        }
    });


    //Do not include! This prevents the form from submitting for DEMO purposes only!

});