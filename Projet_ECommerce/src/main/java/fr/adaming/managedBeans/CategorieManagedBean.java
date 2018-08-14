package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

/**
 * @author Ewen ManagedBean correspondant à la vue Catégorie
 */
@ManagedBean(name = "cAMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {

	/**
	 * Transformation de l'association UML en JAVA L'annotation @EJB permet
	 * d'établir un couplage faible entre les services.
	 */
	@EJB
	private ICategorieService caService;
	/**
	 * Transformation de l'association UML en JAVA L'annotation @EJB permet
	 * d'établir un couplage faible entre les services.
	 */
	@EJB
	private IProduitService prService;

	// Attributs
	/**
	 * Attribut Catégorie qui permettra de stocker les informations d'une
	 * catégorie
	 */
	private Categorie cat;
	/**
	 * Attribut file permettant stocker l'image pour la transmettre aux méthodes
	 * produits
	 */
	private UploadedFile file;
	/**
	 * Attribut liste Catégorie qui permettra de stocker la liste des catégories
	 */
	private List<Categorie> listeCat;
	/**
	 * Attribut liste Produit qui permettra de stocker la liste des produits
	 */
	private List<Produit> listeProd;
	/**
	 * Attribut liste ID Catégorie qui permettra de stocker la liste des ID
	 * catégorie
	 */
	private List<String> listeIdCat;
	/**
	 * Attribut liste Filtre Catégorie qui permettra d'utiliser la fonction
	 * filtre de primefaces
	 */
	private List<Categorie> listeFiltreCat;
	/**
	 * Attribut indice qui permettra d'afficher les tables dynamiquement dans la
	 * vue correspondante.
	 */
	private boolean indice = false;
	/**
	 * Attribut catégorie selectoru qui permettra d'afficher les tables
	 * dynamiquement dans la vue correspondante.
	 */
	private boolean catSelector = false;
	/**
	 * Attribut idSelector qui permettra d'afficher les tables dynamiquement
	 * dans la vue correspondante.
	 */
	private boolean idSelector = true;
	/**
	 * Attribut permettant de créer les critères de rechercher par nom et
	 * mot-clé
	 */
	private String rech;

	/**
	 * Constructeur vide nécessaire à un ManagedBean
	 */
	public CategorieManagedBean() {
		this.cat = new Categorie();
	}

	// méthode @PostConstruct
	@PostConstruct
	public void init() {

		// récupérer la liste des categories, des produits et des ID Catégorie
		listeCat = caService.getAllCategorieService();
		listeProd = prService.getAllProduitService();
		listeIdCat = caService.getAllCatIdService();

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
	 * @return la liste des catégories
	 */
	public List<Categorie> getListeCat() {
		return listeCat;
	}

	/**
	 * @return la liste des catégories entrée
	 */
	public void setListeCat(List<Categorie> listeCat) {
		this.listeCat = listeCat;
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
	 * @return la liste des produits
	 */
	public List<Produit> getListeProd() {
		return listeProd;
	}

	/**
	 * @param  la liste des produits entrée
	 */
	public void setListeProd(List<Produit> listeProd) {
		this.listeProd = listeProd;
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
	 * @return le statut sélecteur catégorie
	 */
	public boolean isCatSelector() {
		return catSelector;
	}

	/**
	 * @param le statut sélecteur catégorie choisi
	 */
	public void setCatSelector(boolean catSelector) {
		this.catSelector = catSelector;
	}

	/**
	 * @return le statut du sélecteur ID
	 */
	public boolean isIdSelector() {
		return idSelector;
	}

	/**
	 * @param le statut du sélecteur ID choisi
	 */
	public void setIdSelector(boolean idSelector) {
		this.idSelector = idSelector;
	}

	/**
	 * @return la liste des ID catégories
	 */
	public List<String> getListeIdCat() {
		return listeIdCat;
	}

	/**
	 * @return la liste des ID catégories entrée
	 */
	public void setListeIdCat(List<String> listeIdCat) {
		this.listeIdCat = listeIdCat;
	}

	/**
	 * @return  la liste des catégorie pour le filtrage 
	 */
	public List<Categorie> getListeFiltreCat() {
		return listeFiltreCat;
	}

	/**
	 * @param la liste des catégorie entrée pour le filtrage
	 */
	public void setListeFiltreCat(List<Categorie> listeFiltreCat) {
		this.listeFiltreCat = listeFiltreCat;
	}

	// Méthodes
	/**
	 * Méthode pour ajouter une catégorie à la BD
	 * @return l'adresse de l'accueil où se trouve la liste des catégorie
	 */
	public String addCategorie() {
		this.cat.setPhoto(file.getContents());
		int verif = caService.addCategorieService(this.cat);

		if (verif != 0) {
			// récupérer la nouvelle liste de la BD
			List<Categorie> newListeCat = caService.getAllCategorieService();

			// mettre à jour la liste dans l'attribut du MB
			listeCat = newListeCat;
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a échoué."));
			return "ajoutCategorie";
		}

	}
	/**
	 * Méthode pour supprimer une catégorie à la BD
	 * @return l'adresse de la page à afficher
	 */
	public String deleteCategorie() {

		int verif = caService.deleteCategorieService(this.cat);

		if (verif != 0) {
			// récupérer la nouvelle liste de la BD
			List<Categorie> newListeCat = caService.getAllCategorieService();
			List<Produit> newListeProd = prService.getAllProduitService();

			// mettre à jour la liste dans l'attribut du MB
			listeProd = newListeProd;
			listeCat = newListeCat;
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a échoué."));
			return "deleteCategorie";
		}

	}

	/**
	 * Méthode pour chercher une catégorie par son ID dans la BD
	 * @return l'adresse de la page à afficher
	 */
	public String rechercherCategorie() {
		Categorie catFound = caService.getByIdCategorieService(this.cat);

		if (catFound != null) {
			this.cat = catFound;
			this.indice = true;
			return "rechercherCategorie";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categorie introuvable"));
			return "rechercherCategorie";
		}

	}

	/**
	 * Méthode pour chercher une catégorie par son nom dans la BD
	 * @return l'adresse de la page à afficher
	 */
	public String rechercherCategorieParNom() {
		// Récupération de la catégorie à partir de la DB
		Categorie catFound = caService.getCatByNomService(rech);

		if (catFound != null) {
			this.cat = catFound;
			this.indice = true;
			return "rechercherCategorie";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categorie introuvable"));
			return "rechercherCategorie";
		}

	}
	
	/**
	 * Méthode pour mettre une catégorie à jour dans la BD
	 * @return l'adresse de la page à afficher
	 */
	public String updateCategorie() {
		this.cat.setPhoto(file.getContents());
		Categorie caOut = caService.updateCategorieService(this.cat);
		if (caOut != null) {
			// récupérer la nouvelle liste de la BD
			List<Categorie> newListeCat = caService.getAllCategorieService();

			// mettre à jour la liste dans l'attribut du MB
			listeCat = newListeCat;
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La mise à jour a échoué."));
			return "updateCategorie";
		}
	}

	/**
	 * Méthode pour rechercher des produits par leur catégorie
	 * @return l'adresse de la page à afficher
	 */
	public String rechercherProduitsByCat() {
		// Récupération de la catégorie à partir de la DB
		cat = caService.getByIdCategorieService(this.cat);
		// Appel de la méthode
		this.listeProd = prService.getProdByCategorie(cat);
		this.indice = true;

		return "listeProduitByCat";

	}

	/**
	 * Méthode pour changer l'affichage de formulaires dans la vue recherche catégorie
	 * @return l'adresse de la page à afficher
	 */
	public void changeType(ValueChangeEvent e) {
		this.catSelector = true;
		this.idSelector = false;
	}

}
