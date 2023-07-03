package com.cg.hms.Entity;

import java.io.Serializable;
import java.sql.Timestamp;


import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class UndergoesId implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Column
private Integer patient;
@Column
private Integer Procedures;
@Column
private Integer stay;
@Column(name="dateundergoes")
private Timestamp date;
}
