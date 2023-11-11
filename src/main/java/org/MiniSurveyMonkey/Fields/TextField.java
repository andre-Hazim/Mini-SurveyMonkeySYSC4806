package org.MiniSurveyMonkey.Fields;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("textField")
@JsonTypeName("TextField")
public class TextField extends Field{

    public TextField(){}

    public TextField(String question, String formId){
        super(question,FieldType.TEXT, formId);

    }


    @Override
    public String toString() {
        return "TextField{" +
                "id=" + super.id +
                "question" + super.getQuestion() +  " " +
                '}';
    }
}