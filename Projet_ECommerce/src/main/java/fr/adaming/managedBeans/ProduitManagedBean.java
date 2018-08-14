package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

/**
 * @author Ewen ManagedBean correspondant � la vue Produit
 */
@ManagedBean(name = "prMB")
@RequestScoped
public class ProduitManagedBean implements Serializable {

	/**
	 * Transformation de l'association UML en JAVA L'annotation @EJB permet
	 * d'�tablir un couplage faible entre les services.
	 */
	@EJB
	private IProduitService prService;
	/**
	 * Transformation de l'association UML en JAVA L'annotation @EJB permet
	 * d'�tablir un couplage faible entre les services.
	 */
	@EJB
	private ICategorieService caService;

	// Attributs
	/**
	 * Attribut Produit qui permettra de stocker les informations d'un produit
	 */
	private Produit pr;
	/**
	 * Attribut Cat�gorie qui permettra de stocker les informations d'une
	 * cat�gorie
	 */
	private Categorie cat;
	/**
	 * Attribut liste Produit qui permettra de stocker la liste des produits
	 */
	private List<Produit> listeProd;
	/**
	 * Attribut liste ID Produit qui permettra de stocker la liste des ID
	 * produits
	 */
	private List<String> listeIdProd;
	/**
	 * Attribut liste Filtre Produit qui permettra d'utiliser la fonction filtre
	 * de primefaces
	 */
	private List<Produit> listeFiltreProd;
	/**
	 * Attribut indice qui permettra d'afficher les tables dynamiquement dans la
	 * vue correspondante.
	 */
	private boolean indice = false;
	/**
	 * Attribut file permettant stocker l'image pour la transmettre aux m�thodes
	 * produits
	 */
	private UploadedFile file;
	/**
	 * Attribut permettant de cr�er les crit�res de rechercher par nom et
	 * mot-cl�
	 */
	private String rech;
	/**
	 * Attribut type permettant de s�lectionner les modes de recherche dans la
	 * vue.
	 */
	private String type;

	/**
	 * Constructeur vide n�cessaire � un ManagedBean
	 */
	public ProduitManagedBean() {
		this.pr = new Produit();
		this.cat = new Categorie();
	}

	/**
	 * Cette m�thode permet de r�cup�rer automatiquement la liste des cat�gories
	 * L'annotation @PostConstruct permet de r�aliser cette op�ration d�s le lancement
	 */
	@PostConstruct
	public void init() {

		/**r�cup�ration la liste des categories et des produits
		 */
		listeProd = prService.getAllProduitService();
		listeIdProd = prService.getAllProdIdService();

	}

	/**
	 * @return le produit
	 */
	public Produit getPr() {
		return pr;
	}

	/**
	 * @param le produit � entrer
	 */
	public void setPr(Produit pr) {
		this.pr = pr;
	}

	/**
	 * @return la cat�gorie
	 */
	public Categorie getCa() {
		return cat;
	}

	/**
	 * @param la cat�gorie � entrer
	 */
	public void setCa(Categorie cat) {
		this.cat = cat;
	}

	/**
	 * @return la liste des produits
	 */
	public List<Produit> getListeProd() {
		return listeProd;
	}

	/**
	 * @param la liste des produits � entrer
	 */
	public void setListeProd(List<Produit> listeProd) {
		this.listeProd = listeProd;
	}

	/**
	 * @return l'indice
	 */
	public boolean isIndice() {
		return indice;
	}

	/**
	 * @param l'indice entr�
	 */
	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	/**
	 * @return l'image � enregistrer en BD
	 */
	public UploadedFile getFile() {
		return file;
	}

	/**
	 * @param l'image mise en ligne dans la vue
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	}

	/**
	 * @return le param�tre recherche
	 */
	public String getRech() {
		return rech;
	}

	/**
	 * @param le param�tre recherche qui permettra de construire des requ�tes en DAO
	 */
	public void setRech(String rech) {
		this.rech = rech;
	}

	/**
	 * @return le type de mode de recherche
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param le type choisi
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return la cat�gorie
	 */
	public Categorie getCat() {
		return cat;
	}

	/**
	 * @param la cat�gorie entr�e
	 */
	public void setCat(Categorie cat) {
		this.cat = cat;
	}

