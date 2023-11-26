package org.MiniSurveyMonkey.Visualizations;

import java.util.ArrayList;

public class Table extends Visualization {
    private ArrayList<String> textResponses;

    public Table(String formId, String fieldName, String fieldId) {
        super(formId, fieldName, fieldId, VisualizationType.TEXT);
    }

    public ArrayList<String> getTextResponses() {
        return textResponses;
    }

    public void setTextResponses(ArrayList<String> textResponses) {
        this.textResponses = textResponses;
    }
}
