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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Affiliatedwith {

	@EmbeddedId
	private AffId id;
	@ManyToOne
	@MapsId("Physician")
	@JoinColumn(name="Physician")
	private Physician physician;
	
	@ManyToOne
	@MapsId("Department")
	@JoinColumn(name="Department")
	private Department department;
	
	@Column
	private Boolean primaryAffiliation;
	
}
