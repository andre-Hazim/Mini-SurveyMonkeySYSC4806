package org.MiniSurveyMonkey.Visualizations;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = HistogramGraph.class, name = "Histogram"),
        @JsonSubTypes.Type(value = PieGraph.class, name = "PieChart"),
})

public abstract class Visualization {

    private String formId;

    private String fieldName;

    private String fieldId;

    private VisualizationType visualizationType;

    public Visualization(String formId, String fieldName, String fieldId, VisualizationType visualizationType) {
        this.formId = formId;
        this.fieldName = fieldName;
        this.fieldId = fieldId;
        this.visualizationType = visualizationType;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public VisualizationType getGraphType(){
        return this.visualizationType;
    }

    public void setGraphType(VisualizationType visualizationType){
        this.visualizationType = visualizationType;
    }

}
