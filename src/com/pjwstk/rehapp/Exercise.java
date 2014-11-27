package com.pjwstk.rehapp;

public class Exercise {
	
	private String title;
	private String description;
	private int imgID;
	private int doneToday; // 1 -- done 0 -- not done
	private String treatmentType;
	
	public Exercise(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getImgID() {
		return imgID;
	}
	public void setImgID(int imgID) {
		this.imgID = imgID;
	}
	public int isDoneToday() {
		return doneToday;
	}
	public void setDoneToday(int doneToday) {
		this.doneToday = doneToday;
	}

	public String getTreatmentType() {
		return treatmentType;
	}

	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}
	
	
	
}
