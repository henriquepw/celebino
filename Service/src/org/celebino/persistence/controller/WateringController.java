package org.celebino.persistence.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.celebino.persistence.dao.WateringDao;
import org.celebino.persistence.model.Watering;

@Path("watering")
public class WateringController {

	/**
	 * Cadastra watering
	 * 
	 * @param Watering
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insert(Watering watering) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		Timestamp time = new Timestamp(System.currentTimeMillis());
		watering.setTime(time);
		try {

			Long idWatering = (long) WateringDao.getInstance().insert(watering);
			watering.setId(idWatering);
			builder.status(Response.Status.OK).entity(watering);

		} catch (SQLException e) {

			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return builder.build();
	}

	/**
	 * retorna todos os waterings.
	 * 
	 * @return Response
	 */
	@PermitAll
	@GET
	@Path("/")
	@Produces("application/json")
	public List<Watering> getAll() {

		List<Watering> waterings = new ArrayList<Watering>();

		try {

			waterings = WateringDao.getInstance().getAll();

		} catch (SQLException e) {
			// TRATAR EXCECAO
		}

		return waterings;
	}

	/**
	 * Retorna watering atraves do Id
	 * 
	 * @param idWatering
	 * @return Response
	 */
	@PermitAll
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getWateringById(@PathParam("id") Long idWatering) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Watering watering = WateringDao.getInstance().getById(idWatering);

			if (watering != null) {

				builder.status(Response.Status.OK);
				builder.entity(watering);

			} else {

				builder.status(Response.Status.NOT_FOUND);
			}

		} catch (SQLException exception) {

			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return builder.build();
	}

	/**
	 * Get watering by garden's id
	 * 
	 * @param gardenId
	 * @return
	 */
	@PermitAll
	@GET
	@Path("/garden/{gardenId}")
	@Produces("application/json")
	public Response getWateringByGardenId(@PathParam("gardenId") Long gardenId) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Watering> waterings = WateringDao.getInstance().getByGardenId(gardenId);

			if (waterings != null) {

				builder.status(Response.Status.OK);
				builder.entity(waterings);
			} else {

				builder.status(Response.Status.NOT_FOUND);
			}

		} catch (SQLException exception) {

			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return builder.build();
	}

}
