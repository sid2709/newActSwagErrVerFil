package com.example.Practise.ActuatSwagErrorHandVersionFilter.ActuatSwagErrorHandVersionFilter.Bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PersonResponse {

	@JsonIgnore
	private int id;
	private String name;

	public PersonResponse(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public PersonResponse() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
