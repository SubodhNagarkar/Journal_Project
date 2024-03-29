package com.springbootproject.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.springbootproject.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {

	User findByUserName(String username);
}
