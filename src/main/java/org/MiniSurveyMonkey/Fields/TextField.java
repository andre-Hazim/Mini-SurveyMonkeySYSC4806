package org.MiniSurveyMonkey.Fields;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("textField")
public class TextField extends Field{

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