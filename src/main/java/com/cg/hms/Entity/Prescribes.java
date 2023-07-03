package com.cg.hms.Entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Prescribes {
    @EmbeddedId
    private PrescribesId id;
    @ManyToOne
    @MapsId("physician")
    @JoinColumn(name="physician")
	private Physician physician;
    
    @ManyToOne
    @MapsId("patient")
    @JoinColumn(name="patient")
	private Patient patient;
    
    @ManyToOne
    @MapsId("medication")
    @JoinColumn(name="medication")
	private Medication medication;
    
    @Column
	private String data;
    
    @ManyToOne
    @MapsId("appointment")
    @JoinColumn(name="appointment")
	private Appointment appointment;
	
}
