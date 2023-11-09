package org.MiniSurveyMonkey.Repositories;

import org.MiniSurveyMonkey.Fields.Field;
import org.MiniSurveyMonkey.Fields.FieldType;
import org.MiniSurveyMonkey.Fields.TextField;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface FieldRepo extends MongoRepository<Field, String>{

    List<Field> findByFieldType(FieldType fieldType);

    List<Field> findByFormId(String formId);


}
