package com.goveca.dto;

import com.goveca.enums.IdentityType;

import lombok.Data;

@Data
public class Account {

	private IdentityType identityType;
	private String name;
	private String email;
	private String password;
}
