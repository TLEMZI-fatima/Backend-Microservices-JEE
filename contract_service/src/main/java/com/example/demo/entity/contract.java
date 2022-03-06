package com.example.demo.entity;
import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Contract")
public class contract {
	
	@Id
	private int id;
	private String type;
	private String date;
	private int price;
	private String seller;
	private String buyer;
	private String proprety;
	private int taxe;
	
	public contract() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	  public contract(int id, String buyer, String seller, String type, int price, int taxe, String date, String proprety)
	  { 
		  super(); 
		  this.id= id;
		  this.setbuyer(buyer);
	      this.setseller(seller); 
	      this.settype(type); 
	      this.setprice(price); 
	      this.settaxe(taxe); 
	      this.setdate(date); 
	      this.setproprety(proprety); 



	  }
	  
	  public String getbuyer() {
		  return buyer; 
	  } 
	  public void setbuyer(String buyer) {
		  this.buyer = buyer; 
		  } 
	  public String getseller() { 
		  return seller;
		  } 
	  public void setseller(String seller) { 
		  this.seller = seller; 
		  }
	  
	  public String gettype() {
		  return type; 
	  } 
	  public void settype(String type) {
		  this.type = type; 
		  } 
	  
	  public int getprice() {
		  return price; 
	  } 
	  public void setprice(int price) {
		  this.price = price; 
		  } 
	  
	  public int gettaxe() {
		  return taxe; 
	  } 
	  public void settaxe(int taxe) {
		  this.taxe = taxe; 
		  } 
	  public int getId() { // TODO Auto-generated method stub // this.id=id; 
		  return this.id;
	  }



	public String getdate() {
		return date;
	}




	public void setdate(String date) {
		this.date = date;
	}



	public String getproprety() {
		return proprety;
	}




	public void setproprety(String proprety) {
		this.proprety = proprety;
	}




	
}
