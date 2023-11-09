package org.MiniSurveyMonkey.Fields;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("mcField")
public class MultipleChoiceField extends Field{
    //options
    public MultipleChoiceField(String question,String formId) {
        super(question, FieldType.MC, formId);
    }
}
