/**
 * Created by roysez on 05.05.2017.
 */


    function deleteUser(username) {
        $.ajax({
            type: "DELETE",
            url: '/users/'+username,

            "statusCode": {
                403: function () {
                    $('.main').html('User with username: ' + username + ', wasn\'t  deleted');
                    $('.main').addClass('alert alert-danger');
                },

                200: function () {
                    $('.main').html('User with username: ' + username + ', was successful deleted');
                    $('.main').addClass('alert alert-success');
                }
            }
        });

    }


    function editUser() {
        // console.log($('#firstName').val());
        // console.log($('#origin-username').text());
        $('.edit-off').css('display','none');
        $('.edit-on').css('display','block');
        $('button.edit-on').css('display','inline-block');
    }

    function cancelEdit() {
        $('.edit-off').css('display','inline-block');
        $('.edit-on').css('display','none');

    }

