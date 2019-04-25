package com.ibm.diypopups.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class perdayclicks {
	
	@Id
	@GeneratedValue
	private long sl;
	private String sysdate;
	private long eid;
	private long clicklimit;
	public long getSl() {
		return sl;
	}
	public void setSl(long sl) {
		this.sl = sl;
	}
	public String getSysdate() {
		return sysdate;
	}
	public void setSysdate(String sysdate) {
		this.sysdate = sysdate;
	}
	public long getEid() {
		return eid;
	}
	public void setEid(long eid) {
		this.eid = eid;
	}
	public long getClicklimit() {
		return clicklimit;
	}
	public void setClicklimit(long clicklimit) {
		this.clicklimit = clicklimit;
	}
	
	

}

