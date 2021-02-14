package com.mmi.mmi.dto;

import lombok.Data;

@Data
public class UserDto {
	private String userName;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String codeJabatan;
}
