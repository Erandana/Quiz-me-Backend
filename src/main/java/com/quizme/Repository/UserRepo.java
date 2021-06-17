package com.quizme.Repository;


import com.quizme.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User,String> {

    public User findByuserName(String userName);
}
