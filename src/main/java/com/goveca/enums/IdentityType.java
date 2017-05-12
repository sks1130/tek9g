package com.goveca.enums;

public enum IdentityType {

	RECRUITMENTFIRM("Recruitment Firm"),
	COMPANY("Company");
	
	private String value;
	private IdentityType(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
