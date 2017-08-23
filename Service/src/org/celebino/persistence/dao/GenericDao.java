package org.celebino.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.celebino.persistence.hibernate.HibernateUtil;
import org.celebino.persistence.model.Garden;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

@SuppressWarnings("deprecation")
public abstract class GenericDao<PK, T> {

	private static Logger logger = LogManager.getLogger(GenericDao.class);

	public abstract List<T> getAll() throws SQLException;

	public abstract Class<?> getEntityClass();

	public Long insertU(T entity) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Long id;

		try {
			session.beginTransaction();
			id = (Long) session.save(entity);
			session.getTransaction().commit();

		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {
			session.close();
		}

		return id;
	}

	public Long insert(T entity) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Long id;

		try {
			session.beginTransaction();
			id = (Long) session.merge(entity);
			session.getTransaction().commit();

		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {
			session.close();
		}

		return id;
	}

	public boolean insertOrUpdate(T entity) throws SQLException {
		logger.info("Init abstract Insert to: " + entity.getClass());

		Session session = HibernateUtil.getSessionFactory().openSession();

		boolean success = false;

		try {
			session.beginTransaction();
			session.saveOrUpdate(entity);
			session.getTransaction().commit();

			success = true;

		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {
			session.close();
		}

		return success;
	}

	public void update(T entity) throws SQLException {
		logger.info("Init abstract Update to: " + entity.getClass());

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			session.beginTransaction();
			session.update(entity);
			session.getTransaction().commit();

		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {
			session.close();
		}
	}

	public void delete(T entity) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			session.beginTransaction();
			session.delete(entity);
			session.getTransaction().commit();

		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {
			session.close();
		}
	}

	public int delete(String fieldIdName, String fieldIdValue) throws SQLException {
		int resultado = 0;

		String className = getEntityClass().getName();

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			session.beginTransaction();

			Query query = session.createQuery("delete " + className + " where " + fieldIdName + " = :fieldValue");

			query.setParameter("fieldValue", fieldIdValue);

			resultado = query.executeUpdate();

			session.getTransaction().commit();

		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {
			session.close();
		}

		return resultado;
	}

	public boolean delete(Long id) throws SQLException {
		boolean isDelete = false;

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			session.beginTransaction();

			T entity = (T) session.load(getEntityClass(), id);
			session.delete(entity);

			// This makes the pending delete to be done
			session.getTransaction().commit();

			isDelete = true;

		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {
			session.close();
		}

		return isDelete;
	}

	public List<T> getAll(String namedQuery) throws SQLException {
		logger.info("Init abstract GetAll to: " + namedQuery);

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<T> list = null;

		try {
			session.beginTransaction();
			Query query = session.getNamedQuery(namedQuery);
			list = (List<T>) query.list();
			session.getTransaction().commit();

		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {
			session.close();
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public T getById(Long pk) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		T entity = null;

		try {
			session.beginTransaction();
			entity = (T) session.get(getEntityClass(), pk);
			Hibernate.initialize(entity);
			session.getTransaction().commit();

		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {
			session.close();
		}

		return entity;
	}

	public abstract T find(T entity) throws SQLException;
}
