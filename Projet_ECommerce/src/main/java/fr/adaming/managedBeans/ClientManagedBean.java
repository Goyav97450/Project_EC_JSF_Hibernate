package fr.adaming.managedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Produit;
import fr.adaming.service.IClientService;
import fr.adaming.service.IProduitService;

/**
 * @author Thibault ManagedBean relatif aux cas d'utilisation du client
 */

@ManagedBean(name = "clMB")
@RequestScoped
public class ClientManagedBean {
	// Attributs
	/**
	 * Attribut client qui servira au client pour s'enregistrer
	 */
	private Client cl;
	/**
	 * Attribut commande qui servira � enregistrer/afficher des commandes
	 */
	private Commande co;
	/**
	 * Attribut cat�gorie pour r�cup�rer l'input client correspondant au choix
	 * de cat�gorie pour la recherche de produit
	 */
	private Categorie cat;
	/**
	 * Attribut liste de cat�gories servant � afficher la liste des cat�gories
	 */
	private List<Categorie> listeCat;
	/**
	 * Attribut liste de produits servant � afficher la liste des produits selon
	 * une recherche
	 */
	private List<Produit> listeProd;
	/**
	 * Attribut qui servira a afficher ou non des tables
	 */
	private boolean ind;
	/**
	 * Attribut correpsondant au nom de la cat�gorie ou le(s) mot(s) cl�(s)
	 * utilis�s par le client pour rechercher des produits
	 */
	private String rech;
	/**
	 * Attribut correspond au choix du type de recherhe par mots cl�s ou
	 * cat�gorie
	 */
	private String type;

	/**
	 * Constructeur vide n�cessaire � un ManagedBean
	 */
	public ClientManagedBean() {
		super();
		cl = new Client();
		co = new Commande();
		cat = new Categorie();
	}

	// Transformation de l'association UML en Java
	/**
	 * Transformation de l'association UML en JAVA L'annotation @EJB permet
	 * d'�tablir un couplage faible entre les services.
	 */
	@ManagedProperty(value = "#{clService}")
	private IClientService clService;

	// Setter obligatoire pour l'injection de d�pendance en JSF (utilisant
	// @ManagedProperty)
	public void setClService(IClientService clService) {
		this.clService = clService;
	}

}
