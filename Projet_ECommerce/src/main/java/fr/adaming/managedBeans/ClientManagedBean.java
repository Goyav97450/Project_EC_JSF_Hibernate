package fr.adaming.managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;
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
	 * Attribut liste de clients servant � afficher la liste des clients selon
	 * une recherche
	 */
	private List<Client> listeClients;

	/**
	 * Attribut liste des ID clients servant � afficher la liste des ID dans un menu d�roulant
	 */
	private List<String> listeIdClients;
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
	 * Attribut client selector qui permettra d'afficher les tables
	 * dynamiquement dans la vue correspondante.
	 */
	private boolean clSelector = false;
	/**
	 * Attribut idCLSelector qui permettra d'afficher les tables dynamiquement
	 * dans la vue correspondante.
	 */
	private boolean idClSelector = true;

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
	 * Transformation de l'association UML en JAVA permet
	 * d'�tablir un couplage faible entre les services.
	 */
	@ManagedProperty(value = "#{clService}")
	private IClientService clService;

	// Setter obligatoire pour l'injection de d�pendance en JSF (utilisant
	// @ManagedProperty)
	public void setClService(IClientService clService) {
		this.clService = clService;
	}

	/**
	 * Transformation de l'association UML en JAVA L'annotation @EJB permet
	 * d'�tablir un couplage faible entre les services.
	 */
	@ManagedProperty(value = "#{prService}")
	private IProduitService prService;

	// Setter obligatoire pour l'injection de d�pendance en JSF (utilisant
	// @ManagedProperty)
	public void setPrService(IProduitService prService) {
		this.prService = prService;
	}
	
	/**
	 * Transformation de l'association UML en JAVA L'annotation @EJB permet
	 * d'�tablir un couplage faible entre les services.
	 */
	@ManagedProperty(value = "#{catService}")
	private ICategorieService caService;

	// Setter obligatoire pour l'injection de d�pendance en JSF (utilisant
	// @ManagedProperty)

	public void setCaService(ICategorieService caService) {
		this.caService = caService;
	}
	
	/**
	 * Transformation de l'association UML en JAVA L'annotation @ManagedProperty permet
	 * d'�tablir un couplage faible entre les services.
	 */
	@ManagedProperty(value = "#{coService}")
	private ICommandeService coService;
	
	// Setter obligatoire pour l'injection de d�pendance en JSF (utilisant
	// @ManagedProperty)
	public void setCoService(ICommandeService coService) {
		this.coService = coService;
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

	/**
	 * @return le statut s�lecteur client
	 */
	public boolean isClSelector() {
		return clSelector;
	}

	/**
	 * @param le
	 *            statut s�lecteur client choisi
	 */
	public void setClSelector(boolean clSelector) {
		this.clSelector = clSelector;
	}

	/**
	 * @return le statut du s�lecteur ID
	 */
	public boolean isIdClSelector() {
		return idClSelector;
	}

	/**
	 * @param le
	 *            statut du s�lecteur ID choisi
	 */
	public void setIdClSelector(boolean idClSelector) {
		this.idClSelector = idClSelector;
	}

	/**
	 * @return la liste des ID clients
	 */
	public List<String> getListeIdClients() {
		return listeIdClients;
	}

	/**
	 * @param la liste des ID clients
	 */
	public void setListeIdClients(List<String> listeIdClients) {
		this.listeIdClients = listeIdClients;
	}

	// m�thode @PostConstruct
	@PostConstruct
	public void init() {

		// r�cup�rer la liste des categories, des produits et des ID Cat�gorie
		listeClients = clService.getAllClient();
		listeIdClients = clService.getAllClIdService();

	}

	// M�thodes
	/**
	 * M�thode pour chercher un client par son ID dans la BD
	 * 
	 * @return l'adresse de la page � afficher
	 */
	public String rechercherClient() {
		Client clFound = clService.getClientById(this.cl);

		if (clFound != null) {
			this.cl = clFound;
			this.ind = true;
			return "rechercherClient";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Client introuvable"));
			return "rechercherClient";
		}

	}

	/**
	 * M�thode pour chercher un client par son adresse mail dans la BD
	 * 
	 * @return l'adresse de la page � afficher
	 */
	public String rechercherClientParMail() {
		Client clFound = clService.getClientByMail(this.rech);

		if (clFound != null) {
			this.cl = clFound;
			this.ind = true;
			return "rechercherClient";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Client introuvable"));
			return "rechercherClient";
		}

	}

	/**
	 * M�thode pour changer l'affichage de formulaires dans la vue recherche
	 * client
	 * 
	 * @return l'adresse de la page � afficher
	 */
	public void changeType(ValueChangeEvent e) {
		this.clSelector = true;
		this.idClSelector = false;
	}
	
	/**
	 * M�thode pour afficher les produits selon le filtre
	 * @return
	 */
	public String affProd () {
		
		switch (type) {
		case "mot":
			//Appel de la m�thode
			listeProd = prService.getProdByKeyWord(rech);
			ind = true;
			break;
		case "cat":
			//R�cup�ration de la cat�gorie � partir de la DB
			cat = caService.getCatByNomService(rech);
			//Appel de la m�thode
			listeProd = prService.getProdByCategorie(cat);
			ind = true;
			break;
		default:
			//Envoie d'un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vous n'avez pas s�lectionn� le type de recherche que vous voulez faire"));
			ind=false;
			break;
		}	
		return "affProd";
	}
	
	public String affComm(Client cl) {
		
		List<Commande> coClient = coService.getCommandeByClient(cl);
		
		return "affComm";
	}

}
