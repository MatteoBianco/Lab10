package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.bar.EntryPoint;
import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {

	private PriorityQueue<Event> queue;
	
	//Parametri di simulazione
	
	private Integer NE; 	//numero eventi
	private Integer NC; 	//numero clienti
	private Integer NT;		//numero tavoli
	private Duration TC; 	//tempo tra clienti
	private Duration TP;	//tempo permanenza clienti
	private Double tolleranza; 	//tolleranza dei clienti
	
	//Modello del mondo
	private List<Tavolo> tavoli;	//tavoli disponibile
	
	//Parametri da calcolare
	private Statistica statistica; 	//statistiche da calcolare
	private String listaClienti;

	public Simulator() {
		super();
		this.queue = new PriorityQueue<>();
		this.tavoli = new ArrayList<>();
		this.NE = 2000;
		this.NT = 15;
		this.statistica = new Statistica();
		this.listaClienti = "";
		
		this.generaTavoli();
		
		LocalDateTime time = LocalDateTime.now();
		
		for(int i=0; i<this.NE; i++) {
			this.NC = (int) (Math.random()*10 + 1); 
			this.TC = Duration.of(((int) (Math.random()*10 + 1)), ChronoUnit.MINUTES);
			
			queue.add(new Event(time, EventType.NEW_CLIENT, this.NC));
			time = time.plusMinutes(TC.toMinutes());
		}
		Collections.sort(this.tavoli);
	}

	public void run() {
		
		if(this.queue != null & this.tavoli != null) {
			while(!this.queue.isEmpty()) {
				Event e = this.queue.poll();
				processEvent(e);
			}
		}
	}
	
	private void processEvent(Event e) {
		
		switch(e.getType()) {
		case NEW_CLIENT: 
			this.statistica.incrementaClienti();
			Tavolo trovato = this.trovaTavolo(e.getNumPersone());
			if(trovato != null) {
				//Aggiorno il modello del mondo
				if(trovato.getId() != -1)
					trovato.setLibero(false);				
				//assegno il tavolo
				e.setTavolo(trovato);						

				//Aggiorno l'output
				this.statistica.incrementaSoddisfatti();
				this.listaClienti += e.toString();
				
				//Genero nuovi eventi
				if(trovato.getId() != -1) {
					this.TP = Duration.of(((int) (Math.random()*61 + 60)), ChronoUnit.MINUTES);
					this.queue.add(new Event(e.getTime().plusMinutes(this.TP.toMinutes()), EventType.TABLE_RETURNED, e.getNumPersone(), trovato));
				}
			}
			else this.statistica.incrementaInsoddisfatti();
			break;
			
		case TABLE_RETURNED: 
			e.getTavolo().setLibero(true);
		}
	}
	
	private Tavolo trovaTavolo(Integer numPersone) {
		
		Collections.sort(this.tavoli);
		for(Tavolo t : this.tavoli) {
			if(t.isLibero() && t.tavoloAdeguato(numPersone))
				return t;
		}
		this.tolleranza = Math.random()*0.9;
		if(Math.random() <= this.tolleranza)
			return new Tavolo(-1,-1, true);
		return null;
	}

	private void generaTavoli() {

		for(int i=1; i<=this.NT; i++) {
			if(i<3) {
				Tavolo t = new Tavolo(i, 10, true);
				tavoli.add(t);
			}
			else if(i<7) {
				Tavolo t = new Tavolo(i, 8, true);
				tavoli.add(t);
			}
			else if(i<11) {
				Tavolo t = new Tavolo(i, 6, true);
				tavoli.add(t);
			}
			else {
				Tavolo t = new Tavolo(i, 4, true);
				tavoli.add(t);
			}
		}
	}
	
	public Statistica getStatistica() {
		return this.statistica;
	}
	
	public String getElencoCLienti() {
		return this.listaClienti;
	}
	
}



