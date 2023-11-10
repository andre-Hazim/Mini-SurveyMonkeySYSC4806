/**
 * File handles all SPA actions by the Form
 */

 const upperStr = "_upper"
 const lowerStr = "_lower"
$(document).ready(function() {
    let questionCount = 1; // first question added by default

    $('#addQuestion').click(function(event) {
        questionCount++;
        event.preventDefault();

        // each question has a div
        const questionDiv = $('<div>').addClass('question').attr('id', `question${questionCount}`);

        const questionLabel = $('<label>').attr('for', `questionTitle${questionCount}`).text(`Question ${questionCount}`);
        const questionInput = $('<input>').attr({
            type: 'text',
            id: `questionTitle${questionCount}`,
            name: `questionTitle${questionCount}`,
            placeholder: 'Enter Survey Question',
            required: 'true'
        });

        const fieldTypeElements = createFieldTypeElement(questionCount)

        const inputContainer = $('<div>').addClass('inputContainer');

        questionDiv.append(questionLabel).append(questionInput).append('<br><br>').append(fieldTypeElements.label).append(fieldTypeElements.dropdown);
        questionDiv.append(inputContainer);
        $('#surveyForm').append('<br><br>').append(questionDiv);
    });

    $('#surveyForm').on('change', '[id^=fieldType]', function() {
        const selectedOption = $(this).val();
        const questionNumber = $(this).attr('id').match(/\d+/)[0];
        const parentDiv = $(this).closest('.question');
        const inputContainer = parentDiv.find('.inputContainer');

        inputContainer.empty();

        const fieldContainer = $('<div>');
        if (selectedOption === 'number') {

            fieldContainer.append(
                createNumericalField(fieldContainer, questionCount, lowerStr)
            )

            fieldContainer.append(
                createNumericalField(fieldContainer, questionCount, upperStr)
            )
            //lower bound

        } else if (selectedOption === 'text') {
            fieldContainer.append(createTextField(questionNumber))
        } else if (selectedOption === 'multipleChoice') {
            const mcContainer = $('<div>'); // container for multiple choice options

            // initial two multiple choice options
            fieldContainer.append(createMCOption(questionNumber, 1)).append(createMCOption(questionNumber, 2));


            // to add more choices
            const addChoiceBtn = $('<button>').text('Add Choice').click(function(event) {
                event.preventDefault();
                const optionCount = fieldContainer.find('.mcOption').length + 1;
                fieldContainer.append(createMCOption(questionNumber, optionCount));
                updateRemoveButtons(); // update remove buttons after addition
            });
            inputContainer.append('<br>').append(addChoiceBtn);
        }

        inputContainer.append(fieldContainer);
    });


    function createFieldTypeElement(questionCount) {
        const fieldTypeLabel = $('<label>').attr('for', `fieldType${questionCount}`).text('Choose Field Type:');
        const fieldTypeDropdown = $('<select>').attr({
            id: `fieldType${questionCount}`,
            name: `fieldType${questionCount}`
        }).html(`
        <option value="text">Select a Field Type</option>
        <option value="text">Text Field</option>
        <option value="number">Number Field</option>
        <option value="multipleChoice">Multiple Choice</option>
    `);

        return {
            label: fieldTypeLabel,
            dropdown: fieldTypeDropdown
        };
    }

function createNumericalField(fieldContainer, questionCount, type) {
    const divForField = $('<div>')

   label = (type == upperStr ? 'Upper bound' : 'Lower bound')
    divForField.append($('<label>').text(label))
    divForField.append(
        $('<input>').attr({
           type: 'number',
           id: questionCount + type,
           name: questionCount + type,
           placeholder: '(Optional)'
       }).on("input", checkNumericalValidity(fieldContainer, questionCount)))

    return divForField
}

    function createTextField(questionNumber){
        const textFieldDiv = $('<div>').addClass('textField');
        const textArea = $('<textarea>').attr({
            type:'textArea',
            name: `textField${questionNumber}`,
            rows:'5',
            cols: '50',
            readOnly: true,
            placeholder: 'User answer would go here'
        });
        $(textArea).css("resize","none")
        textFieldDiv.append(textArea);
        return textFieldDiv;
    }
    // Function to create a multiple choice option
    function createMCOption(questionNumber, optionCount) {
        const mcOptionDiv = $('<div>').addClass('mcOption');
        const radioBtn = $('<input>').attr({
            type: 'radio',
            name: `mcOption${questionNumber}`,
        });

        const optionInput = $('<input>').attr({
            type: 'text',
            name: `mcOption${questionNumber}Text`,
            placeholder: 'Enter Choice',
            required: 'true'
        });

        const removeButton = $('<button>').text('Remove').prop('disabled', true).click(function () {
            $(this).closest('.mcOption').remove();
            updateRemoveButtons(); // update remove buttons after removal
        });

        mcOptionDiv.append(radioBtn, optionInput, removeButton);
        return mcOptionDiv;
    }

    // Function to enable/disable remove buttons for MC options
    function updateRemoveButtons() {
        $('.question').each(function () {
            const mcOptions = $(this).find('.mcOption');
            const numOptions = mcOptions.length;

            mcOptions.each(function (index) {
                const removeButton = $(this).find('button');
                removeButton.prop('disabled', numOptions <= 2 && index < 2);
            });
        });
    }


function checkNumericalValidity(fieldContainer, questionCount) {
    return function () {
        const lower = fieldContainer.find('#' + questionCount + lowerStr);
        const upper = fieldContainer.find('#' + questionCount + upperStr);
        const lowerValue = parseInt(lower.val(), 10);
        const upperValue = parseInt(upper.val(), 10);

        if (!isNaN(lowerValue) && !isNaN(upperValue) && lowerValue > upperValue) {
            upper.css("outline", "auto");
            lower.css("outline", "auto");
            upper.css("outline-color", "red");
            lower.css("outline-color", "red");
            upper.focus(function () {
                upper.css("outline-color", "red");
            });
            lower.focus(function () {
                lower.css("outline-color", "red");
            });
        } else {
            upper.css({
                'outline': ''
            });
            lower.css({
                'outline': ''
            });
            upper.focus(function () {
                upper.css({
                    'outline': ''
                });
            });
            lower.focus(function () {
                lower.css({
                    'outline': ''
                });
            });
        }
    };
}

})