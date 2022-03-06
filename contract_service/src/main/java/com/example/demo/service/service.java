package com.example.demo.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.contract;
import com.example.demo.repository.repository;


@Service
@RequestMapping("/contract")

@RestController
public class service {

	@Autowired
	private repository repository;

	@PostMapping("/add")
	public String save(@RequestBody contract cContract) {
		repository.save(cContract);
		return "Added contract with id : " + cContract.getId();
	}
	@PostMapping("/")
	public String sav() {
		return "Added contract with id : ";
	}

	@GetMapping("/findAll")
	public List<contract> get() {
		return repository.findAll();
	}

	@GetMapping("/findAll/{id}")
	public Optional<contract> getC(@PathVariable int id) {
		return repository.findById(id);
	}

	

}
