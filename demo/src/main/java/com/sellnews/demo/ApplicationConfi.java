package com.sellnews.demo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class ApplicationConfi extends Configuration {
	/*
	 * datasourcefactory use thay relational database sathe connect karva.factory dropwizard use kre because different parameter nu group bnave like usename password connection url..
	 */
	@NotNull
	@Valid
	private DataSourceFactory dataSourceFactory =new DataSourceFactory();
	/*
	 * @return a getter method use krine datasourcefactory no object yml file je argument ma api hoy ama thi deserallze krine le...
	 */
	@JsonProperty("database")
	public DataSourceFactory getDataSourceFactory()
	{
		return dataSourceFactory;
	}
}
