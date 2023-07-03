package com.cg.hms.Entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Trainedin {
	
	@EmbeddedId
	private TrainedId id;
	
	@ManyToOne
	@MapsId("physician")
	@JoinColumn(name="physician")
	private Physician physician;
	
	@ManyToOne
	@MapsId("treatment")
	@JoinColumn(name="treatment")
    private Procedures treatment;
	
    private String certificationdate;
    private String certificationexpires;
	public Trainedin(Physician physician, Procedures treatment, String certificationdate, String certificationexpires) {
		super();
		this.physician = physician;
		this.treatment = treatment;
		this.certificationdate = certificationdate;
		this.certificationexpires = certificationexpires;
	}
    

}
