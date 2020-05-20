package it.polito.tdp.bar.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

	/*
	 * NC e TC sarebbero meglio come variabili locali dentro il ciclo for, anziché variabili di istanza
	 * è un po' strano che l'inizio della simulazione sia 'now' (la rende meno ripetibile), ma è accettabile in quanto il testo non specificava altrimenti
	 * il metodo generaTavoli è un po' troppo "cablato" (hardcoded) per i miei gusti, lo sposterei fuori dal SImulator (nel Model)
	 * nella classe Tavolo, la semantica di equals (uguaglianza di id) e quella di compareTo (confront tra posti) sono diverse, e potrebbe creare confusion. Ad esempio compareTo==0 non implica che equals==true.
		Sarebbe meglio definire un Comparator esterno, piuttosto che dire che l'ordinamento per posti sia l'rdinamento "naturale" dei tavoli
	 * nel calcolo delle statistiche conti i GRUPPI e non i CLIENTI. Un gruppo da 10 conta "+1" come un gruppo da 2
		questo può cambiare le percentuali finali in quanto sarà più probabile scontentare i gruppi grandi. Se contassi le PERSONE scontente, avresti percentuali maggiori.
	 * dentro trovaTavolo richiami OGNI VOLTA l'ordinamento dei tavoli -> inutile
	 * il new Tavolo(-1, -1) è bruttino…. Se rappresenta il bancone, potresti crearlo una volta per tutte e lasciarlo nell'elenco dei tavoli.
		per chiareza spezzerei la ricerca del tavolo (trovaTavolo) dalla tolleranza
	 * listaclienti += e.toString() non mi piace molto, preferirei salvare in una List<Event> e poi convertire in stringa solo nel controller
	 * .plusMinutes(this.TP.toMinutes()) forse si può semplificare con .plus(this.TP)
	 */
	private Simulator sim;
	
	public void simula() {
		this.sim = new Simulator();
		this.sim.setTavoli(this.generaTavoli());
		sim.run();
	}
	
	public Integer getClienti() {
		return this.sim.getStatistica().getNumero_totale_clienti();
	}
	
	public Integer getSoddisfatti() {
		return this.sim.getStatistica().getNumero_clienti_soddisfatti();
	}
	
	public Integer getInsoddisfatti() {
		return this.sim.getStatistica().getNumero_clienti_insoddisfatti();
	}
	
	public String getElencoClienti() {
		return this.sim.getElencoCLienti();
	}
	
	private List<Tavolo> generaTavoli() {
		
		List<Tavolo> tavoli = new ArrayList<>();
		for(int i=1; i<=sim.getNT(); i++) {
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
		tavoli.add(new Tavolo(-1,-1,true));
		return tavoli;
	}
	
}
