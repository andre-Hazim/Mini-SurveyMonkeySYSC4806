package org.MiniSurveyMonkey.Visualizations;

import java.util.ArrayList;

public class HistogramGraph extends Graph {


    public HistogramGraph(String formId, String fieldName, String fieldId) {
        super(formId, fieldName, fieldId, VisualizationType.HISTOGRAMGRAPH);
    }


    @Override
    public void calculateResponse(ArrayList<String> responses) {

    }
}
