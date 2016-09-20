package com.sellnews.demo;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
	public static void main(String arg[]) {
		try {
			new HelloWorldApplication().run(arg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
    }


	@Override
	public void run(HelloWorldConfiguration arg0, Environment arg1) throws Exception {
		// TODO Auto-generated method stub
		HelloWorldResource resource = new HelloWorldResource(arg0.getTemplate(), arg0.getDefaultname());
		arg1.jersey().register(resource);
		arg1.jersey().register(new AnotherResource());
	}
}
