
//Gets called in the closed form page to navigate to the Edit Form page
$(document).ready(function(){
    //navigate to edit form page
    $("#editButton").click(function (){
        const formId = $(document).find("#formId_span").data("backend-id")
        window.location.replace("/editForm/" + formId);
    });

});

//Once in the edit page, manage populating the field, and submitting
$(document).ready(function() {
    var formId = $(document).find("#formId_span").data("backend-id");

    // Make an AJAX call to get the form data
    $.ajax({
        type: 'GET',
        url: '/getForm/' + formId,
        success: function(data) {
            // Handle the form information
            console.log('Form Information:', data);

            if (data && data.fields) {
                data.fields.forEach(function(field, index) {
                    const questionDiv = createQuestionDiv(); // Create the question div using existing function

                    // Set question title
                    questionDiv.find(`#questionTitle${index + 1}`).val(field.question);

                    // Set field type and create elements accordingly
                    questionDiv.find(`#fieldType${index + 1}`).val(field['@type']).change();

                    // Pre-fill values based on field type
                    if (field['@type'] === 'NumberField') {
                        $(`#${index + 1}${lowerStr}`).val(field.lowerBound || 0);
                        $(`#${index + 1}${upperStr}`).val(field.upperBound || 100);
                    } else if (field['@type'] === 'MultipleChoiceField') {
                        field.options.forEach(function(option, optionIndex) {
                            if (optionIndex >= 2) {
                                const addChoiceBtn = questionDiv.find(`#addChoice${index + 1}`);
                                addChoiceBtn.click();
                            }
                            questionDiv.find(`#mcOption${index + 1}Text${optionIndex + 1}`).val(option);
                        });
                    } else if (field['@type'] === 'TextField') {
                        // For text fields, if needed
                    }

                    // Append the populated question div to the questions container
                    $('#questionsContainer').append(questionDiv);
                });
            }
        },
        error: function(error) {
            console.error('Error retrieving form information:', error);
        }
    });
});

