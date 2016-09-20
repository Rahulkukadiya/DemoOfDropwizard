package com.sellnews.demo;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.jboss.logging.annotations.Pos;

import com.google.common.base.Optional;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ApplicationResource {
	private EmployeDao employeDao;
	private final static Logger LOGGER = Logger.getLogger(EmployeDao.class.getName());

	public ApplicationResource(EmployeDao employeDao) {
		this.employeDao = employeDao;
	}

	@GET
	@Path("/user")
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDataJ() {

		try {
			List<Employe> employe_data = employeDao.findAll();
			return Response.ok().entity(employe_data).build();
		} catch (Exception exception) {
			LOGGER.error(exception);
			return Response.serverError().entity(ResponseMap.generatMap(exception.getMessage())).build();
		}
	}

	@GET
	@Path("/user/{id}")
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") long id) {
		try {
			EmployePojo employe = employeDao.getDataById(id);
			if (employe != null) {
				return Response.ok(employe).build();
			} else {
				LOGGER.info("here");
				return Response.accepted().entity(ResponseMap.generatMap(ConstantVariabls.USER_NOT_FOUND)).build();
			}
		} catch (Exception exception) {
			LOGGER.error(exception);
			return Response.serverError().entity(ResponseMap.generatMap(exception.getMessage())).build();
		}
	}

	@POST
	@Path("/user")
	@Consumes(MediaType.APPLICATION_JSON)
	@UnitOfWork
	public Response insert_userJ(EmployePojo employe) {
		try {
			String result = employeDao.insert_empdetails(employe);
			return Response.status(201).entity(ResponseMap.generatMap(result)).build();
		} catch (Exception exception) {
			LOGGER.error("here");
			return Response.serverError().entity(ResponseMap.generatMap(exception.getMessage())).build();
		}

	}

	@DELETE
	@Path("/user/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@UnitOfWork
	public Response removeUser(@PathParam("id") long id) {
		try {
			String result = employeDao.removeUser(id);
			return Response.accepted().entity(ResponseMap.generatMap(result)).build();
		} catch (Exception exception) {
			LOGGER.error(exception);
			return Response.serverError().entity(ResponseMap.generatMap(exception.getMessage())).build();
		}
	}

	@PUT
	@Path("/user/{id}")
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	public Response edit_Data(@PathParam("id") long id, EmployePojo new_employe_Pojo) {
		try {
			String result = employeDao.update_User(id, new_employe_Pojo);
			return Response.accepted().entity(ResponseMap.generatMap(result)).build();
		} catch (Exception exception) {
			LOGGER.error(exception);
			return Response.serverError().entity(ResponseMap.generatMap(exception.getMessage())).build();
		}
	}

	@POST
	@Path("/admin")
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUserByDate(@QueryParam("startdate")Timestamp startdate,@QueryParam("lastdate")Timestamp lastdate) {
		try {
			LOGGER.info("here ...................");
			LastModifiedData lastModifiedData=new LastModifiedData();
			lastModifiedData.setLastdate(lastdate);
			lastModifiedData.setStartdate(startdate);
			List<Employe> user_data = employeDao.getUser(lastModifiedData);
			return Response.ok().entity(user_data).build();
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			LOGGER.error(exception);
			return Response.serverError().entity(ResponseMap.generatMap(exception.getMessage())).build();

		}

	}
}
