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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Stay {
	
	@Id
	private int stayid;
	@ManyToOne
	@JoinColumn(name="patient")
    private Patient patient;
	@ManyToOne
	@JoinColumn(name="room")
    private Room room;
	@Column
    private Timestamp start_time;
	@Column
    private Timestamp end_time;

}
