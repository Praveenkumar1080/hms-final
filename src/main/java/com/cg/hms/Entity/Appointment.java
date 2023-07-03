package com.cg.hms.Entity;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

	@Id
	private Integer AppointmentID;
	@ManyToOne
	@JoinColumn(name="patient")
	private Patient patient;
	@ManyToOne
	@JoinColumn(name="prepnurse")
	private Nurse prepnurse;
	@ManyToOne
	@JoinColumn(name="physican")
	private Physician physician;
	@Column
	private Timestamp start_dt_Time;
	@Column
	private Timestamp end_dt_time;
	@Column
	private String examinationroom;
	}
