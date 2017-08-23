package org.celebino.persistence.hibernate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		HibernateUtil.getSessionFactory();
	}

	public void contextDestroyed(ServletContextEvent event) {
		HibernateUtil.shutdown();
	}
}
