package com.goveca.service;

import com.goveca.Entity.AccountEntity;
import com.goveca.Entity.VerificationTokenEntity;
import com.goveca.Exception.EmailExistsException;
import com.goveca.dto.Account;

public interface IAccountService {

	AccountEntity createAccount(Account account) throws EmailExistsException;
	AccountEntity getAccount(String verificationToken);
	void createVerificationToken(AccountEntity account, String token);
	VerificationTokenEntity getVerificationToken(String VerificationToken);
}
