package org.celebino.persistence.controller;

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
import org.celebino.persistence.dao.GardenDao;
import org.celebino.persistence.model.AirConditioning;
import org.celebino.persistence.model.Garden;

@Path("garden")
public class GardenController {

	/**
	 * Cadastra o jardim
	 * 
	 * @param User
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insert(Garden garden) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {
			Long id = (Long) GardenDao.getInstance().insertU(garden);
			garden.setId(id);
			builder.status(Response.Status.OK).entity(garden);

		} catch (SQLException e) {
			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return builder.build();
	}

	/**
	 * retorna todos os jardins.
	 * 
	 * @return Response
	 */
	@PermitAll
	@GET
	@Path("/")
	@Produces("application/json")
	public List<Garden> getAll() {
		List<Garden> gardens = new ArrayList<Garden>();

		try {
			gardens = GardenDao.getInstance().getAll();
		} catch (SQLException e) {
			// TRATAR EXCECAO
		}

		return gardens;
	}

	/**
	 * Get Garden by Id
	 * 
	 * @param idGarden
	 * @return Response
	 */
	@PermitAll
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getGardenById(@PathParam("id") Long idGarden) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {
			Garden garden = GardenDao.getInstance().getById(idGarden);

			if (garden != null) {

				builder.status(Response.Status.OK);
				builder.entity(garden);
			} else {

				builder.status(Response.Status.NOT_FOUND);
			}

		} catch (SQLException exception) {
			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return builder.build();
	}

	/**
	 * Get garden by username's user
	 * 
	 * @param username
	 * @return Response
	 */
	@PermitAll
	@GET
	@Path("/username/{username}")
	@Produces("application/json")
	public Response getGardensByUsername(@PathParam("username") String username) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {
			List<Garden> gardens = GardenDao.getInstance().getByUsername(username);

			if (gardens != null) {
				builder.status(Response.Status.OK);
				builder.entity(gardens);
				
			} else {
				builder.status(Response.Status.NOT_FOUND);
			}

		} catch (SQLException exception) {
			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return builder.build();
	}

	/**
	 * Get gardens by user id
	 * 
	 * @param userId
	 * @return
	 */
	@PermitAll
	@GET
	@Path("/user/{id}")
	@Produces("application/json")
	public Response getGardensByUserId(@PathParam("id") Long userId) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {
			List<Garden> gardens = GardenDao.getInstance().getByUserId(userId);

			if (gardens != null) {
				builder.status(Response.Status.OK);
				builder.entity(gardens);
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
	public Response update(Garden graden) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {
			GardenDao.getInstance().update(graden);
			builder.status(Response.Status.OK).entity(graden);

		} catch (SQLException exception) {
			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return builder.build();

	}

}
