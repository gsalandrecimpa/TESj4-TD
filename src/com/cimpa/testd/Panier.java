package com.cimpa.testd;

import java.util.ArrayList;
import java.util.Objects;

public class Panier {
	private int contenance;
	private ArrayList<Orange> panier = new ArrayList<>();
	
	public Panier(int contenance) throws PanierException {
		setContenance(contenance);
	}
	
	public int getContenance() {
		return contenance;
	}
	public void setContenance(int contenance) throws PanierException {
		if (contenance < 1) throw new PanierException("La contenance doit être d'au moins 1");
		if (contenance < panier.size()) throw new PanierException(
				"Une nouvelle contenance ne peut être inférieure à la taille courante (="
						+panier.size()+") du panier.");
		this.contenance = contenance;
	}
	
	public boolean estPlein() {
		return panier.size() == contenance;
	}
	
	public boolean estVide() {
		return panier.size() == 0;
	}
	
	public void ajoute(Orange o) throws PanierException {
		if (o == null) throw new PanierException("ajout impossible d'un element null");
		if (estPlein()) throw new PanierException("ajout impossible car le panier est plein");
		if (!panier.add(o)) throw new PanierException("echec ajout, retour false de la methode add");
	}
	
	public void retire() {
		if (!panier.isEmpty()) {
			panier.remove(panier.size()-1);
		}
	}
	
	public double getPrix() {
		double prix = 0;
		for (Orange o:panier) prix+=o.getPrix();
		return prix;
	}
	
	public void boycotteOrigine(String bo) {
		int i;
		for (i=0;i<panier.size();) {
			if (Objects.equals(panier.get(i).getOrigine(),bo)) 
				panier.remove(i);
			else 
				i++;
		}
	}
	
	
	@Override
	public String toString() {
		String ret = String.format("Panier-prix total=%.02f:",getPrix());
		for (Orange o:panier) ret+=o.toString()+";";
		return ret;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Panier) {
			Panier p = (Panier) obj;
			return contenance == p.contenance
					&& Objects.equals(panier, p.panier);
		}
		else return false;
	}
	
	
//	public static void main(String[] args) throws PanierException, OrangeException {
//		Panier p = new Panier(3);
//		//p.ajoute(new Orange(-0.80, "France"));
//		p.ajoute(new Orange(0.80, "France"));
//		p.ajoute(new Orange(0.80, "Espagne"));
//		p.ajoute(new Orange(0.90, "Floride"));
//		//p.ajoute(new Orange(0.90, "Floride"));
//		
//		System.out.println(p);
//		
//		p.boycotteOrigine("France");
//		System.out.println(p);
//		
//		p.retire();
//		System.out.println(p);
//		p.retire();
//		System.out.println(p);
//		p.retire();
//		System.out.println(p);
//		
//	}
 

}
