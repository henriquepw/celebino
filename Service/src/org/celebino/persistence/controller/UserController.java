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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.celebino.persistence.dao.UserDao;
import org.celebino.persistence.model.Login;
import org.celebino.persistence.model.User;
import org.hibernate.HibernateException;

@Path("user")
public class UserController {

	/**
	 * Cadastra usuario
	 * 
	 * @param User
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insert(User user) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Long idUser = (long) UserDao.getInstance().insertU(user);
			user.setId(idUser);
			builder.status(Response.Status.OK).entity(user);

		} catch (SQLException e) {

			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return builder.build();
	}

	/**
	 * retorna todos os usuarios.
	 * 
	 * @return Response
	 */
	@PermitAll
	@GET
	@Path("/")
	@Produces("application/json")
	public List<User> getAll() {

		List<User> users = new ArrayList<User>();

		try {

			users = UserDao.getInstance().getAll();

		} catch (SQLException e) {
			// TRATAR EXCECAO
		}

		return users;
	}

	/**
	 * Retorna usuario atraves do Id
	 * 
	 * @param idUser
	 * @return Response
	 */
	@PermitAll
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getUserById(@PathParam("id") Long idUser) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			User user = UserDao.getInstance().getById(idUser);

			if (user != null) {

				builder.status(Response.Status.OK);
				builder.entity(user);

			} else {

				builder.status(Response.Status.NOT_FOUND);
			}

		} catch (SQLException exception) {

			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return builder.build();
	}

	@PermitAll
	@GET
	@Path("/email/{email}")
	@Produces("application/json")
	public Response getUserByEmail(@PathParam("email") String email) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			User user = UserDao.getInstance().getByEmail(email);

			if (user != null) {

				builder.status(Response.Status.OK);
				builder.entity(user);

			} else {

				builder.status(Response.Status.NOT_FOUND);
			}

		} catch (SQLException exception) {

			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return builder.build();
	}

	/**
	 * Get user by username
	 * 
	 * @param username
	 * @return
	 */
	@PermitAll
	@GET
	@Path("/username/{username}")
	@Produces("application/json")
	public Response getUserByUsername(@PathParam("username") String username) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			User user = UserDao.getInstance().getByUsername(username);

			if (user != null) {

				builder.status(Response.Status.OK);
				builder.entity(user);

			} else {

				builder.status(Response.Status.NOT_FOUND);
			}

		} catch (SQLException exception) {

			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return builder.build();
	}

	/**
	 * Login
	 * 
	 * @param emailInput
	 * @param passwordInput
	 * @return
	 * @throws SQLException
	 */
	@PermitAll
	@POST
	@Path("/login")
	@Consumes("application/json")
	@Produces("application/json")
	public Response login(Login login) throws SQLException {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			User user = UserDao.getInstance().getByEmail(login.getEmail());

			if (user != null) {

				if (user.getPassword().equals(login.getPassword())) {
					builder.status(Response.Status.OK);
					builder.entity(user);
				} else {
					// Senha errada
					builder.status(Response.Status.UNAUTHORIZED);
				}

			} else {
				// Usuario nao encontrado
				builder.status(Response.Status.NOT_FOUND);
			}

		} catch (HibernateException exception) {

			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return builder.build();
	}

}
