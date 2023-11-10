package org.MiniSurveyMonkey.Repositories;
import org.MiniSurveyMonkey.Forms.Form;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface FormRepo extends MongoRepository<Form, String>{


}
