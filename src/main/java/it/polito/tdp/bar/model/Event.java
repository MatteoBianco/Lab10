package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event implements Comparable<Event> {
	
	public enum EventType {
		NEW_CLIENT, TABLE_RETURNED;
	}
	
	private LocalDateTime time;
	private EventType type;
	private Integer numPersone;
	private Tavolo tavolo;
	
	public Event(LocalDateTime time, EventType type, Integer numPersone) {
		super();
		this.time = time;
		this.type = type;
		this.numPersone = numPersone;
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

	public Integer getNumPersone() {
		return numPersone;
	}

	public void setNumPersone(Integer numPersone) {
		this.numPersone = numPersone;
	}
	
	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}

	@Override
	public int compareTo(Event o) {
		return this.time.compareTo(o.time);
	}

	@Override
	public String toString() {
		return "Giorno e ora: " + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT).format(time) + ";\nNumero di clienti al tavolo: " + numPersone + ";\n" + tavolo + "\n\n";
	}
	
	

}
