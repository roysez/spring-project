/**
 * Created by roysez on 05.05.2017.
 */

    $(document).ready(function ()
    {
        $(".backup-picture").on("error", function(){
            console.log('Test');
            $(this).attr('src',
                'https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQ6afIs-1S3GXUawbA_UeVHFwO8niO_4O7iEll3Uh8o_FNejgiC');
        });

    });


    function deleteUser(username) {
        $.ajax({
            type: "DELETE",
            url: '/users/'+username,

            "statusCode": {
                401: function () {
                    $('.main').html('User with username: ' + username + ', wasn\'t  deleted');
                    $('.main').addClass('alert alert-danger');
                },
                404: function () {
                    $('.main').html('User not found');
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
        $('.edit-off').css('display','none');
        $('.edit-on').css('display','block');
        $('tr:last-child>td.edit-on').css('display','inline-block');
        $('tr>td:first-child.edit-o').html('User profile photo:');
        $('button.edit-on').css('display','inline-block');

    }

    function cancelEdit() {
        $('.edit-off').css('display','inline-block');
        $('.edit-on').css('display','none');
        $('tr>td:first-child.edit-o').html('');
    }


    function submitEditUser(username){
        var newUsername = $('#username').val();
        var newFirstName = $('#firstName').val();
        var newLastName = $('#lastName').val();
        var newEmail = $('#email').val();

        var user = {
            'ssoId' : newUsername,
            'firstName' : newFirstName,
            'lastName' : newLastName,
            'email' : newEmail
        };

        function setNewFields() {
            $('#origin-username').html(newUsername);
            $('#origin-firstName').html(newFirstName);
            $('#origin-lastName').html(newLastName);
            $('#origin-email').html(newEmail);
        }

        $.ajax({
            type: "PUT",
            url: '/users/'+username,
            data: JSON.stringify(user),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType: 'json',
            "statusCode": {
                401: function () {
                    cancelEdit();
                    $('.alert-block').html('User with username: ' + username + ', can\'t be edited');
                    $('.alert-block').addClass('alert alert-danger');
                },
                404: function () {
                    cancelEdit();
                    $('.alert-block').html('User not found');
                    $('.alert-block').addClass('alert alert-danger');
                },
                200: function () {
                    //cancelEdit();

                    window.location.replace("/users/"+newUsername);
                    setTimeout(function(){
                        //do what you need here
                        $('.alert-block').html('Profile has been successfully edited');
                        $('.alert-block').addClass('alert alert-success');
                    }, 5000);

                    // setNewFields();
                }
            }

        });

    }

