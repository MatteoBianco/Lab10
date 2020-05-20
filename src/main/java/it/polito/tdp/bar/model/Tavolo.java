package it.polito.tdp.bar.model;

public class Tavolo implements Comparable<Tavolo>{

	private Integer id;
	private Integer posti;
	private boolean libero;
	
	public Tavolo(Integer id, Integer posti, boolean libero) {
		super();
		this.id = id;
		this.posti = posti;
		this.libero = libero;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPosti() {
		return posti;
	}

	public void setPosti(Integer posti) {
		this.posti = posti;
	}

	public boolean isLibero() {
		return libero;
	}

	public void setLibero(boolean libero) {
		this.libero = libero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Tavolo other = (Tavolo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public boolean tavoloAdeguato (Integer nClienti) {
		if(nClienti <= this.posti & nClienti > (this.posti/2))
			return true;
		else return false;
	}

	@Override
	public int compareTo(Tavolo o) {
		return this.posti.compareTo(o.posti);
	}

	@Override
	public String toString() {
		if(this.id == -1)
			return "Clienti serviti al bancone";
		return "Tavolo n." + id + ", posti:" + posti;
	}
	
}
