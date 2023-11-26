package org.MiniSurveyMonkey.Visualizations;

import java.util.ArrayList;

public class PieGraph extends Graph {

    private ArrayList<String> options;

    public PieGraph(String formId, String fieldName, String fieldId, ArrayList<String> options) {
        super(formId, fieldName, fieldId, VisualizationType.PIEGRAPH);
        this.options = options;

    }

    @Override
    public void calculateResponse(ArrayList<String> responses) {

    }
}
