package com.ibm.diypopups.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Advertisements")
public class Advertisements extends UserDateAudit{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String categoryadd;
	
	@NotBlank
	private String product;
		
	@NotBlank
	private String dop; 
	
	@NotBlank
	private String description;
	
	private int clicks;
	
	private long eid;
	
	private int Vcredits;
	
	public int getVcredits() {
		return Vcredits;
	}

	public void setVcredits(int vcredits) {
		Vcredits = vcredits;
	}

	public long getEid() {
		return eid;
	}

	public void setEid(long eid) {
		this.eid = eid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategoryadd() {
		return categoryadd;
	}

	public void setCategoryadd(String categoryadd) {
		this.categoryadd = categoryadd;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	
	public String getDop() {
		return dop;
	}

	public void setDop(String dop) {
		this.dop = dop;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}}
