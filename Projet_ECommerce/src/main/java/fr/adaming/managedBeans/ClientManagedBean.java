/**
 * 
 */
package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.IClientService;
import fr.adaming.service.IProduitService;
import fr.adaming.service.ProduitServiceImpl;

/**
 * @author Thibault
 * ManagedBean correspondant � la vue Client
 */
@ManagedBean(name="clMB")
@RequestScoped
public class ClientManagedBean implements Serializable{

	/**
	 * Transformation de l'association UML en JAVA
	 * L'annotation @EJB permet d'�tablir un couplage faible entre les services.
	 */
	@EJB
	private IClientService clService;
	//Attributs
	/**
	 * Attribut client qui servira au client pour s'enregistrer
	 */
	private Client cl;
	/**
	 * Attribut commande qui servira � enregistrer/afficher des commandes
	 */
	private Commande co;
	/**
	 * Attribut cat�gorie pour r�cup�rer l'input client correspondant au choix de cat�gorie pour la recherche de produit
	 */
	private Categorie cat;
	/**
	 * Attribut liste de cat�gories servant � afficher la liste des cat�gories
	 */
	private List<Categorie> listeCat;
	/**
	 * Attribut liste de produits servant � afficher la liste des produits selon une recherche
	 */
	private List<Produit> listeProd;
	/**
	 * Attribut qui servira a afficher ou non des tables
	 */
	private boolean ind;
	/**
	 * Attribut correpsondant au nom de la cat�gorie ou le(s) mot(s) cl�(s) utilis�s par le client pour rechercher des produits
	 */
	private String rech;
	/**
	 * Attribut correspond au choix du type de recherhe par mots cl�s ou cat�gorie
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
	
	/**
	 * Cette m�thode permet de r�cup�rer automatiquement la liste des cat�gories
	 * L'annotation @PostConstruct permet de r�aliser cette op�ration d�s le lancement
	 */
	@PostConstruct
	public void init() {
		this.listeCat = clService.getAllCategorie();
		ind = false;
	}

	/**
	 * @return le client s'�tant enregistrer
	 */
	public Client getCl() {
		return cl;
	}

	/**
	 * @param set le client du MB pour pouvoir r�cup�rer ses donn�es � enregistrer
	 */
	public void setCl(Client cl) {
		this.cl = cl;
	}

	/**
	 * @return la commande r�aliser par le client
	 */
	public Commande getCo() {
		return co;
	}

	/**
	 * @param set la commande afin de r�cup�rer les inputs du client
	 */
	public void setCo(Commande co) {
		this.co = co;
	}

	/**
	 * @return pas vraiment utile
	 */
	public Categorie getCat() {
		return cat;
	}

	/**
	 * @param set une cat�gorie qui servira de filtre pour la m�thode affProdByCat()
	 */
	public void setCat(Categorie cat) {
		this.cat = cat;
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}



	/**
	 * @return un boolean qui servira a afficher ou nom des donn�es sur la vue
	 */
	public boolean isInd() {
		return ind;
	}

	/**
	 * @param ind the ind to set
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
	 * @param set pour r�cup�rer les entr�es utlis�es par l'utilisateur pour ses recherches
	 */
	public void setRech(String rech) {
		this.rech = rech;
	}

	/**
	 * @return la liste des cat�gories depuis la DB pour l'afficher dans la vue
	 */
	public List<Categorie> getListeCat() {
		return listeCat;
	}

	/**
	 * @param pas vraiment utile
	 */
	public void setListeCat(List<Categorie> listeCat) {
		this.listeCat = listeCat;
	}

	/**
	 * @return la liste des produits r�sultant d'une recherche
	 */
	public List<Produit> getListeProd() {
		return listeProd;
	}

	/**
	 * @param pas vraiment utile
	 */
	public void setListeProd(List<Produit> listeProd) {
		this.listeProd = listeProd;
	}
	
	
	//Autres m�thodes correspondant au cas d'utilisation client
	/**
	 * M�thode pour afficher les produits selon le filtre
	 * @return
	 */
	public String affProd () {
		
		switch (type) {
		case "mot":
			//Appel de la m�thode
			listeProd = clService.getProdByKeyWord(rech);
			ind = true;
			break;
		case "cat":
			//R�cup�ration de la cat�gorie � partir de la DB
			cat = clService.getCatByNom(rech);
			//Appel de la m�thode
			listeProd = clService.getProdByCategorie(cat);
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
	
//	public String ajoutCl() {
//		//Appel de la m�thode
//		int verif = clService.saveClient(cl);
//		
//		if (verif!=0) {
//			//Envoie d'un message d'erreur
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'enregestriment s'est bien d�roul�, merci"));
//			
//			return "ajoutCl";
//		} else {
//			//Envoie d'un message d'erreur
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'enregestriment a �chou� veuillez r�essayer"));
//			
//			return "ajoutCl";
//		}
//	}
	
}
