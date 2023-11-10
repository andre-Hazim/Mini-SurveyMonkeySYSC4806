/**
 * File handles all SPA actions by the Form
 */
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
            placeholder: 'Enter Survey Question'
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
            const numericalContainer = $('<div>');

            //lower bound
            fieldContainer.append(
                $('<input>').attr({
                    type: 'number',
                    id: `questionTitle${questionCount}` + 'numerical_lower',
                    name: `questionTitle${questionCount}` + 'numerical_lower',
                    placeholder: 'Lower Bound (Optional)'
                }).on("input", checkNumericalValidity)
            );

            // upper bound
            fieldContainer.append(
                $('<input>').attr({
                    type: 'number',
                    id: `questionTitle${questionCount}` + 'numerical_upper',
                    name: `questionTitle${questionCount}` + 'numerical_upper',
                    placeholder: 'Upper Bound (Optional)'
                }).on("input", checkNumericalValidity));

            function checkNumericalValidity() {
                const lower = fieldContainer.find(`#questionTitle${questionCount}` + 'numerical_lower');
                const upper = fieldContainer.find(`#questionTitle${questionCount}` + 'numerical_upper');
                const lowerValue = parseInt(lower.val(), 10);
                const upperValue = parseInt(upper.val(), 10);

                if (!isNaN(lowerValue) && !isNaN(upperValue) && lowerValue > upperValue) {
                    upper.css("outline", "auto");
                    lower.css("outline", "auto");
                    upper.css("outline-color", "red");
                    lower.css("outline-color", "red");
                    upper.focus(function() {
                        upper.css("outline-color", "red");
                    });
                    lower.focus(function() {
                        lower.css("outline-color", "red");
                    });
                } else {
                    upper.css({
                        'outline': ''
                    });
                    lower.css({
                        'outline': ''
                    });
                    upper.focus(function() {
                        upper.css({
                            'outline': ''
                        });
                    });
                    lower.focus(function() {
                        lower.css({
                            'outline': ''
                        });
                    });
                }
            }

        } else if (selectedOption === 'text') {
            // TODO: show text field
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
})