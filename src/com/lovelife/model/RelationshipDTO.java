package com.lovelife.model;

import java.io.Serializable;

public class RelationshipDTO implements Serializable {
	private static final long serialVersionUID = -6685605560894055899L;
	private String rID;
	private String startDate;
	private int fID;
	private int sID;
	
	public String getrID() {
		return rID;
	}
	public void setrID(String rID) {
		this.rID = rID;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public int getfID() {
		return fID;
	}
	public void setfID(int fID) {
		this.fID = fID;
	}
	public int getsID() {
		return sID;
	}
	public void setsID(int sID) {
		this.sID = sID;
	}
	
	public RelationshipDTO() {
		super();
	}
	
	public RelationshipDTO(String rID, String startDate, int fID, int sID) {
		super();
		this.rID = rID;
		this.startDate = startDate;
		this.fID = fID;
		this.sID = sID;
	}
	
	@Override
	public String toString() {
		return "RelationshipDTO [rID=" + rID + ", startDate=" + startDate + ", fID=" + fID + ", sID=" + sID + "]";
	}
	
}
