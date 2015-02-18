package com.pjwstk.rehapp.model;

import java.util.Date;

public class Note {
	private String issuer;
	private String content;
	private String sendingUserId;
	private Date date;
	
	private boolean fromTherapist;
	
	public Note(String content, boolean fromTherapist){
		this.content = content;
		this.fromTherapist = fromTherapist;
	}
	
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isfromTherapist() {
		return fromTherapist;
	}

	public void setfromTherapist(boolean fromTherapist) {
		this.fromTherapist = fromTherapist;
	}

	public String getSendingUserId() {
		return sendingUserId;
	}

	public void setSendingUserId(String sendingUserId) {
		this.sendingUserId = sendingUserId;
	}
}
