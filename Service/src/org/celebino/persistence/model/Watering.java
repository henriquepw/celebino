package org.celebino.persistence.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sun.xml.txw2.annotation.XmlElement;

@Entity
@Table(name = "tb_watering")
@NamedQuery(name = "Watering.getAll", query = "from Watering")
public class Watering {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "watering_id")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_garden_id")
	private Garden garden;

	@Column(name = "watering_time")
	private Timestamp time;

	public Watering() {
	}

	public Watering(Long id, Garden garden, Timestamp time) {
		super();
		this.id = id;
		this.garden = garden;
		this.time = time;
	}

	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public Garden getGarden() {
		return garden;
	}

	public void setGarden(Garden garden) {
		this.garden = garden;
	}

	@XmlElement
	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Watering [id=" + id + ", garden=" + garden + ", time=" + time + "]";
	}

}
