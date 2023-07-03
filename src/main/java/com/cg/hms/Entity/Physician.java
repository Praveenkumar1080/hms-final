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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Physician {
@Id
private Integer EmployeeID;
@Column
private String name;
@Column
private String position;
@Column
private Integer ssn;

}
