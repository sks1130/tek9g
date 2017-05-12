package com.goveca.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {
	
	private Map<String,Object> token;
	private String status;
	private String message;
	private Object data;

}
