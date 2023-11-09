package org.MiniSurveyMonkey;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document("response")
public class Response {

    @Id
    private String id;

    //key is the String ID of a field - value String
    private HashMap<String, String> fieldAnswers;

    public Response() {
        fieldAnswers = new HashMap<>();
    }

    /**
     * get a response by id of a field
     * @param id
     * @return - response or null if id not found
     */
    public String getResponseById(String id) {
        return fieldAnswers.get(id);
    }

    /**
     * get all field-response pairs
     * @return responses
     */
    public HashMap<String, String> getFieldAnswers() {
        return fieldAnswers;
    }

    /**
     * add a new field response
     * @param fieldId
     * @param content
     */
    public void addFieldResponse(String fieldId, String content) {
        this.fieldAnswers.put(fieldId, content);
    }

    /**
     * getter for Id
     * @return id
     */
    public String getId() {
        return id;
    }
  
    public void setId(String id) {
        this.id = id;
    }

    public void setFieldAnswers(HashMap<String, String> fieldAnswers) {
        this.fieldAnswers = fieldAnswers;
    }
}
