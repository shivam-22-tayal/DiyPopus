package com.ibm.diypopups.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class creditcounter {
	@Id
	@GeneratedValue 
	private int sln;
	private long eid;
	private long vid;
	
	public long getEid() {
		return eid;
	}
	public void setEid(long eid) {
		this.eid = eid;
	}
	public long getVid() {
		return vid;
	}
	public void setVid(long vid) {
		this.vid = vid;
	}
	public int getSln() {
		return sln;
	}
	public void setSln(int sln) {
		this.sln = sln;
	}
	
	
	

}
