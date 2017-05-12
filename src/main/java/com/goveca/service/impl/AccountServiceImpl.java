package com.goveca.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.goveca.Entity.AccountEntity;
import com.goveca.Entity.VerificationTokenEntity;
import com.goveca.Exception.EmailExistsException;
import com.goveca.dto.Account;
import com.goveca.repository.AccountRepository;
import com.goveca.repository.VerificationTokenRepository;
import com.goveca.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Override
	public AccountEntity createAccount(Account accountDto) throws EmailExistsException{

		AccountEntity account = AccountEntity.builder().email(accountDto.getEmail())
				.identityType(accountDto.getIdentityType()).name(accountDto.getName())
				.password(accountDto.getPassword()).enabled(false).build();
		
		return createUserAccount(account);
		
	}
	
	public AccountEntity createUserAccount(AccountEntity account) throws EmailExistsException{
		
		if (emailExist(account.getEmail())) {
            throw new EmailExistsException(
              "There is an account with that email adress: "
              + account.getEmail());
        }
		return accountRepository.save(account);
	}
	
	 private boolean emailExist(String email) {
	        AccountEntity account = accountRepository.findByEmail(email);
	        if (account != null) {
	            return true;
	        }
	        return false;
	    }

	@Override
	public AccountEntity getAccount(String verificationToken) {
		AccountEntity account = verificationTokenRepository.findByToken(verificationToken).getAccount();
		return account;
	}

	@Override
	public void createVerificationToken(AccountEntity account, String token) {
		
		VerificationTokenEntity token1 = new VerificationTokenEntity(token,account);
		verificationTokenRepository.save(token1);
	}

	@Override
	public VerificationTokenEntity getVerificationToken(String VerificationToken) {
		return verificationTokenRepository.findByToken(VerificationToken);
	}

}
