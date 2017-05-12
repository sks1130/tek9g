package com.goveca.Entity;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "verificationToken")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationTokenEntity {
	private static final long serialVersionUID = 1L;
	private static final int EXPIRATION = 60 * 24;

	@Id
	private Long id;

	private String token;

	private AccountEntity account;

	private Date expiryDate;

	private Date calculateExpiryDate(int expiryTimeInMinutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Timestamp(cal.getTime().getTime()));
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}

	public VerificationTokenEntity(final String token, final AccountEntity account) {
		this.id = 0L;
		this.token = token;
		this.account = account;
		this.expiryDate = calculateExpiryDate(EXPIRATION);
	}

}
