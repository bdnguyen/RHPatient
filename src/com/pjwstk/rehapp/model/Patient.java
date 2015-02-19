package com.pjwstk.rehapp.model;

public class Patient {
	private String name;
	private int id;
	private Therapist therapist;
	
	public Patient(String name){
		name = this.name;		
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
	
	public Therapist getTherapist() {
		return therapist;
	}
	public void setTherapist(Therapist therapist) {
		this.therapist = therapist;
	}
		
}
