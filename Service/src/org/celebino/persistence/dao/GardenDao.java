package org.celebino.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import org.celebino.persistence.hibernate.HibernateUtil;
import org.celebino.persistence.model.Garden;
import org.celebino.persistence.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class GardenDao extends GenericDao<Long, Garden> {

	private static GardenDao instance;

	public static GardenDao getInstance() {
		instance = new GardenDao();
		return instance;
	}

	@Override
	public List<Garden> getAll() throws SQLException {
		return super.getAll("Garden.getAll");
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return Garden.class;
	}

	@Override
	public Garden find(Garden entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * get garden by User id
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public List<Garden> getByUserId(Long userId) throws SQLException {

		Session session = HibernateUtil.getSessionFactory().openSession();

		List<Garden> gardens = null;

		try {

			String hql = "from Garden as g" + " where g.user.id like :userId";

			Query query = session.createQuery(hql);
			query.setParameter("userId", userId);

			gardens = (List<Garden>) query.list();

		} catch (HibernateException hibernateException) {

			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {

			session.close();
		}

		return gardens;
	}

	/**
	 * get garden by username
	 * 
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public List<Garden> getByUsername(String username) throws SQLException {

		Session session = HibernateUtil.getSessionFactory().openSession();

		List<Garden> gardens = null;

		try {

			String hql = "from Garden as g" + " where g.user.username like :username";

			Query query = session.createQuery(hql);
			query.setParameter("username", "%" + username + "%");

			gardens = (List<Garden>) query.list();

		} catch (HibernateException hibernateException) {

			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {

			session.close();
		}

		return gardens;
	}

}
