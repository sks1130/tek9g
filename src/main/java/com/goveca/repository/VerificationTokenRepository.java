package com.goveca.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.goveca.Entity.AccountEntity;
import com.goveca.Entity.VerificationTokenEntity;

public interface VerificationTokenRepository extends MongoRepository<VerificationTokenEntity, Long>{

	VerificationTokenEntity findByToken(String token);
	 
	VerificationTokenEntity findByAccount(AccountEntity account);
}
