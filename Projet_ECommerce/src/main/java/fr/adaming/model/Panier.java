package fr.adaming.model;

import java.util.List;

public class Panier {

	// Transformation de l'association UML en Java
	private List<LigneCommande> listeCom;

	// Getters and Setters
	public List<LigneCommande> getListeCom() {
		return listeCom;
	}

	public void setListeCom(List<LigneCommande> listeCom) {
		this.listeCom = listeCom;
	}

	//
	@Override
	public String toString() {
		return "Panier [listeCom=" + listeCom + "]";
	}

}
