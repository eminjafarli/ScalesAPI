package com.agrarco.agrovestapi.Configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
@Configuration
@EnableJpaRepositories(
        basePackages = "com.agrarco.agrovestapi.ScalesRepository",
        entityManagerFactoryRef = "scalesEntityManagerFactory",
        transactionManagerRef = "scalesTransactionManager"
)
public class ScalesDataSourceConfig {

    @Bean(name = "scalesDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource scalesDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "scalesEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean scalesEntityManagerFactory(
            @Qualifier("scalesDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.agrarco.agrovestapi.ScalesEntity");
        emf.setPersistenceUnitName("scales");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.SQLServerDialect");
        emf.setJpaVendorAdapter(vendorAdapter);

        return emf;
    }

    @Bean(name = "scalesTransactionManager")
    public JpaTransactionManager scalesTransactionManager(
            @Qualifier("scalesEntityManagerFactory") LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(emf.getObject());
    }
}
