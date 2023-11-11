package org.MiniSurveyMonkey.Fields;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextField.class, name = "TextField"),
        @JsonSubTypes.Type(value = NumberField.class, name = "NumberField"),
        @JsonSubTypes.Type(value = MultipleChoiceField.class, name = "MultipleChoiceField")
})
@Document("field")
public abstract class Field {
    @Id
    protected String id;

    private String formId;

    private FieldType fieldType;

    private String question;

    // Default constructor
    public Field() {
    }

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
