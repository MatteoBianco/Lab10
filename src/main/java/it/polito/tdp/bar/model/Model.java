package it.polito.tdp.bar.model;

public class Model {

	private Simulator sim;
	
	public void simula() {
		this.sim = new Simulator();
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
	
}
