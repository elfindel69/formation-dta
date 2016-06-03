package fr.pizzeria.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "performance")
public class Performance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String service;

	@Column(name = "date_mesure")
	private Timestamp dateMesure;

	private long execution;

	public Performance() {
		super();
	}

	public Performance(String service, Timestamp dateBefore, long execution) {
		super();
		this.service = service;
		this.dateMesure = dateBefore;
		this.execution = execution;
	}
	
	

	@Override
	public String toString() {
		return "Performance [id=" + id + ", service=" + service + ", dateMesure=" + dateMesure + ", execution="
				+ execution + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Timestamp getDateMesure() {
		return dateMesure;
	}

	public void setDateMesure(Timestamp dateMesure) {
		this.dateMesure = dateMesure;
	}

	public long getExecution() {
		return execution;
	}

	public void setExecution(long execution) {
		this.execution = execution;
	}

}
