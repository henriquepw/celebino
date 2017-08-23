package org.celebino.persistence.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;
import org.celebino.persistence.controller.AirConditioningController;
import org.celebino.persistence.controller.GardenController;
import org.celebino.persistence.controller.GardenStatusController;
import org.celebino.persistence.controller.LitersMinuteController;
import org.celebino.persistence.controller.UserController;
import org.celebino.persistence.controller.WateringController;

public class CelebinoApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public CelebinoApplication() {

		// Multiple client-request: Cross-Filter
		CorsFilter filter = new CorsFilter();
		filter.getAllowedOrigins().add("*");
		filter.setAllowedMethods("POST, GET, DELETE, PUT, OPTIONS");
		filter.setAllowedHeaders("Content-Type, Authorization");

		this.singletons.add(filter);

		// Controllers disponíveis no serviço.
		this.singletons.add(new UserController());
		this.singletons.add(new GardenController());
		this.singletons.add(new GardenStatusController());
		this.singletons.add(new WateringController());
		this.singletons.add(new AirConditioningController());
		this.singletons.add(new LitersMinuteController());
	}

	public Set<Class<?>> getClasses() {
		return this.empty;
	}

	public Set<Object> getSingletons() {
		return this.singletons;
	}
}