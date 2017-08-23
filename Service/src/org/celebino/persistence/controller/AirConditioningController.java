package org.celebino.persistence.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.celebino.persistence.dao.AirConditioningDao;
import org.celebino.persistence.dao.UserDao;
import org.celebino.persistence.model.AirConditioning;
import org.celebino.persistence.model.Login;
import org.celebino.persistence.model.User;
import org.hibernate.HibernateException;

@Path("airconditioning")
public class AirConditioningController {

	/**
	 * Cadastrar air
	 * 
	 * @param AirConditioning
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insert(AirConditioning air) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {
			Long idAir = (long) AirConditioningDao.getInstance().insertU(air);
			air.setId(idAir);
			builder.status(Response.Status.OK).entity(air);

		} catch (SQLException e) {
			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return builder.build();
	}

	/**
	 * Return all air.
	 * 
	 * @return Response
	 */
	@PermitAll
	@GET
	@Path("/")
	@Produces("application/json")
	public List<AirConditioning> getAll() {
		List<AirConditioning> airConditionings = new ArrayList<>();

		try {
			airConditionings = AirConditioningDao.getInstance().getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return airConditionings;
	}

	/**
	 * Return air by id
	 * 
	 * @param id
	 * @return Response
	 */
	@PermitAll
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getAirConditioningById(@PathParam("id") Long idAirConditioning) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {
			AirConditioning air = AirConditioningDao.getInstance().getById(idAirConditioning);

			if (air != null) {
				builder.status(Response.Status.OK);
				builder.entity(air);

			} else {
				builder.status(Response.Status.NOT_FOUND);
			}

		} catch (SQLException exception) {
			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return builder.build();
	}

	@PermitAll
	@PUT
	@Path("/")
	@Produces("application/json")
	@Consumes("application/json")
	public Response update(AirConditioning air) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {
			AirConditioningDao.getInstance().update(air);
			builder.status(Response.Status.OK).entity(air);

		} catch (SQLException exception) {
			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return builder.build();

	}
}
