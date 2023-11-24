$(document).ready(function() {
    $('#LoginForm').submit(function (event) {
        event.preventDefault();

        const conUsername = $(`#username`).val()

        const userObject = {username : conUsername};

        $.ajax({
            type: 'POST',
            url: '/login',
            contentType: 'application/json',
            data: JSON.stringify(userObject),
            success: function (response){
                console.log("User logged in. Response: " + response);
                const name = JSON.parse(response).Username;
                location.href = `/homePage/${name}`;
            },
            error: function (error) {
                console.error("Error logging in: ", error);
            }
        })
    })
})