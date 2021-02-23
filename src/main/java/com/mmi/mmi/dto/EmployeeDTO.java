package com.mmi.mmi.dto;

import com.mmi.mmi.model.entity.Employee;
import com.mmi.mmi.service.converter.GenderConverter;
import com.mmi.mmi.service.converter.GenderConverter.gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
	private String nama;
	private String tanggalLahir;
	private String codeJabatan;
	private String jabatan;
	private int nip;
	private gender jenisKelamin;
	
	public void setDataEmployee(Employee employee) {
		this.nama = employee.getName();
		this.tanggalLahir = employee.getBirthDate().toString();
		this.codeJabatan = employee.getPosition().getCode();
		this.jabatan = employee.getPosition().getName();
		this.nip = employee.getIdNumber();
		this.jenisKelamin = GenderConverter.convert(employee.getGender()) ;
	}
}
