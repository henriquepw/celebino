package org.celebino.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import org.celebino.persistence.hibernate.HibernateUtil;
import org.celebino.persistence.model.Garden;
import org.celebino.persistence.model.GardenStatus;
import org.celebino.persistence.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class GardenStatusDao extends GenericDao<Long, GardenStatus> {

	private static GardenStatusDao instance;

	public static GardenStatusDao getInstance() {
		instance = new GardenStatusDao();
		return instance;
	}

	@Override
	public List<GardenStatus> getAll() throws SQLException {
		return super.getAll("GardenStatus.getAll");
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return GardenStatus.class;
	}

	@Override
	public GardenStatus find(GardenStatus entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get garden status by garden id
	 * 
	 * @param gardenId
	 * @return
	 * @throws SQLException
	 */
	public List<GardenStatus> getByGardenId(Long gardenId) throws SQLException {

		Session session = HibernateUtil.getSessionFactory().openSession();

		List<GardenStatus> gardenstatus = null;

		try {

			String hql = "from GardenStatus as g" + " where g.garden.id like :gardenId";

			Query query = session.createQuery(hql);
			query.setParameter("gardenId", gardenId);

			gardenstatus = (List<GardenStatus>) query.list();

		} catch (HibernateException hibernateException) {

			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {

			session.close();
		}

		return gardenstatus;
	}

}
