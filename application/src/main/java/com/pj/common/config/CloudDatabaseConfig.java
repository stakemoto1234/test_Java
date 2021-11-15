package com.pj.common.config;

import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.pivotal.cfenv.core.CfCredentials;
import io.pivotal.cfenv.jdbc.CfJdbcEnv;

import com.zaxxer.hikari.HikariDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@Profile("cloud")
public class CloudDatabaseConfig extends AbstractCloudConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(CloudDatabaseConfig.class);

    @Bean
    public DataSource dataSource(){
        
        CfJdbcEnv cfJdbcEnv = new CfJdbcEnv();
		CfCredentials hanaCredentials = cfJdbcEnv.findCredentialsByTag("hana");

        String url = hanaCredentials.getUri("hana");
        String user = hanaCredentials.getUsername();
        String password = hanaCredentials.getPassword();

        /* for trial */
        logger.info("hana url - " + url);
        logger.info("user - " + user);
        logger.info("password - " + password);
            
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .driverClassName(com.sap.db.jdbc.Driver.class.getName())
                .url(url)
                .username(user)
                .password(password)
                .build();
    }

    /*
    @Bean
    public DataSource dataSource(@Value("${hana.url}") final String url,
        @Value("${hana.user}") final String user,
        @Value("${hana.password}") final String password) {

        return DataSourceBuilder.create()
        .type(HikariDataSource.class)
        .driverClassName(com.sap.db.jdbc.Driver.class.getName())
        .url(url)
        .username(user)
        .password(password)
        .build();
    }
    */

    /*
        @Bean
    public DataSource dataSource(@Value("jdbc:sap://c9690c1c-91ff-4d3f-9ec3-1f1c3fb3ae47.hana.trial-eu10.hanacloud.ondemand.com:443?encrypt=true&validateCertificate=true&currentschema=EXTS4H_HDI") final String url,
        @Value("EXTS4H_HDI_DVD7I5382154MPGEZ3TIXYVBG_RT") final String user,
        @Value("Lj9tPjRvvnWOXJ4jgL1Q69IIUykYb91wN4qyPQdT-TuRxsCmWUQcN.ssQTV9zVVB0tcQ6eZJEpaeTAqpa5ZZkM92Bgc9CFouBwB3YRdBXfUxg6KUm2kZ7xis6tEu6kCY") final String password) {

        return DataSourceBuilder.create()
        .type(HikariDataSource.class)
        .driverClassName(com.sap.db.jdbc.Driver.class.getName())
        .url(url)
        .username(user)
        .password(password)
        .build();
    }
    */
}