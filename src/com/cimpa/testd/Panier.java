package com.cimpa.testd;

import java.util.ArrayList;
import java.util.Objects;

public class Panier {
	private int contenance;
	private ArrayList<Orange> contenu = new ArrayList<>();
	
	public Panier(int contenance) throws PanierException {
		setContenance(contenance);
	}
	
	public int getContenance() {
		return contenance;
	}
	public void setContenance(int contenance) throws PanierException {
		if (contenance < 1) throw new PanierException("La contenance doit être d'au moins 1");
		if (contenance < contenu.size()) throw new PanierException(
				"Une nouvelle contenance ne peut être inférieure à la taille courante (="
						+contenu.size()+") du panier.");
		this.contenance = contenance;
	}
	
	public boolean estPlein() {
		return contenu.size() == contenance;
	}
	
	public boolean estVide() {
		return contenu.isEmpty();
	}
	
	public void ajoute(Orange o) throws PanierException {
		if (o == null) throw new PanierException("ajout impossible d'un element null");
		if (estPlein()) throw new PanierException("ajout impossible car le panier est plein");
		if (!contenu.add(o)) throw new PanierException("echec ajout, retour false de la methode add");
	}
	
	public void retire() {
		if (!contenu.isEmpty()) {
			contenu.remove(contenu.size()-1);
		}
	}
	
	public double getPrix() {
		double prix = 0;
		for (Orange o:contenu) prix+=o.getPrix();
		return prix;
	}
	
	public void boycotteOrigine(String bo) {
		int i;
		for (i=0;i<contenu.size();) {
			if (Objects.equals(contenu.get(i).getOrigine(),bo)) 
				contenu.remove(i);
			else 
				i++;
		}
	}
	
	
	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder();
		bld.append(String.format("Panier-prix total=%.02f:",getPrix()));
		for (Orange o:contenu) {
			bld.append(o.toString());
			bld.append(';');
		}
		return bld.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Panier) {
			Panier p = (Panier) obj;
			return contenance == p.contenance
					&& Objects.equals(contenu, p.contenu);
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
