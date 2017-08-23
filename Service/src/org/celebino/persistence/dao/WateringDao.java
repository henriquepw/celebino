package org.celebino.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import org.celebino.persistence.hibernate.HibernateUtil;
import org.celebino.persistence.model.Watering;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class WateringDao extends GenericDao<Long, Watering> {

	private static WateringDao instance;

	public static WateringDao getInstance() {
		instance = new WateringDao();
		return instance;
	}

	@Override
	public List<Watering> getAll() throws SQLException {
		return super.getAll("Watering.getAll");

	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return Watering.class;
	}

	@Override
	public Watering find(Watering entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get watering by garden's id
	 * 
	 * @param gardenId
	 * @return
	 * @throws SQLException
	 */
	public List<Watering> getByGardenId(Long gardenId) throws SQLException {

		Session session = HibernateUtil.getSessionFactory().openSession();

		List<Watering> waterings = null;

		try {

			String hql = "from Watering as w" + " where w.garden.id like :gardenId";

			Query query = session.createQuery(hql);
			query.setParameter("gardenId", gardenId);

			waterings = (List<Watering>) query.list();

		} catch (HibernateException hibernateException) {

			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {

			session.close();
		}

		return waterings;
	}

}
