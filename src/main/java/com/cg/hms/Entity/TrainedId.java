package com.cg.hms.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TrainedId implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Column(name="physician")
private Integer physician;
@Column(name="treatment")
private Integer treatment;
public TrainedId() {
	super();
	// TODO Auto-generated constructor stub
}
public Integer getPhysician() {
	return physician;
}
public void setPhysician(Integer physician) {
	this.physician = physician;
}
public Integer getTreatment() {
	return treatment;
}
public void setTreatment(Integer treatment) {
	this.treatment = treatment;
}

}
