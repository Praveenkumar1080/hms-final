package com.cg.hms.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
public class On_call {
@Id
private int nurse;
@ManyToOne
@JoinColumns ({
    @JoinColumn(name="blockfloor", referencedColumnName = "blockfloor"),
    @JoinColumn(name="blockcode", referencedColumnName = "blockcode"),
})
private Block block;
@Column
private String oncallstart;
@Column
private String oncallend;

}
