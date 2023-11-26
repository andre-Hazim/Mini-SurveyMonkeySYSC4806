$(document).ready(function() {

    $('#responseForm').submit(function (event) {
            event.preventDefault();

        const responseObj = {
            formId: $(document).find("#formId_span").data("backend-id"),
            fieldAnswers: {}
        };

        $('.question').each(function () {
            var backendId = $(this).data("backend-id")

            var response;
            if ($(this).find('input[type="radio"]').length > 0) {
                response = $(this).find('input[type="radio"]:checked').val();
            } else if ($(this).find('textarea').length > 0) {
                response = $(this).find('textarea').val();
            } else if ($(this).find('input[type="number"]').length > 0) {
                console.log('help')
                response = $(this).find('input[type="number"]').val();
            }

            if (responseObj.fieldAnswers.hasOwnProperty(backendId)) {
                responseObj.fieldAnswers[backendId].push(response);
            } else {
                responseObj.fieldAnswers[backendId] = response;
            }
        });
        console.log(JSON.stringify(responseObj))

            //handle ajax call
            $.ajax({
                type: 'POST',
                url: '/submitResponse',
                contentType: 'application/json',
                data: JSON.stringify(responseObj),
                success: function (response) {
                    console.log("Form submitted successfully. Response:", response);
                    window.location.replace("/submission-complete");
                },
                error: function (error) {
                    console.error("Error submitting form:", error);
                }
            });

        })
});