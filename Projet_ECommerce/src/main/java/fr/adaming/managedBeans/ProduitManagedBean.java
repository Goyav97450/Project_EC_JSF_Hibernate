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
 * @author Ewen ManagedBean correspondant à la vue Produit
 */
@ManagedBean(name = "prMB")
@RequestScoped
public class ProduitManagedBean implements Serializable {

	/**
	 * Transformation de l'association UML en JAVA L'annotation @EJB permet
	 * d'établir un couplage faible entre les services.
	 */
	@EJB
	private IProduitService prService;
	/**
	 * Transformation de l'association UML en JAVA L'annotation @EJB permet
	 * d'établir un couplage faible entre les services.
	 */
	@EJB
	private ICategorieService caService;

	// Attributs
	/**
	 * Attribut Produit qui permettra de stocker les informations d'un produit
	 */
	private Produit pr;
	/**
	 * Attribut Catégorie qui permettra de stocker les informations d'une
	 * catégorie
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
	 * Attribut file permettant stocker l'image pour la transmettre aux méthodes
	 * produits
	 */
	private UploadedFile file;
	/**
	 * Attribut permettant de créer les critères de rechercher par nom et
	 * mot-clé
	 */
	private String rech;
	/**
	 * Attribut type permettant de sélectionner les modes de recherche dans la
	 * vue.
	 */
	private String type;

	/**
	 * Constructeur vide nécessaire à un ManagedBean
	 */
	public ProduitManagedBean() {
		this.pr = new Produit();
		this.cat = new Categorie();
	}

	/**
	 * Cette méthode permet de récupérer automatiquement la liste des catégories
	 * L'annotation @PostConstruct permet de réaliser cette opération dès le lancement
	 */
	@PostConstruct
	public void init() {

		/**récupération la liste des categories et des produits
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
	 * @param le produit à entrer
	 */
	public void setPr(Produit pr) {
		this.pr = pr;
	}

	/**
	 * @return la catégorie
	 */
	public Categorie getCa() {
		return cat;
	}

	/**
	 * @param la catégorie à entrer
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
	 * @param la liste des produits à entrer
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
	 * @param l'indice entré
	 */
	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	/**
	 * @return l'image à enregistrer en BD
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
	 * @return le paramètre recherche
	 */
	public String getRech() {
		return rech;
	}

	/**
	 * @param le paramètre recherche qui permettra de construire des requêtes en DAO
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
	 * @return la catégorie
	 */
	public Categorie getCat() {
		return cat;
	}

	/**
	 * @param la catégorie entrée
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
	 * @param  la liste des ID produits entrée
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
	 * @param la liste des produits entrée pour le filtrage
	 */
	public void setListeFiltreProd(List<Produit> listeFiltreProd) {
		this.listeFiltreProd = listeFiltreProd;
	}

	// Méthodes
	/**
	 * Méthode pour ajouter un produit à la BD
	 * @return l'adresse de l'accueil où se trouve la liste des produits
	 */
	public String addProduit() {
		//Inscription de la photo
		this.pr.setPhoto(file.getContents());
		//Appel de la méthode
		int verif = prService.addProduitService(this.pr, cat);

		if (verif != 0) {
			// récupérer la nouvelle liste de la BD
			List<Produit> newListeProd = prService.getAllProduitService();

			// mettre à jour la liste dans l'attribut du MB
			listeProd = newListeProd;
			//retourner à l'accueil
			return "accueilAdmin";
		} else {
			//Envoie d'un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a échoué."));
			return "accueilAdmin";
		}

	}
	/**
	 * Méthode pour supprimer un produit à la BD
	 * @return l'adresse de la page à afficher
	 */
	public String deleteProduit() {
		//appel de la méthode
		int verif = prService.deleteProduitService(pr);

		if (verif != 0) {
			// récupérer la nouvelle liste de la BD
			List<Produit> newListeProd = prService.getAllProduitService();

			// mettre à jour la liste dans l'attribut du MB
			listeProd = newListeProd;
			//Retour à l'accueil
			return "accueilAdmin";
		} else {
			//Envoie d'un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a échoué."));
			return "deleteProduit";
		}

	}
	/**
	 * Méthode pour chercher un produit par son ID dans la BD
	 * @return l'adresse de la page à afficher
	 */
	public String rechercherProduitById() {
		//Appel de la méthode
		Produit prFound = prService.getByIdProduitService(this.pr);

		if (prFound != null) {
			//mettre à jour l'attribut produit et indice du MB
			this.pr = prFound;
			this.indice = true;
			//retour à la page recherche
			return "rechercherProduitById";

		} else {
			//Envoie d'un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categorie introuvable"));
			return "rechercherProduitById";
		}

	}

	/**
	 * Méthode pour chercher un produit par mot clé ou catégorie dans la BD
	 * @return l'adresse de la page à afficher
	 */
	public String rechercherProduit() {
		//Boucle déterminant chaque cas en fonction du type sélectionné
		switch (type) {
		case "mot":
			// Appel de la méthode recherche par mot-clé
			listeProd = prService.getProdByKeyWord(rech);
			//mise à jour de l'indice
			this.indice = true;
			break;
		case "cat":
			// Récupération de la catégorie à partir de la DB
			cat = caService.getCatByNomService(rech);
			// Appel de la méthode recherche par ID
			listeProd = prService.getProdByCategorie(cat);
			//mise à jour de l'indice
			this.indice = true;
			break;
		default:
			// Envoie d'un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Vous n'avez pas sélectionné le type de recherche que vous voulez faire"));
			//mise à jour de l'indice
			this.indice = false;
			break;
		}
		return "rechercherProduit";
	}
	
	/**
	 * Méthode pour mettre un produit à jour dans la BD
	 * @return l'adresse de la page à afficher
	 */
	public String updateProduit() {
		//Inscription de la photo
		this.pr.setPhoto(file.getContents());
		//Appel de la méthode
		int verif = prService.updateProduitService(this.pr);

		if (verif != 0) {
			// récupérer la nouvelle liste de la BD
			List<Produit> newListeProd = prService.getAllProduitService();

			// mettre à jour la liste dans l'attribut du MB
			listeProd = newListeProd;
			//retour à l'accueil
			return "accueilAdmin";

		} else {
			// Envoie d'un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modification echouée"));
			return "updateProduit";
		}

	}

}
