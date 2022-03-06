package com.example.demo.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.client;
import com.example.demo.repository.repository;
@Service
@RequestMapping("/clients")

@RestController
public class controller {

	@Autowired
	private repository repository;

	@PostMapping("/add")
	public String save(@RequestBody client client) {
		repository.save(client);
		return "Added client with id : " + client.getId();
	}

	@GetMapping("/findAll")
	public List<client> gets() {
		return repository.findAll();
	}

	@GetMapping("/findAll/{id}")
	public Optional<client> get(@PathVariable int id) {
		return repository.findById(id);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		repository.deleteById(id);
		return "client deleted with id : " + id;
	}

}
