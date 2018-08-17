package fr.adaming.managedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Produit;


/**
 * @author Thibault
 * ManagedBean relatif aux cas d'utilisation du client
 */

@ManagedBean(name = "clMB")
@RequestScoped
public class ClientManagedBean {
	//Attributs
		/**
		 * Attribut client qui servira au client pour s'enregistrer
		 */
		private Client cl;
		/**
		 * Attribut commande qui servira à enregistrer/afficher des commandes
		 */
		private Commande co;
		/**
		 * Attribut catégorie pour récupérer l'input client correspondant au choix de catégorie pour la recherche de produit
		 */
		private Categorie cat;
		/**
		 * Attribut liste de catégories servant à afficher la liste des catégories
		 */
		private List<Categorie> listeCat;
		/**
		 * Attribut liste de produits servant à afficher la liste des produits selon une recherche
		 */
		private List<Produit> listeProd;
		/**
		 * Attribut qui servira a afficher ou non des tables
		 */
		private boolean ind;
		/**
		 * Attribut correpsondant au nom de la catégorie ou le(s) mot(s) clé(s) utilisés par le client pour rechercher des produits
		 */
		private String rech;
		/**
		 * Attribut correspond au choix du type de recherhe par mots clés ou catégorie
		 */
		private String type;
		/**
		 * Constructeur vide nécessaire à un ManagedBean
		 */
		public ClientManagedBean() {
			super();
			cl = new Client();
			co = new Commande();
			cat = new Categorie();
		}
}
