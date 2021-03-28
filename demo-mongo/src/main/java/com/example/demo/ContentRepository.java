package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ds on 2021/03/16.
 */

@Repository
public interface ContentRepository extends MongoRepository<Content, String> {

}