package org.MiniSurveyMonkey;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document("user")
public class User {
    @Id
    private String id;

    private String username;

    public User(String username){this.username = username;}

    public User(){
        this("");
    }

    public String getId() {return id;}

    public String getUsername() {return username;}

    public void setId(String id) {this.id = id;}

    public void setUsername(String username) {this.username = username;}

    @Override
    public String toString(){
        return "{username:" + this.username +"}";
    }
}
