package org.celebino.persistence.model;

import java.util.Date;

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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "tb_litersminute")
@NamedQuery(name = "LitersMinute.getAll", query = "from LitersMinute")
public class LitersMinute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "litersminute_id")
	private Long id;

	@Column(name = "litersminute_date")
	private Timestamp date;

	@Column(name = "litersminute_lmin")
	private Double lmin;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_airconditioning_id")
	private AirConditioning airconditioning;

	public LitersMinute() {
	}

	public LitersMinute(Long id, Double lmin, AirConditioning airconditioning) {
		this.id = id;
		this.lmin = lmin;
		this.airconditioning = airconditioning;
	}

	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@XmlElement
	public Double getLmin() {
		return lmin;
	}

	public void setLmin(Double lmin) {
		this.lmin = lmin;
	}

	@XmlElement
	public AirConditioning getAirconditioning() {
		return airconditioning;
	}

	public void setAirconditioning(AirConditioning airconditioning) {
		this.airconditioning = airconditioning;
	}

	@Override
	public String toString() {
		return this.lmin + " L/Min - " + date + " #" + this.airconditioning.getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airconditioning == null) ? 0 : airconditioning.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lmin == null) ? 0 : lmin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LitersMinute other = (LitersMinute) obj;
		if (airconditioning == null) {
			if (other.airconditioning != null)
				return false;
		} else if (!airconditioning.equals(other.airconditioning))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lmin == null) {
			if (other.lmin != null)
				return false;
		} else if (!lmin.equals(other.lmin))
			return false;
		return true;
	}

}
