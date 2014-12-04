package com.pjwstk.rehapp.model;

import java.util.HashSet;
import java.util.Set;


public class Therapist {	
	
	private Set<Patient> patients = new HashSet<>();
	private String name;
	private int id;
	
	public Therapist(String name){
		name=this.name;
	}
	
	public Set<Patient> getPatients() {
		return patients;
	}
	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
}
