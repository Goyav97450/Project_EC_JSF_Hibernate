package fr.adaming.managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
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
	 * Attribut commande qui servira à enregistrer/afficher des commandes
	 */
	private Commande co;
	/**
	 * Attribut catégorie pour récupérer l'input client correspondant au choix
	 * de catégorie pour la recherche de produit
	 */
	private Categorie cat;
	/**
	 * Attribut liste de catégories servant à afficher la liste des catégories
	 */
	private List<Categorie> listeCat;
	/**
	 * Attribut liste de produits servant à afficher la liste des produits selon
	 * une recherche
	 */
	private List<Produit> listeProd;
	/**
	 * Attribut liste de clients servant à afficher la liste des clients selon
	 * une recherche
	 */
	private List<Client> listeClients;
	/**
	 * Attribut qui servira a afficher ou non des tables
	 */
	private boolean ind;
	/**
	 * Attribut correpsondant au nom de la catégorie ou le(s) mot(s) clé(s)
	 * utilisés par le client pour rechercher des produits
	 */
	private String rech;
	/**
	 * Attribut correspond au choix du type de recherhe par mots clés ou
	 * catégorie
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

	// Transformation de l'association UML en Java
	/**
	 * Transformation de l'association UML en JAVA L'annotation @EJB permet
	 * d'établir un couplage faible entre les services.
	 */
	@ManagedProperty(value = "#{clService}")
	private IClientService clService;

	// Setter obligatoire pour l'injection de dépendance en JSF (utilisant
	// @ManagedProperty)
	public void setClService(IClientService clService) {
		this.clService = clService;
	}

	/**
	 * @return the cl
	 */
	public Client getCl() {
		return cl;
	}

	/**
	 * @param cl
	 *            the cl to set
	 */
	public void setCl(Client cl) {
		this.cl = cl;
	}

	/**
	 * @return the co
	 */
	public Commande getCo() {
		return co;
	}

	/**
	 * @param co
	 *            the co to set
	 */
	public void setCo(Commande co) {
		this.co = co;
	}

	/**
	 * @return the cat
	 */
	public Categorie getCat() {
		return cat;
	}

	/**
	 * @param cat
	 *            the cat to set
	 */
	public void setCat(Categorie cat) {
		this.cat = cat;
	}

	/**
	 * @return the listeCat
	 */
	public List<Categorie> getListeCat() {
		return listeCat;
	}

	/**
	 * @param listeCat
	 *            the listeCat to set
	 */
	public void setListeCat(List<Categorie> listeCat) {
		this.listeCat = listeCat;
	}

	/**
	 * @return the listeProd
	 */
	public List<Produit> getListeProd() {
		return listeProd;
	}

	/**
	 * @param listeProd
	 *            the listeProd to set
	 */
	public void setListeProd(List<Produit> listeProd) {
		this.listeProd = listeProd;
	}

	/**
	 * @return the listeClients
	 */
	public List<Client> getListeClients() {
		return listeClients;
	}

	/**
	 * @param listeClients
	 *            the listeClients to set
	 */
	public void setListeClients(List<Client> listeClients) {
		this.listeClients = listeClients;
	}

	/**
	 * @return the ind
	 */
	public boolean isInd() {
		return ind;
	}

	/**
	 * @param ind
	 *            the ind to set
	 */
	public void setInd(boolean ind) {
		this.ind = ind;
	}

	/**
	 * @return the rech
	 */
	public String getRech() {
		return rech;
	}

	/**
	 * @param rech
	 *            the rech to set
	 */
	public void setRech(String rech) {
		this.rech = rech;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	// méthode @PostConstruct
	@PostConstruct
	public void init() {

		// récupérer la liste des categories, des produits et des ID Catégorie
		listeClients = clService.getAllClient();

	}
	
}
