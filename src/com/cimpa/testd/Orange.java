package com.cimpa.testd;

import java.util.Objects;

public class Orange {
	private double prix; //euros
	private String origine;
	
	public Orange(double prix, String origine) throws OrangeException {
		setPrix(prix);
		setOrigine(origine);
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) throws OrangeException {
		if (prix < 0) throw new OrangeException("Le prix ne peut être négatif");
		this.prix = prix;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) throws OrangeException {
		if (origine == null) throw new OrangeException("L'origine ne peut être définie comme null");
		this.origine = origine;
	}
	
	@Override
	public String toString() {
		return String.format("prix=%.02f,origine=%s", prix,origine);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Orange) {
			Orange o = (Orange) obj;
			return prix == o.prix 
					&& Objects.equals(origine, o.origine);
		}
		else return false;
	}

}
