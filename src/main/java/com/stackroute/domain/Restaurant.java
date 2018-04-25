package com.stackroute.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Restaurant implements Serializable{

private String name;
private String location;
private int id;
public void setName(String name) {
	this.name = name;
}
public void setLocation(String location) {
	this.location = location;
}
public void setId(int id) {
	this.id = id;
}


/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   
	public String getName() {
	return name;
}
public String getLocation() {
	return location;
}
public int getId() {
	return id;
}


}
