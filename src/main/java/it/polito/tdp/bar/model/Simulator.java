package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.bar.EntryPoint;
import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {

	private PriorityQueue<Event> queue;
	
	//Parametri di simulazione
	
	private Integer NE; 	//numero eventi
	private Integer NT;		//numero tavoli
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
				
		LocalDateTime time = LocalDateTime.now();
		
		for(int i=0; i<this.NE; i++) {
			Integer NC = (int) (Math.random()*10 + 1); 
			Duration TC = Duration.of(((int) (Math.random()*10 + 1)), ChronoUnit.MINUTES);
			
			queue.add(new Event(time, EventType.NEW_CLIENT, NC));
			time = time.plusMinutes(TC.toMinutes());
		}
	}

	public void run() {
		
		this.tavoli.sort(new Comparator<Tavolo>() {
			@Override
			public int compare(Tavolo o1, Tavolo o2) {
				return o1.getPosti().compareTo(o2.getPosti());
			}
		});
		
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
			this.statistica.incrementaClienti(e.getNumPersone());
			Tavolo trovato = this.trovaTavolo(e.getNumPersone());
			if(trovato != null) {
				//Aggiorno il modello del mondo
				if(trovato.getId() != -1)
					trovato.setLibero(false);				
				//assegno il tavolo
				e.setTavolo(trovato);						

				//Aggiorno l'output
				this.statistica.incrementaSoddisfatti(e.getNumPersone());
				this.listaClienti += e.toString();
				
				//Genero nuovi eventi
				this.TP = Duration.of(((int) (Math.random()*61 + 60)), ChronoUnit.MINUTES);
				this.queue.add(new Event(e.getTime().plus(this.TP), EventType.TABLE_RETURNED, e.getNumPersone(), trovato));
			}
			else {
				this.tolleranza = Math.random()*0.9;
				if(Math.random() <= this.tolleranza) {
					//Assegno il bancone (primo tavolo della lista, -1 posti per convenzione
					e.setTavolo(tavoli.get(0));
					//Aggiorno l'output
					this.statistica.incrementaSoddisfatti(e.getNumPersone());
					this.listaClienti += e.toString();
				}
				else this.statistica.incrementaInsoddisfatti(e.getNumPersone());
			}
			break;
			
		case TABLE_RETURNED: 
			e.getTavolo().setLibero(true);
		}
	}
	
	private Tavolo trovaTavolo(Integer numPersone) {
		
		for(Tavolo t : this.tavoli) {
			if(t.isLibero() && t.tavoloAdeguato(numPersone))
				return t;
		}
		return null;
	}
	
	public Statistica getStatistica() {
		return this.statistica;
	}
	
	public String getElencoCLienti() {
		return this.listaClienti;
	}
	
	public void setTavoli(List<Tavolo> tavoli) {
		this.tavoli = tavoli;
	}
	
	public Integer getNT() {
		return this.NT;
	}
	
}



