package com.agrarco.agrovestapi.Configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
@Configuration
@EnableJpaRepositories(
        basePackages = "com.agrarco.agrovestapi.AgrovestRepository",
        entityManagerFactoryRef = "agrovestEntityManagerFactory",
        transactionManagerRef = "agrovestTransactionManager"
)
public class AgrovestDataSourceConfig {

    @Bean(name = "agrovestDataSource")
    @ConfigurationProperties(prefix = "app.datasource.agrovest")
    public DataSource agrovestDataSource() {
        return org.springframework.boot.jdbc.DataSourceBuilder.create().build();
    }

    @Bean(name = "agrovestEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean agrovestEntityManagerFactory(
            @Qualifier("agrovestDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.agrarco.agrovestapi.AgrovestEntity");
        emf.setPersistenceUnitName("agrovest");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.SQLServerDialect");
        emf.setJpaVendorAdapter(vendorAdapter);

        return emf;
    }

    @Bean(name = "agrovestTransactionManager")
    public JpaTransactionManager agrovestTransactionManager(
            @Qualifier("agrovestEntityManagerFactory") LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(emf.getObject());
    }
}
