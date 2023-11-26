import { injectGraphs } from "/loadGraphs.js"
$(document).ready(function() {
    var formId = $(document).find("#formId_span").data("backend-id")
    console.log(formId)
    var formClosed = true
    $.ajax({
        type: 'GET',
        url: '/getForm/' + formId,
        success: function(data) {
            // Handle the form information
            console.log('Form Information:', data);
            injectFields(data)

        },
        error: function(error) {
            // Handle errors
            console.error('Error retrieving form information:', error);
        }
    });

    function injectFields(form) {
        fields = form.fields
        const questionsContainer = $("#questionsContainer")
        $.each(fields, function(index, field) {
            // Label
            const questionLabel = $('<label>').attr('for', `${index + 1}`).text(`${index + 1}. ${field.question}`);
            questionLabel.css("display","block");

            const fieldContainer = $("<div>").attr({
                id: "field-" + (field.question).replace(" ", "-")
            }).addClass("question").data("backend-id", field.id)

            fieldContainer.append(questionLabel)
            //checking which input type
            if (field.fieldType === "TEXT") {
                fieldContainer.append(buildTextField(field))
            } else if (field.fieldType === "MC") {
                fieldContainer.append(buildMCField(field, index))
            } else {
                console.log("numerical field")
                fieldContainer.append(buildNumericalField(field))
            }
            questionsContainer.append(fieldContainer,'<br>')
        })
    }

    function buildTextField(fieldInfo) {
        return textField = $('<textarea>').attr({
            type:'textArea',
            id: fieldInfo.question + fieldInfo.id,
            name: fieldInfo.question + fieldInfo.id,
            rows:'5',
            cols: '50',
            placeholder: 'Enter answer here'
        });
    }

    function buildMCField(fieldInfo, index) {
        const mcFieldContainer = $('<div>');

        const options = fieldInfo.options || [];
        options.forEach(function (option, count) {
            const radioBtn = $('<input>').attr({
                type: 'radio',
                name: fieldInfo.question + fieldInfo.id,
                id: 'Q'+ (index + 1) + 'Option' + (count + 1)
            });

            const optionLabel = $('<label>').attr('for', 'Q'+ (index + 1) + 'Option' + (count + 1)).text(option);

            mcFieldContainer.append(radioBtn, optionLabel, '<br>');
        });
        return mcFieldContainer;
    }

    function buildNumericalField(fieldInfo) {
        return numericalField = $('<input>').attr({
            type: 'number',
            id: fieldInfo.question + fieldInfo.id,
            name: fieldInfo.question + fieldInfo.id,
        }).on("input", function() {
            const input = parseInt($(this).val(), 10)
            if ((fieldInfo.lowerBound != null && input < fieldInfo.lowerBound) ||
                (fieldInfo.upperBound != null && input > fieldInfo.upperBound)) {
                updateErrorStatus($(this), true)
            } else {
                updateErrorStatus($(this), false)
            }
        })
    }

    function updateErrorStatus(field, error) {
        if (error) {
            field.css("outline", "auto");
            field.css("outline-color", "red");
            field.focus(function() {
                field.css("outline-color", "red")
            });
        } else {
            field.css({
                'outline': ''
            });
            field.focus(function() {
                field.css({
                    'outline': ''
                });
            });
        }
    }
});