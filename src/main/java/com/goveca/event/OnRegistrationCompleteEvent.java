package com.goveca.event;

import org.springframework.context.ApplicationEvent;

import com.goveca.Entity.AccountEntity;

import lombok.Data;

@Data
public class OnRegistrationCompleteEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String appUrl;
	private AccountEntity account;

	public OnRegistrationCompleteEvent(AccountEntity account) {
		super(account);
		this.account = account;
	}
}
