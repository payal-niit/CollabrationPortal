package com.userportal.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.userportal.daoimpl.BlogCommentDAOImpl;
import com.userportal.daoimpl.BlogDAOImpl;
import com.userportal.daoimpl.FriendDAOImpl;
import com.userportal.daoimpl.UserDAOImpl;
import com.userportal.model.Blog;
import com.userportal.model.BlogComment;
import com.userportal.model.Category;
import com.userportal.model.Forum;
import com.userportal.model.Friend;
import com.userportal.model.SubCategory;
import com.userportal.model.User;


@Configuration
@ComponentScan("com.userportal")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	@Bean(name = "dataSource")
	public DataSource getH2DataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/UserPortal");

		dataSource.setUsername("sa");
		dataSource.setPassword("");
		
		Properties connectionProperties = new Properties();
		connectionProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		connectionProperties.setProperty("hibernate.show_sql", "true");
		connectionProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		connectionProperties.setProperty("hibernate.use_sql_comments", "true");
		return dataSource;
	}
      
    
    private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.hbm2ddl.auto", "update");
    	properties.put("hibernate.use_sql_comments", "true");
    	//properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    	return properties;
    }
    
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    	sessionBuilder.addProperties(getHibernateProperties());
    	sessionBuilder.addAnnotatedClasses(Blog.class);
    	sessionBuilder.addAnnotatedClasses(User.class);
    	sessionBuilder.addAnnotatedClasses(Friend.class);
    	sessionBuilder.addAnnotatedClasses(BlogComment.class);
    	sessionBuilder.addAnnotatedClasses(Forum.class);
    	sessionBuilder.addAnnotatedClasses(Category.class);
    	sessionBuilder.addAnnotatedClasses(SubCategory.class);
    	
    	return sessionBuilder.buildSessionFactory();
    }
    
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
				sessionFactory);

		return transactionManager;
	}
	
	@Autowired
    @Bean(name = "blogDAO")
    public BlogDAOImpl getBlogDao(SessionFactory sessionFactory) {
    	return new BlogDAOImpl(sessionFactory);
    }
	
	@Autowired
    @Bean(name = "userDAO")
    public UserDAOImpl getUserDao(SessionFactory sessionFactory) {
    	return new UserDAOImpl(sessionFactory);
    }
	
	@Autowired
    @Bean(name = "friendDAO")
    public FriendDAOImpl getFriendDao(SessionFactory sessionFactory) {
    	return new FriendDAOImpl(sessionFactory);
    }
	
	@Autowired
    @Bean(name = "blogCommentDAO")
    public BlogCommentDAOImpl getBlogCommentDao(SessionFactory sessionFactory) {
    	return new BlogCommentDAOImpl(sessionFactory);
    }
	
	@Bean
    public CommonsMultipartResolver multipartResolver() {
        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }
	
	/*@Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }*/
	
	

}

