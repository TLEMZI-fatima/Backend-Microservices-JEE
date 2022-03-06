package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "ClientDAO")
public class client {
	
	@Id
	private int id;
	private String nom;
	private String type;
	private String prenom;
	private String address;
	private int code_postal;
	private int mobile;
	
	public client() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	  public client(int id, String nom, String prenom, String type, String address, int  mobile, int code_postal)
	  { 
		  super(); 
		  this.id= id;
		  this.setName(nom);
		  this.setType(type);

	      this.setprenon(prenom); 
	      this.setAddress(address); 
	      this.setmobile(mobile); 
	      this.setcode_postal(code_postal); 

	  }
	  
	  public String getnom() {
		  return nom; 
	  } 
	  public void setName(String nom) {
		  this.nom = nom; 
		  } 
	  public String getprenom() { 
		  return prenom;
		  } 
	  public void setprenon(String prenom) { 
		  this.prenom = prenom; 
		  }
	  
	  public String getaddress() {
		  return address; 
	  } 
	  public void setAddress(String address) {
		  this.address = address; 
		  } 
	  
	  public int getmobile() {
		  return mobile; 
	  } 
	  public void setmobile(int mobile) {
		  this.mobile = mobile; 
		  } 
	  
	  public int getcode_postal() {
		  return code_postal; 
	  } 
	  public void setcode_postal(int code_postal) {
		  this.code_postal = code_postal; 
		  } 
	  public int getId() { // TODO Auto-generated method stub // this.id=id; 
		  return this.id;
	  }




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}
	 
	
}
