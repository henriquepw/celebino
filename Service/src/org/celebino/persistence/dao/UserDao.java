package org.celebino.persistence.dao;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import org.celebino.persistence.hibernate.HibernateUtil;
import org.celebino.persistence.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class UserDao extends GenericDao<Long, User> {

	private static UserDao instance;

	public static UserDao getInstance() {
		instance = new UserDao();
		return instance;
	}

	@Override
	public List<User> getAll() throws SQLException {
		return super.getAll("User.getAll");
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return User.class;
	}

	@Override
	public User find(User entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * get user by email
	 * 
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public User getByEmail(String email) throws SQLException {

		Session session = HibernateUtil.getSessionFactory().openSession();

		User user = null;

		try {

			String hql = "from User as a" + " where a.email like :email";

			Query query = session.createQuery(hql);
			query.setParameter("email", "%" + email + "%");

			user = (User) query.uniqueResult();

		} catch (HibernateException hibernateException) {

			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {

			session.close();
		}

		return user;
	}

	/**
	 * Get user by username
	 * 
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public User getByUsername(String username) throws SQLException {

		Session session = HibernateUtil.getSessionFactory().openSession();

		User user = null;

		try {

			String hql = "from User as a" + " where a.username like :username";

			Query query = session.createQuery(hql);
			query.setParameter("username", "%" + username + "%");

			user = (User) query.uniqueResult();

		} catch (HibernateException hibernateException) {

			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {

			session.close();
		}

		return user;
	}

	public User login(String email, String password) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		User user = null;

		try {

			String hql = "from User as u" + " where u.email = :email" + " and u.senha = :password";

			Query query = session.createQuery(hql);
			query.setParameter("email", email);
			query.setParameter("password", password);

			user = (User) query.uniqueResult();

		} catch (HibernateException hibernateException) {

			session.getTransaction().rollback();

		} finally {

			session.close();
		}

		return user;
	}

}