	/**
	 * @return la liste des ID produits
	 */
	public List<String> getListeIdProd() {
		return listeIdProd;
	}

	/**
	 * @param  la liste des ID produits entr�e
	 */
	public void setListeIdProd(List<String> listeIdProd) {
		this.listeIdProd = listeIdProd;
	}

	/**
	 * @return  la liste des produits pour le filtrage
	 */
	public List<Produit> getListeFiltreProd() {
		return listeFiltreProd;
	}

	/**
	 * @param la liste des produits entr�e pour le filtrage
	 */
	public void setListeFiltreProd(List<Produit> listeFiltreProd) {
		this.listeFiltreProd = listeFiltreProd;
	}

	// M�thodes
	/**
	 * M�thode pour ajouter un produit � la BD
	 * @return l'adresse de l'accueil o� se trouve la liste des produits
	 */
	public String addProduit() {
		//Inscription de la photo
		this.pr.setPhoto(file.getContents());
		//Appel de la m�thode
		int verif = prService.addProduitService(this.pr, cat);

		if (verif != 0) {
			// r�cup�rer la nouvelle liste de la BD
			List<Produit> newListeProd = prService.getAllProduitService();

			// mettre � jour la liste dans l'attribut du MB
			listeProd = newListeProd;
			//retourner � l'accueil
			return "accueilAdmin";
		} else {
			//Envoie d'un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a �chou�."));
			return "accueilAdmin";
		}

	}
	/**
	 * M�thode pour supprimer un produit � la BD
	 * @return l'adresse de la page � afficher
	 */
	public String deleteProduit() {
		//appel de la m�thode
		int verif = prService.deleteProduitService(pr);

		if (verif != 0) {
			// r�cup�rer la nouvelle liste de la BD
			List<Produit> newListeProd = prService.getAllProduitService();

			// mettre � jour la liste dans l'attribut du MB
			listeProd = newListeProd;
			//Retour � l'accueil
			return "accueilAdmin";
		} else {
			//Envoie d'un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a �chou�."));
			return "deleteProduit";
		}

	}
	/**
	 * M�thode pour chercher un produit par son ID dans la BD
	 * @return l'adresse de la page � afficher
	 */
	public String rechercherProduitById() {
		//Appel de la m�thode
		Produit prFound = prService.getByIdProduitService(this.pr);

		if (prFound != null) {
			//mettre � jour l'attribut produit et indice du MB
			this.pr = prFound;
			this.indice = true;
			//retour � la page recherche
			return "rechercherProduitById";

		} else {
			//Envoie d'un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categorie introuvable"));
			return "rechercherProduitById";
		}

	}

	/**
	 * M�thode pour chercher un produit par mot cl� ou cat�gorie dans la BD
	 * @return l'adresse de la page � afficher
	 */
	public String rechercherProduit() {
		//Boucle d�terminant chaque cas en fonction du type s�lectionn�
		switch (type) {
		case "mot":
			// Appel de la m�thode recherche par mot-cl�
			listeProd = prService.getProdByKeyWord(rech);
			//mise � jour de l'indice
			this.indice = true;
			break;
		case "cat":
			// R�cup�ration de la cat�gorie � partir de la DB
			cat = caService.getCatByNomService(rech);
			// Appel de la m�thode recherche par ID
			listeProd = prService.getProdByCategorie(cat);
			//mise � jour de l'indice
			this.indice = true;
			break;
		default:
			// Envoie d'un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Vous n'avez pas s�lectionn� le type de recherche que vous voulez faire"));
			//mise � jour de l'indice
			this.indice = false;
			break;
		}
		return "rechercherProduit";
	}
	
	/**
	 * M�thode pour mettre un produit � jour dans la BD
	 * @return l'adresse de la page � afficher
	 */
	public String updateProduit() {
		//Inscription de la photo
		this.pr.setPhoto(file.getContents());
		//Appel de la m�thode
		int verif = prService.updateProduitService(this.pr);

		if (verif != 0) {
			// r�cup�rer la nouvelle liste de la BD
			List<Produit> newListeProd = prService.getAllProduitService();

			// mettre � jour la liste dans l'attribut du MB
			listeProd = newListeProd;
			//retour � l'accueil
			return "accueilAdmin";

		} else {
			// Envoie d'un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modification echou�e"));
			return "updateProduit";
		}

	}

}
