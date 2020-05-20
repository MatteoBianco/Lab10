package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.bar.EntryPoint;
import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {

	private PriorityQueue queue;
	
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

	public Simulator() {
		super();
		this.queue = new PriorityQueue<>();
		this.tavoli = new ArrayList<>();
		this.NE = 2000;
		this.NT = 15;
		this.statistica = new Statistica();
		
		this.generaTavoli();
		
		LocalDateTime time = LocalDateTime.now();
		
		for(int i=0; i<this.NE; i++) {
			this.tolleranza = Math.random()*0.9;
			this.NC = (int) (Math.random()*10 + 1); 
			this.TC = Duration.of(((int) (Math.random()*10 + 1)), ChronoUnit.MINUTES);
			this.TP = Duration.of(((int) (Math.random()*61 + 60)), ChronoUnit.MINUTES);
			
			queue.add(new Event(time, EventType.NEW_CLIENT, this.NC, this.TP, this.tolleranza));
			time = time.plusMinutes(TC.toMinutes());
		}
		
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
	
}



