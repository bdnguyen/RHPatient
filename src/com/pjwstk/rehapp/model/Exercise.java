package com.pjwstk.rehapp.model;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Exercise implements Parcelable {
	
	private int exID;
	private String title;
	private String description;
	List<String> imgURLs = new ArrayList<>();
	private boolean doneToday;
	private String treatmentType;
	List<Bitmap> imgs = new ArrayList<>();
	
	public Exercise(String title, String description, boolean doneToday, ArrayList<String> imgURLs) {
		super();
		this.title = title;
		this.description = description;
		this.doneToday = doneToday;
		this.imgURLs = imgURLs;
	}
	
	public Exercise(Parcel in) {
		this.title = in.readString();
		this.description = in.readString();
		this.doneToday = in.readByte() != 0; //doneToday true if byte != 0
		this.exID = in.readInt();
		this.imgURLs = in.readArrayList(null);
	}

	public int getExID() {
		return exID;
	}

	public void setExID(int exID) {
		this.exID = exID;
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
	
	public List<String> getImgURLs() {
		return imgURLs;
	}

	public void setImgURLs(List<String> imgURLs) {
		this.imgURLs = imgURLs;
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
	
	public List<Bitmap> getImgs() {
		return imgs;
	}

	public void setImgs(List<Bitmap> imgs) {
		this.imgs = imgs;
	}
	
	public void addImgs(Bitmap bm){
		if(imgs != null){
			if(!imgs.contains(bm)){
				imgs.add(bm);
			}
		}
	}
	
	public void removeImgs(Bitmap bm){
		if(imgs.contains(bm)){
			imgs.remove(bm);
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
		out.writeInt(exID);
		out.writeList(imgURLs);
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
