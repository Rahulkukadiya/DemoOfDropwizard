package com.sellnews.demo;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class HelloWorldConfiguration extends Configuration{
	@NotEmpty
	private String template="klj";
	@NotEmpty
	private String defaultname;
	@JsonProperty
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	@JsonProperty
	public String getDefaultname() {
		return defaultname;
	}
	public void setDefaultname(String defaultname) {
		this.defaultname = defaultname;
	}
	
	

}
