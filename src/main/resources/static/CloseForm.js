$(document).ready(function(){
    function closeForm(formId) {
        $.ajax({
            type: 'POST',
            url: '/closeForm',
            data: {formId: formId},
            success: function (data) {
                // Handle the form information
                console.log('Form Information:', data);
                $('#closeButton').remove()
                $('#editButton').remove()
                $('#formContainer').append('<p>Form is closed</p>');
                $('#responseForm').remove();
            },
            error: function (error) {
                // Handle errors
                console.error('Error retrieving form information:', error);
            }
        });
    }

    $("#closeButton").click(function (){
        var formId = $(document).find("#formId_span").data("backend-id")
        const requestData = {formId: formId}
        let formUser = $('#author').text();
        formUser = formUser.replace('Author: ', '')

        $.ajax({
            type: 'GET',
            url: '/getUser',
            success: function (data){
                console.log('Active user: ' ,data)
                if (formUser === data){

                    closeForm(formId);
                }
            },
            error: function(error) {
                // Handle errors
                console.error('Error retrieving form information:', error);
            }
        })
    });
});