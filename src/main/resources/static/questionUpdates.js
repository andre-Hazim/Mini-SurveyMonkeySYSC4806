import { upperStr, lowerStr, questionCount } from './Form.js';
import { createFieldTypeElement } from './createField.js';

/**
 * Function to create a specific question div and its initial contents
 * @returns {*|jQuery}
 */
function createQuestionDiv() {
    // each question has a div
    const questionDiv = $('<div>').addClass('question').attr('id', `question${questionCount}`);

    const questionLabel = $('<label>').attr('for', `questionTitle${questionCount}`).text(`Question ${questionCount} `);
    const questionInput = $('<input>').attr({
        type: 'text',
        id: `questionTitle${questionCount}`,
        name: `questionTitle${questionCount}`,
        placeholder: 'Enter Survey Question',
        required: 'true'
    });

    // add a delete button
    const deleteQuestionButton = $('<button>').attr('name', 'deleteQuestion').text('X').css('margin-left', '5px');

    const fieldTypeElements = createFieldTypeElement();
    const inputContainer = $('<div>').addClass('inputContainer');

    questionDiv.append(questionLabel,questionInput, deleteQuestionButton, '<br><br>', fieldTypeElements.label, fieldTypeElements.dropdown, inputContainer);

    return questionDiv;
}

/**
 * Function to update attributes and labels for the remaining questions
 * after a question has been deleted
 */
function updateQuestionNumbers() {
    $('.question').each(function (index) {
        const questionNumber = index + 1;

        // Update question div id and question label
        $(this).attr('id', `question${questionNumber}`);
        $(this).find('label[for^="questionTitle"]').attr('for', `questionTitle${questionNumber}`).text(`Question ${questionNumber} `);

        // Update question title input id and name
        $(this).find('input[id^=questionTitle]').attr({
            id: `questionTitle${questionNumber}`,
            name: `questionTitle${questionNumber}`
        });

        // Update field type dropdown id and name
        $(this).find('label[for^="fieldType"]').attr('for', `fieldType${questionNumber}`)
        $(this).find('select[id^=fieldType]').attr({
            id: `fieldType${questionNumber}`,
            name: `fieldType${questionNumber}`
        });

        // Update numerical field ids and names
        $(this).find('input[type="number"]').each(function () {
            const numericType = $(this).attr('id').includes(upperStr) ? upperStr : lowerStr;
            $(this).attr({
                id: `${questionNumber}${numericType}`,
                name: `${questionNumber}${numericType}`
            });
        });

        // Update textfield id and name
        $(this).find('textarea[id^=textField]').attr({
            id: `textField${questionNumber}`,
            name: `textField${questionNumber}`
        });

        // Update MC option ids and names
        $(this).find('input[id^=mcQ]').each(function () {
            const optionType = $(this).attr('id').includes('Text') ? 'Text' : 'Radio';
            const matchResult = $(this).attr('id').match(/\d+$/);
            const optionCount = matchResult ? parseInt(matchResult[0]) : 0;
            $(this).attr({
                name: `mcQ${questionNumber}${optionType}`,
                id: `mcQ${questionNumber}${optionType}${optionCount}`
            });
        });
    });

    updateDeleteQuestionButton();
}

/**
 * Function to enable/disable a delete question button
 */
function updateDeleteQuestionButton() {
    const deleteButtons = $('[name="deleteQuestion"]');

    // disable if there is only one, otherwise enable it
    deleteButtons.prop('disabled', $('.question').length === 1);
}

export { createQuestionDiv, updateQuestionNumbers, updateDeleteQuestionButton, createFieldTypeElement };

