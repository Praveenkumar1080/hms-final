package com.cg.hms.Entity;

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
public class Patient {
    @Id
	private Integer ssn;
    @Column
	private String name;
    @Column
	private String address;
    @Column
	private Integer phone;
    @Column
	private Integer insuranceid;
    @ManyToOne
    @JoinColumn(name="pcp")
	private Physician pcp;
	
	
}
