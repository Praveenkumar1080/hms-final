package com.cg.hms.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Appointment {

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Integer appointmentid;
	@Column
	private Integer patient;
	@Column()
	private Integer prepnums;
	@Column
	private Integer physician;
	@Column
	private String startDtTime;
	@Column
	private String end_dt_time;
	@Column
	private String examinationroom;
	}
