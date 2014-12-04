package com.pjwstk.rehapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Exercise implements Parcelable {
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
	
	public Exercise(Parcel in) {
		this.title = in.readString();
		this.description = in.readString();
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(title);
		out.writeString(description);
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
