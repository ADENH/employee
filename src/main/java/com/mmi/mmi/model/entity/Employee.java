package com.mmi.mmi.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude="position")
public class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Integer id;
	@NotNull
	private String name;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date birthDate;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "position_id", nullable = false)
	@JsonIgnore
    private Position position;
	
	@Column(unique = true)
	@NotEmpty
	private Integer idNumber;
	@NotEmpty
	private Integer gender;
	@NotEmpty
	private Integer isDelete;
	
	@Override  
	public String toString()  
	{  
	return "Employee [employee id=" + id+ ", name=" + name + ", birthDate=" + birthDate + ", idNumber=" + idNumber + ", gender=" + gender + ", isDelete=" + isDelete + "]";  
	} 
}
