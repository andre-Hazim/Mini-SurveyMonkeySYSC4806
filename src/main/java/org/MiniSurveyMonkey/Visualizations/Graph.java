package org.MiniSurveyMonkey.Visualizations;

import java.util.ArrayList;

public abstract class Graph extends Visualization{
    private ArrayList<String> xLabels;

    private ArrayList<String> yData;

    public Graph(String formId, String fieldName, String fieldId, VisualizationType visualizationType) {
        super(formId, fieldName, fieldId, visualizationType);
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
    public abstract void calculateResponse(ArrayList<String> responses);

}
