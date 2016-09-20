package com.sellnews.demo;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import io.dropwizard.hibernate.AbstractDAO;

public class EmployeDao extends AbstractDAO<Employe> {
	private final static Logger LOGGER = Logger.getLogger(EmployeDao.class.getName());

	public EmployeDao(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	public List<Employe> findAll() {
		List<Employe> emp_details = new ArrayList<Employe>();
		try {
			emp_details = currentSession().createCriteria(Employe.class).add(Restrictions.ilike("emp_Status", "true"))
					.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
							.add(Projections.property("firstname"), "firstname")
							.add(Projections.property("phone"), "phone")
							.add(Projections.property("lastname"), "lastname")
							.add(Projections.property("e_mail"), "e_mail")
							.add(Projections.property("position"), "position"))
					.list();
			// LOGGER.info("Data is retrival");
		} catch (HibernateException exception) {
			LOGGER.error(exception);

		}
		return emp_details;
	}

	public String insert_empdetails(EmployePojo employepojo) {
		try {

			Employe employe = new Employe();
			if (employepojo.getE_mail() == null) {
				LOGGER.info("data is entered");
				BeanUtils.copyProperties(employe, employepojo);
				employe.setEmp_registerDate(CurrentTimeStamp.generateTimeStamp());
				employe.setLastModified(CurrentTimeStamp.generateTimeStamp());
				currentSession().save(employe);
				LOGGER.info("data is entered");
				return ConstantVariabls.VALID_STATUS;
			} else if (VerificationClass.verificationOfEmail(employepojo.getE_mail())) {
				BeanUtils.copyProperties(employe, employepojo);
				employe.setEmp_registerDate(CurrentTimeStamp.generateTimeStamp());
				employe.setLastModified(CurrentTimeStamp.generateTimeStamp());
				currentSession().save(employe);
				LOGGER.info("data is entered");
				return ConstantVariabls.VALID_STATUS;
			} else {
				return ConstantVariabls.INVALID_EMAIL;
			}
		} catch (IllegalAccessException exception) {

			LOGGER.error(exception);
			return ConstantVariabls.ERRORS;
		} catch (InvocationTargetException exception) {
			// TODO Auto-generated catch block
			LOGGER.error(exception);
			return ConstantVariabls.ERRORS;
		}
	}

	public EmployePojo getDataById(long id) {
		Employe emp_details = null;
		EmployePojo employePojo = null;
		try {
			emp_details = (Employe) currentSession().createCriteria(Employe.class).add(Restrictions.eq("id", id)).list()
					.get(0);
			if ("true".equals(emp_details.getEmp_Status())) {
				EmployePojo emp_detail = (EmployePojo) currentSession().createCriteria(Employe.class)
						.add(Restrictions.eq("id", id))
						.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
								.add(Projections.property("firstname"), "firstname")
								.add(Projections.property("phone"), "phone")
								.add(Projections.property("lastname"), "lastname")
								.add(Projections.property("e_mail"), "e_mail")
								.add(Projections.property("position"), "position"))
						.setResultTransformer(Transformers.aliasToBean(EmployePojo.class)).list().get(0);
				LOGGER.info("Data Sucessfully get by id");
				return emp_detail;
			} else {
				LOGGER.error(ConstantVariabls.DATA_NOT_FOUND);
				return employePojo;
			}
		} catch (HibernateException hibernateException) {
			LOGGER.error(hibernateException);
			return employePojo;
		}
	}

	public String removeUser(Long id) {
		try {
			Employe employe = (Employe) currentSession().createCriteria(Employe.class).add(Restrictions.eq("id", id))
					.list().get(0);
			if ("true".equals(employe.getEmp_Status())) {
				employe.setEmp_Status("false");
				currentSession().update(employe);
				LOGGER.info("Data Sucessfully removed");
				return ConstantVariabls.VALID_STATUS;
			} else {
				return ConstantVariabls.USER_NOT_FOUND;
			}
		} catch (Exception exception) {
			LOGGER.error(exception);
			return ConstantVariabls.ERRORS;
		}
	}

	public String update_User(Long id, EmployePojo new_employe) {
		try {
			Employe employe = (Employe) currentSession().createCriteria(Employe.class).add(Restrictions.eq("id", id))
					.list().get(0);

			if (("true".equals(employe.getEmp_Status()))) {
				if (((new_employe.getE_mail() != null))) {
					if (VerificationClass.verificationOfEmail(new_employe.getE_mail()))
						employe.setE_mail(new_employe.getE_mail());
					else
						return ConstantVariabls.INVALID_EMAIL;
				}
				if ((new_employe.getFirstname() != null)) {
					employe.setFirstname(new_employe.getFirstname());
				}
				if ((new_employe.getLastname() != null)) {
					employe.setLastname(new_employe.getLastname());
				}
				if ((new_employe.getPhone() != null)) {
					employe.setPhone(new_employe.getPhone());
				}
				if ((new_employe.getPosition() != null)) {
					employe.setPosition(new_employe.getPosition());
				}
				LOGGER.info(CurrentTimeStamp.generateTimeStamp());
				employe.setLastModified(CurrentTimeStamp.generateTimeStamp());
				LOGGER.info(CurrentTimeStamp.generateTimeStamp());
				currentSession().update(employe);
				LOGGER.info("Data Sucessfully updated");
				return ConstantVariabls.VALID_STATUS;
			} else {
				return ConstantVariabls.USER_NOT_FOUND;
			}
		} catch (Exception exception) {
			LOGGER.error(exception);
			return ConstantVariabls.ERRORS;
		}
	}

	public List<Employe> getUser(LastModifiedData lastModifiedData) {
		List<Employe> employelist = null;
		try {
			LOGGER.info("jkl");
			/*employelist = currentSession().createCriteria(Employe.class).add(Restrictions.between("lastModified",
					lastModifiedData.getStartdate(), lastModifiedData.getLastdate())).list();*/
			employelist=currentSession().createCriteria(Employe.class).add(Restrictions.gt("lastModified",
					lastModifiedData.getStartdate())).add((Restrictions.lt("lastModified",lastModifiedData.getLastdate()))).list();
		} catch (HibernateException exception) {
			// TODO Auto-generated catch block
			LOGGER.error(exception.getMessage());
			return employelist;
		}
		return employelist;
	}

}
