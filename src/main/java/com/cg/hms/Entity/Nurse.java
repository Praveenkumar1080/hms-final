package com.cg.hms.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Nurse {
	@Id
	private int employeeid;
	@Column
    private String name;
	@Column
    private String position;
	@Column
    private boolean registered;
	@Column
    private int ssn;

}
