package lk.ijse.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "lk.ijse.spring.repo")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class JPAConfig {

    @Autowired
     Environment env;

@Bean
public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds, JpaVendorAdapter va){
    LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
    bean.setJpaVendorAdapter(va);
    bean.setDataSource(ds);
    bean.setPackagesToScan(env.getRequiredProperty("entity.package.name"));
    return bean;

}
@Bean
public DataSource dataSource() throws NamingException {
  /* DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUrl(env.getProperty("my.app.url"));
    dataSource.setUsername(env.getProperty("my.app.username"));
    dataSource.setPassword(env.getProperty("my.app.password"));
    dataSource.setDriverClassName(env.getProperty("my.app.driverClassname"));
    return dataSource;*/

    return (DataSource) new JndiTemplate().lookup("java:comp/env/jdbc/pool");
}



@Bean
public JpaVendorAdapter jpaVendorAdapter(){
    HibernateJpaVendorAdapter vendor =new HibernateJpaVendorAdapter();
    vendor.setDatabasePlatform(env.getProperty("my.app.dialect"));
    vendor.setDatabase(Database.MYSQL);
    vendor.setShowSql(true);
    vendor.setGenerateDdl(true);
    return vendor;
}

@Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
    return new JpaTransactionManager(emf);

}
}
