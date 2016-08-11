package com.iprocure_dan;

import java.io.Serializable;

public class Products implements Serializable{
    private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String type;
	private String price;
	

	public Products() {
	}

	public Products(long id, String name, String type, String price) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
	}

	public Products(String name, String type, String price) {
		this.name = name;
		this.type = type;
		this.price = price;
	}

	@Override
	public String toString() {
		return name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	
	
}
