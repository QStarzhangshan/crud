package com.zs.CRUD_3.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

@Configuration
@ComponentScan
public class MyBatisconfig {
	@Bean
	public PageHelper pageHelper() {
	 PageHelper pageHelper = new PageHelper();
	    Properties p = new Properties();
	    p.setProperty("offsetAsPageNum", "true");
	    p.setProperty("rowBoundsWithCount", "true");
	    p.setProperty("reasonable", "true");
	    p.setProperty("dialect", "mysql");
	    p.setProperty("supportMethodsArguments", "false");
	     p.setProperty("pageSizeZero", "true");
	    pageHelper.setProperties(p);
	    return pageHelper;
	}
}