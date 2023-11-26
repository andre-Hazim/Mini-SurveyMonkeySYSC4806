package org.MiniSurveyMonkey.Forms;

import org.MiniSurveyMonkey.Fields.Field;
import org.MiniSurveyMonkey.Visualizations.Visualization;
import org.MiniSurveyMonkey.Response;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "Form")
public class Form {

    @Id
    private String id;

    private ArrayList<Field> fields;

    private ArrayList<Response> responses;

    private String formName;

    private String author;

    private boolean closed;

    private ArrayList<Visualization> visualizations;


    public Form(){
        this("","");
    }

    public Form(String formName, String author) {
        this.formName = formName;
        this.author = author;
        fields = new ArrayList<>();
        responses = new ArrayList<>();
        closed = false;
        this.visualizations = new ArrayList<>();
    }

    public void addField(Field field){
        this.fields.add(field);
    }
    public void addResponse(Response response){
        this.responses.add(response);
    }

    public void removeField(Field field){this.fields.remove(field);}

    public void removeResponse(Response response){this.fields.remove(response);}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public ArrayList<Response> getResponses() {
        return responses;
    }

    public void setResponses(ArrayList<Response> responses) {
        this.responses = responses;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public ArrayList<Visualization> getGraphs() {
        return visualizations;
    }

    public void addVisualization(Visualization visualization) {
        this.visualizations.add(visualization);
    }

    public void setVisualizations(ArrayList<Visualization> visualizations) {
        this.visualizations = visualizations;
    }

    @Override
    public String toString() {
        return "Form{" +
                "id='" + id + '\'' +
                ", fields=" + fields +
                ", responses=" + responses +
                ", formName='" + formName + '\'' +
                ", author='" + author + '\'' +
                ", closed=" + closed +
                '}';
    }


}
