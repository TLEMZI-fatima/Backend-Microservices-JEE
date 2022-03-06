package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.client;


public interface repository extends MongoRepository<client, Integer>{

}
