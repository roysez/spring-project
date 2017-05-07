/**
 * Created by roysez on 05.05.2017.
 */
$(document).ready(function () {

});

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
