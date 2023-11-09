package org.MiniSurveyMonkey.Repositories;

import org.MiniSurveyMonkey.Response;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResponseRepo extends MongoRepository<Response, String> {
}
