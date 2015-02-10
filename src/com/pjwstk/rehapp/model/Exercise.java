package com.pjwstk.rehapp.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Exercise implements Parcelable {
	
	private String title;
	private String description;
	List<String> imgURLs = new ArrayList<String>();
	private boolean doneToday;
	private String treatmentType;
	
	public Exercise(String title, String description, boolean doneToday) {
		super();
		this.title = title;
		this.description = description;
		this.doneToday = doneToday;
	}
	
	public Exercise(Parcel in) {
		this.title = in.readString();
		this.description = in.readString();
		this.doneToday = in.readByte() != 0; //doneToday true if byte != 0
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
	
	public void addImgURL(String iURL){
		if(iURL != null){
			if(!imgURLs.contains(iURL)){
				imgURLs.add(iURL);
			}
		}
	}
	
	public void removeImgURL(String iURL){
		if(imgURLs.contains(iURL)){
			imgURLs.remove(iURL);
		}
	}
	
	public boolean isDoneToday() {
		return doneToday;
	}
	public void setDoneToday(boolean doneToday) {
		this.doneToday = doneToday;
	}

	public String getTreatmentType() {
		return treatmentType;
	}

	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(title);
		out.writeString(description);
		out.writeByte((byte) (doneToday ? 1 : 0)); //if doneToday true, byte == 1
	}
	
	public static final Parcelable.Creator<Exercise> CREATOR = new Parcelable.Creator<Exercise>() {
		public Exercise createFromParcel(Parcel in){
			return new Exercise(in);
		}
		
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }

		
	};
	
	
}
