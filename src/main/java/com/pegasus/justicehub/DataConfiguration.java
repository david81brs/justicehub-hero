package com.pegasus.justicehub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;


public class DataConfiguration {

    @Autowired
    Properties jap;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.postgresql.jdbc.Driver");
        dataSource.setUrl(jap.getJusticeAppProps().getUrl());
        dataSource.setUsername(jap.getJusticeAppProps().getUsername());
        dataSource.setPassword(jap.getJusticeAppProps().getPassword());
        return dataSource;

    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.POSTGRESQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.PostgresQLDialect");
        adapter.setPrepareConnection(true);
        return adapter;
    }

}
