package org.MiniSurveyMonkey.Fields;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document("mcField")
@JsonTypeName("MultipleChoiceField")
public class MultipleChoiceField extends Field {
    private ArrayList<String> options;
    private String selectedOption;

    public MultipleChoiceField() {}
    /**
     * Constructor for MC field
     * @param question, the question associated with this field
     * @param options, the list of options
     */
    public MultipleChoiceField(String question, String formId, ArrayList<String> options) {
        super(question, FieldType.MC, formId);
        this.options = options;
    }

    /**
     * Method to get options
     * @return ArrayList<String> options
     */
    public ArrayList<String> getOptions() {
        return options;
    }

    /**
     * Method to set options
     * @param options
     */
    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    /**
     * Method to get the selected option
     * @return String value of selected option
     */
    public String getSelectedOption() {
        return selectedOption;
    }

    /**
     * Method to set the selected option
     * @param selectedOption
     */
    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }
}
