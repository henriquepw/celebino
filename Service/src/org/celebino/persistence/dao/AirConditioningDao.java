package org.celebino.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import org.celebino.persistence.hibernate.HibernateUtil;
import org.celebino.persistence.model.AirConditioning;
import org.celebino.persistence.model.Garden;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class AirConditioningDao extends GenericDao<Long, AirConditioning> {

	private static AirConditioningDao instance;

	public static AirConditioningDao getInstance() {
		instance = new AirConditioningDao();
		return instance;
	}

	@Override
	public List<AirConditioning> getAll() throws SQLException {
		return super.getAll("AirConditioning.getAll");
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return AirConditioning.class;
	}

	@Override
	public AirConditioning find(AirConditioning entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
