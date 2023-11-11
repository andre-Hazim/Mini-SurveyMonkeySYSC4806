package org.MiniSurveyMonkey.Fields;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("field")
public abstract class Field {
    @Id
    protected String id;

    private String formId;

    private FieldType fieldType;

    private String question;

    public Field(String question, FieldType fieldType, String formId){
        this.question = question;
        this.fieldType= fieldType;
        this.formId = formId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", fieldType=" + fieldType +
                ", question='" + question + '\'' +
                '}';
    }
}
