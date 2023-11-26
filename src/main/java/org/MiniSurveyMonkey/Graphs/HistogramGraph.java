package org.MiniSurveyMonkey.Graphs;

import java.util.ArrayList;

public class HistogramGraph extends Graph{

    private ArrayList<String> xLabels;

    private ArrayList<String> yData;

    public HistogramGraph(String formId, String fieldName, String fieldId) {
        super(formId, fieldName, fieldId, GraphType.HISTOGRAMGRAPH);
    }

    public ArrayList<String> getxLabels() {
        return xLabels;
    }

    public void setxLabels(ArrayList<String> xLabels) {
        this.xLabels = xLabels;
    }

    public ArrayList<String> getyData() {
        return yData;
    }

    public void setyData(ArrayList<String> yData) {
        this.yData = yData;
    }


    @Override
    public void calculateResponse(ArrayList<String> responses) {

    }
}
