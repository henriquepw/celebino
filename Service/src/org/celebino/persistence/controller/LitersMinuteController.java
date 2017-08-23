package org.celebino.persistence.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import org.celebino.persistence.dao.GardenStatusDao;
import org.celebino.persistence.dao.LitersMinuteDao;
import org.celebino.persistence.dao.UserDao;
import org.celebino.persistence.model.AirConditioning;
import org.celebino.persistence.model.GardenStatus;
import org.celebino.persistence.model.LitersMinute;
import org.celebino.persistence.model.Login;
import org.celebino.persistence.model.User;
import org.hibernate.HibernateException;

@Path("litersminute")
public class LitersMinuteController {

	/**
	 * Cadastra litersminute
	 * 
	 * @param User
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insert(LitersMinute litersminute) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		Timestamp date = new Timestamp(System.currentTimeMillis());
		litersminute.setDate(date);

		try {
			AirConditioning air = AirConditioningDao.getInstance().getById(litersminute.getAirconditioning().getId());

			if (air.getId().equals(null)) {
				AirConditioningDao.getInstance().insertU(litersminute.getAirconditioning());
			} else {
				litersminute.setAirconditioning(air);
			}

			Long id = LitersMinuteDao.getInstance().insertU(litersminute);
			litersminute.setId(id);

			System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date.getTime()));
			System.out.println(date.getTime());
			builder.status(Response.Status.OK).entity(litersminute);

		} catch (SQLException e) {
			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return builder.build();
	}

	/**
	 * Return all lmin
	 * 
	 * @return Response
	 */
	@PermitAll
	@GET
	@Path("/")
	@Produces("application/json")
	public List<LitersMinute> getAll() {
		List<LitersMinute> lmin = new ArrayList<>();

		try {
			lmin = LitersMinuteDao.getInstance().getAll();

		} catch (SQLException e) {
			// TRATAR EXCECAO
		}

		return lmin;
	}

	/**
	 * Return all lmin by air
	 * 
	 * @return Response
	 */
	@PermitAll
	@GET
	@Path("/byair/{id}")
	@Produces("application/json")
	public Response getAirConditioningById(@PathParam("id") Long idAir) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {
			List<LitersMinute> lmin = LitersMinuteDao.getInstance().getByAirId(idAir);

			if (lmin != null) {
				builder.status(Response.Status.OK);
				builder.entity(lmin);

			} else {
				builder.status(Response.Status.NOT_FOUND);
			}

		} catch (SQLException exception) {
			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return builder.build();
	}

}
