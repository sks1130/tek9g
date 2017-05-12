package com.goveca.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.goveca.Entity.AccountEntity;
import com.goveca.Exception.EmailExistsException;
import com.goveca.dto.Account;
import com.goveca.dto.ClientResponse;
import com.goveca.event.OnRegistrationCompleteEvent;
import com.goveca.service.IAccountService;

@RestController
@EnableAutoConfiguration
public class AccountController {

	@Autowired
	private IAccountService accountservice;

	@Autowired
	ApplicationEventPublisher eventPublisher;

	@RequestMapping(value="registerFirm",method = RequestMethod.POST)
	public @ResponseBody ClientResponse insertNewClient(@RequestBody @Valid Account accountDto) throws EmailExistsException{
		
		ClientResponse clientResponse = new ClientResponse();
		
		AccountEntity account = accountservice.createAccount(accountDto);
		
		try{
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(account));
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return clientResponse;
		
	}

}
