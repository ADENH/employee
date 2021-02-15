package com.mmi.mmi.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mmi.mmi.dto.PositionDTO;

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
@ToString(of = {"code","name"})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Position implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotEmpty
	private Integer id;
	@NotEmpty
	@Column(unique = true)
	private String code;
	@NotEmpty
	private String name;
	@NotEmpty
	private Integer isDelete;
	
	@OneToMany(mappedBy = "position", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	@JsonIgnore
    private Set<Employee> employees;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
	@JsonIgnore
	private Set<User> user = new HashSet<>();
	
	public void setDataPositionDto(PositionDTO positionDTO) {
		this.code = positionDTO.getCode();
		this.name = positionDTO.getName();
		this.isDelete = 0;
	}
	
}
