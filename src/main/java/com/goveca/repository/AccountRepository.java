package com.goveca.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.goveca.Entity.AccountEntity;

public interface AccountRepository extends MongoRepository<AccountEntity, Long>{
	
	AccountEntity findByEmail(String email);

}
