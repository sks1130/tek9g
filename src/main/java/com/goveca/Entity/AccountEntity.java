package com.goveca.Entity;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.goveca.enums.IdentityType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="account")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountEntity {

	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	private IdentityType identityType;
	
	private boolean enabled;
	
	
	private String name;
	private String email;
	private String password;
}
