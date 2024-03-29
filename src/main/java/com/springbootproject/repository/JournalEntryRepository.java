package com.springbootproject.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.springbootproject.entity.JournalEntry;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}
