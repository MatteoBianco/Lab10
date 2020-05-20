package it.polito.tdp.bar.model;

public class Statistica {

	private Integer numero_totale_clienti;
	private Integer numero_clienti_soddisfatti;
	private Integer numero_clienti_insoddisfatti;
	
	public Statistica() {
		numero_totale_clienti = 0;
		numero_clienti_soddisfatti = 0;
		numero_clienti_insoddisfatti = 0;
	}
	
	public Integer getNumero_totale_clienti() {
		return numero_totale_clienti;
	}
	
	public void setNumero_totale_clienti(Integer numero_totale_clienti) {
		this.numero_totale_clienti = numero_totale_clienti;
	}
	
	public Integer getNumero_clienti_soddisfatti() {
		return numero_clienti_soddisfatti;
	}
	
	public void setNumero_clienti_soddisfatti(Integer numero_clienti_soddisfatti) {
		this.numero_clienti_soddisfatti = numero_clienti_soddisfatti;
	}
	
	public Integer getNumero_clienti_insoddisfatti() {
		return numero_clienti_insoddisfatti;
	}
	
	public void setNumero_clienti_insoddisfatti(Integer numero_clienti_insoddisfatti) {
		this.numero_clienti_insoddisfatti = numero_clienti_insoddisfatti;
	}
	
	public void incrementaClienti(Integer clienti) {
		this.numero_totale_clienti += clienti;
	}
	
	public void incrementaSoddisfatti(Integer soddisfatti) {
		this.numero_clienti_soddisfatti += soddisfatti;
	}
	
	public void incrementaInsoddisfatti(Integer insoddisfatti) {
		this.numero_clienti_insoddisfatti += insoddisfatti;
	}

	
}
