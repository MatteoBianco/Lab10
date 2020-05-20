package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Event implements Comparable<Event> {
	
	public enum EventType {
		NEW_CLIENT, TABLE_RETURNED;
	}
	
	private LocalDateTime time;
	private EventType type;
	private Integer num_persone;
	private Duration durata;
	private Double tolleranza;
	
	public Event(LocalDateTime time, EventType type, Integer num_persone, Duration durata, Double tolleranza) {
		super();
		this.time = time;
		this.type = type;
		this.num_persone = num_persone;
		this.durata = durata;
		this.tolleranza = tolleranza;
	}
	

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}


	public Integer getNum_persone() {
		return num_persone;
	}

	public void setNum_persone(Integer num_persone) {
		this.num_persone = num_persone;
	}

	public Duration getDurata() {
		return durata;
	}

	public void setDurata(Duration durata) {
		this.durata = durata;
	}

	public Double getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(Double tolleranza) {
		this.tolleranza = tolleranza;
	}

	@Override
	public int compareTo(Event o) {
		return this.time.compareTo(o.time);
	}
	
	

}
