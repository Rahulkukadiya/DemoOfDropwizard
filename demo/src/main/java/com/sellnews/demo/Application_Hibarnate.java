package com.sellnews.demo;

import java.util.Optional;

import org.apache.log4j.spi.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Application_Hibarnate extends Application<ApplicationConfi>{

	/*
	 * hibarnate bundle
	 */
	
	private final  HibernateBundle<ApplicationConfi> hibernateBundle=new HibernateBundle<ApplicationConfi>(Employe.class) {
	public DataSourceFactory getDataSourceFactory(ApplicationConfi confi)
	{
		return confi.getDataSourceFactory();
	}
	};
	@Override
	public void run(ApplicationConfi confi, Environment enviroment) throws Exception {
		// TODO Auto-generated method stub
		final EmployeDao employe_Dao=new EmployeDao(hibernateBundle.getSessionFactory());
		enviroment.jersey().register(new ApplicationResource(employe_Dao));
		
	}
	public void initialize(final Bootstrap<ApplicationConfi> bootstrap)
	{
		bootstrap.addBundle(hibernateBundle);
	}
	public static  void main(String args[])
	{
		try {
			LoggerContext context=(LoggerContext)org.slf4j.LoggerFactory.getILoggerFactory();
			context.reset();
			ContextInitializer initializer=new ContextInitializer(context);
			initializer.autoConfig();
			new Application_Hibarnate().run(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
