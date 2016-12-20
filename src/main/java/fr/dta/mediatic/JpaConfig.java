package fr.dta.mediatic;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class JpaConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
	BoneCPDataSource ds = new BoneCPDataSource();
	ds.setDriverClass(env.getRequiredProperty("hibernate.driverClassName"));
	ds.setJdbcUrl(env.getRequiredProperty("hibernate.url"));
	ds.setUsername(env.getRequiredProperty("hibernate.username"));
	ds.setPassword(env.getRequiredProperty("hibernate.password"));
	ds.setMaxConnectionsPerPartition(100);
	ds.setMinConnectionsPerPartition(1);
	ds.setPartitionCount(2);
	ds.setConnectionTestStatement("select 1");
	return ds;
    }

    @Bean(name="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
	emf.setDataSource(dataSource());
	emf.setPackagesToScan(new String[] { "fr.dta.mediatic.model" });
	JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	emf.setJpaVendorAdapter(vendorAdapter);
	emf.setJpaProperties(additionalProperties());
	return emf;
    }

    private Properties additionalProperties() {
	Properties properties = new Properties();
	properties.setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
	properties.setProperty("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
	properties.setProperty("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
	properties.setProperty("hibernate.use_second_level_cache", env.getRequiredProperty("hibernate.cache.use_second_level_cache"));
	return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(javax.persistence.EntityManagerFactory emf) {
	JpaTransactionManager transactionManager = new JpaTransactionManager();
	transactionManager.setEntityManagerFactory(emf);
	return transactionManager;
    }

}
