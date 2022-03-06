package com.example.demo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.contract;


public interface repository extends MongoRepository<contract, Integer>{

}
