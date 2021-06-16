package com.quizme.Repository;

import com.quizme.Model.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizRepository extends MongoRepository<Quiz,String> {
}
