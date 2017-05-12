package com.goveca.configuration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mongodb.Mongo;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;

@Configuration
@EnableMongoRepositories(basePackages="com.goveca.repository")
@EnableTransactionManagement
public class MongoConfig {

	@Value("${mongo.port}")
	private int port;
	@Value("${mongo.ip}")
	private String host;
	@Value("${mongo.name}")
	private String db;
	@Value("${mongo.user}")
	private String userName;
	@Value("${mongo.pass}")
	private String passwd;
	@Value("${mongo.connection.per.host}")
	private int connPerHost;
	@Value("${mongo.threads.allowed.to.block.for.connection.multiplier}")
	private int threadBlockMultipler;
	@Value("${mongo.connect.timeout}")
	private int connectionTimeout;
	@Value("${mongo.max.wait.time}")
	private int mongoMaxWait;
	@Value("${mongo.socket.keep.alive}")
	private boolean keepAlive;
	@Value("${mongo.socket.timeout}")
	private int socketTimeout;

	public @Bean MongoClientFactoryBean mongo() {
		MongoClientFactoryBean mongo = new MongoClientFactoryBean();
		System.out.println(host);
		System.out.println(passwd);
		mongo.setHost(host);
		mongo.setPort(port);
		if (StringUtils.isNotEmpty(host) && StringUtils.isNotEmpty(passwd)) {
			MongoCredential mongoCredential = MongoCredential.createCredential(userName, db, passwd.toCharArray());
			mongo.setCredentials(new MongoCredential[] { mongoCredential });
		}
		mongo.setMongoClientOptions(mongoClientOptions());
		return mongo;
	}

	private MongoClientOptions mongoClientOptions() {
		MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(connPerHost).connectTimeout(connectionTimeout).socketTimeout(socketTimeout).threadsAllowedToBlockForConnectionMultiplier(threadBlockMultipler).maxWaitTime(mongoMaxWait).socketKeepAlive(keepAlive).build();
		return options;
	}

	public @Bean MongoTemplate mongoTemplate(Mongo mongo) throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongo, db);
		return mongoTemplate;
	}
	


}
