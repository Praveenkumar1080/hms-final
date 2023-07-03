package com.cg.hms.Entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Undergoes {
@EmbeddedId
private UndergoesId id;
@ManyToOne
@MapsId("Patient")
@JoinColumn(name="Patient")
private Patient patient;

@ManyToOne
@MapsId("Procedures")
@JoinColumn(name="Procedures")
private Procedures Procedures;

@ManyToOne
@MapsId("Stay")
@JoinColumn(name="Stay")
private Stay stay;

@ManyToOne
@MapsId("Physician")
@JoinColumn(name="Physician")
private Physician Physician;

@ManyToOne
@JoinColumn(name="AssistingNurse")
private Nurse assistingnurse;

}
