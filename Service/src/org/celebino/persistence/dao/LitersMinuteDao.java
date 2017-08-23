package org.celebino.persistence.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Date;

import org.celebino.persistence.hibernate.HibernateUtil;
import org.celebino.persistence.model.LitersMinute;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class LitersMinuteDao extends GenericDao<Long, LitersMinute> {

	private static LitersMinuteDao instance;

	public static LitersMinuteDao getInstance() {
		instance = new LitersMinuteDao();
		return instance;
	}

	@Override
	public List<LitersMinute> getAll() throws SQLException {
		return super.getAll("LitersMinute.getAll");
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return LitersMinute.class;
	}

	@Override
	public LitersMinute find(LitersMinute entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LitersMinute> getByAirId(Long idair) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<LitersMinute> lmin = null;

		try {
			String hql = "from LitersMinute as l" + " where l.airconditioning.id like :id";

			Query query = session.createQuery(hql);
			query.setParameter("id", idair);

			lmin = (List<LitersMinute>) query.list();

		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {
			session.close();
		}

		return lmin;
	}
}
