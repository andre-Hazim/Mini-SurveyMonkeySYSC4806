package org.MiniSurveyMonkey.Repositories;

import org.MiniSurveyMonkey.Response;
import org.MiniSurveyMonkey.User;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface UserRepo extends MongoRepository<User, String>{
}
