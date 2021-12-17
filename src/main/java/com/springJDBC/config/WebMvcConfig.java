package com.springJDBC.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.springJDBC.dao.StudentDao;
import com.springJDBC.dao.StudentDaoImpl;

@Configuration
@EnableWebMvc
@ComponentScan("com.springJDBC.*")
public class WebMvcConfig {
	
	@Bean
	InternalResourceViewResolver viewResolver() {

		InternalResourceViewResolver vr = new InternalResourceViewResolver();

		vr.setPrefix("/");
		vr.setSuffix(".jsp");
		return vr;
	}

	@Bean
	DriverManagerDataSource getDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/sampledb");
		ds.setUsername("root");
		ds.setPassword("manah");

		return ds;
	}

	@Bean
	public StudentDao getUserDao() {
		return new StudentDaoImpl(getDataSource());
	}
}
