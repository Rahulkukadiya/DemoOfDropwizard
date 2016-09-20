package com.sellnews.demo;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class ConvertObject {
	public static Employe employe=new Employe();
	public static EmployePojo employePojo=new EmployePojo();
	public static Employe convertToEmploye(EmployePojo employePojoq) 
	{
		try {
			BeanUtils.copyProperties(employe, employePojoq);
			return employe;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			return employe;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return employe;
		}
		
	}
	public static EmployePojo convertToEmployePojo(Employe employe) 
	{
		try {
			BeanUtils.copyProperties(employePojo,employe);
			return employePojo;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return employePojo;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return employePojo;
		}
	}
}
