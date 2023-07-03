package com.cg.hms.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class AffId implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Column
private Integer Physician;
@Column
private Integer Department;
}
