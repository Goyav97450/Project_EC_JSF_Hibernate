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
 * @author Ewen ManagedBean correspondant � la vue Cat�gorie
 */
@ManagedBean(name = "cAMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {

	/**
	 * Transformation de l'association UML en JAVA L'annotation @EJB permet
	 * d'�tablir un couplage faible entre les services.
	 */
	@EJB
	private ICategorieService caService;
	/**
	 * Transformation de l'association UML en JAVA L'annotation @EJB permet
	 * d'�tablir un couplage faible entre les services.
	 */
	@EJB
	private IProduitService prService;

	// Attributs
	/**
	 * Attribut Cat�gorie qui permettra de stocker les informations d'une
	 * cat�gorie
	 */
	private Categorie cat;
	/**
	 * Attribut file permettant stocker l'image pour la transmettre aux m�thodes
	 * produits
	 */
	private UploadedFile file;
	/**
	 * Attribut liste Cat�gorie qui permettra de stocker la liste des cat�gories
	 */
	private List<Categorie> listeCat;
	/**
	 * Attribut liste Produit qui permettra de stocker la liste des produits
	 */
	private List<Produit> listeProd;
	/**
	 * Attribut liste ID Cat�gorie qui permettra de stocker la liste des ID
	 * cat�gorie
	 */
	private List<String> listeIdCat;
	/**
	 * Attribut liste Filtre Cat�gorie qui permettra d'utiliser la fonction
	 * filtre de primefaces
	 */
	private List<Categorie> listeFiltreCat;
	/**
	 * Attribut indice qui permettra d'afficher les tables dynamiquement dans la
	 * vue correspondante.
	 */
	private boolean indice = false;
	/**
	 * Attribut cat�gorie selectoru qui permettra d'afficher les tables
	 * dynamiquement dans la vue correspondante.
	 */
	private boolean catSelector = false;
	/**
	 * Attribut idSelector qui permettra d'afficher les tables dynamiquement
	 * dans la vue correspondante.
	 */
	private boolean idSelector = true;
	/**
	 * Attribut permettant de cr�er les crit�res de rechercher par nom et
	 * mot-cl�
	 */
	private String rech;

	/**
	 * Constructeur vide n�cessaire � un ManagedBean
	 */
	public CategorieManagedBean() {
		this.cat = new Categorie();
	}

	// m�thode @PostConstruct
	@PostConstruct
	public void init() {

		// r�cup�rer la liste des categories, des produits et des ID Cat�gorie
		listeCat = caService.getAllCategorieService();
		listeProd = prService.getAllProduitService();
		listeIdCat = caService.getAllCatIdService();

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
	 * @return la liste des cat�gories
	 */
	public List<Categorie> getListeCat() {
		return listeCat;
	}

	/**
	 * @return la liste des cat�gories entr�e
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
	 * @param l'indice entr�
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
	 * @param  la liste des produits entr�e
	 */
	public void setListeProd(List<Produit> listeProd) {
		this.listeProd = listeProd;
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
	 * @return le statut s�lecteur cat�gorie
	 */
	public boolean isCatSelector() {
		return catSelector;
	}

	/**
	 * @param le statut s�lecteur cat�gorie choisi
	 */
	public void setCatSelector(boolean catSelector) {
		this.catSelector = catSelector;
	}

	/**
	 * @return le statut du s�lecteur ID
	 */
	public boolean isIdSelector() {
		return idSelector;
	}

	/**
	 * @param le statut du s�lecteur ID choisi
	 */
	public void setIdSelector(boolean idSelector) {
		this.idSelector = idSelector;
	}

	/**
	 * @return la liste des ID cat�gories
	 */
	public List<String> getListeIdCat() {
		return listeIdCat;
	}

	/**
	 * @return la liste des ID cat�gories entr�e
	 */
	public void setListeIdCat(List<String> listeIdCat) {
		this.listeIdCat = listeIdCat;
	}

	/**
	 * @return  la liste des cat�gorie pour le filtrage 
	 */
	public List<Categorie> getListeFiltreCat() {
		return listeFiltreCat;
	}

	/**
	 * @param la liste des cat�gorie entr�e pour le filtrage
	 */
	public void setListeFiltreCat(List<Categorie> listeFiltreCat) {
		this.listeFiltreCat = listeFiltreCat;
	}

	// M�thodes
	/**
	 * M�thode pour ajouter une cat�gorie � la BD
	 * @return l'adresse de l'accueil o� se trouve la liste des cat�gorie
	 */
	public String addCategorie() {
		this.cat.setPhoto(file.getContents());
		int verif = caService.addCategorieService(this.cat);

		if (verif != 0) {
			// r�cup�rer la nouvelle liste de la BD
			List<Categorie> newListeCat = caService.getAllCategorieService();

			// mettre � jour la liste dans l'attribut du MB
			listeCat = newListeCat;
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a �chou�."));
			return "ajoutCategorie";
		}

	}
	/**
	 * M�thode pour supprimer une cat�gorie � la BD
	 * @return l'adresse de la page � afficher
	 */
	public String deleteCategorie() {

		int verif = caService.deleteCategorieService(this.cat);

		if (verif != 0) {
			// r�cup�rer la nouvelle liste de la BD
			List<Categorie> newListeCat = caService.getAllCategorieService();
			List<Produit> newListeProd = prService.getAllProduitService();

			// mettre � jour la liste dans l'attribut du MB
			listeProd = newListeProd;
			listeCat = newListeCat;
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a �chou�."));
			return "deleteCategorie";
		}

	}

	/**
	 * M�thode pour chercher une cat�gorie par son ID dans la BD
	 * @return l'adresse de la page � afficher
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
	 * M�thode pour chercher une cat�gorie par son nom dans la BD
	 * @return l'adresse de la page � afficher
	 */
	public String rechercherCategorieParNom() {
		// R�cup�ration de la cat�gorie � partir de la DB
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
	 * M�thode pour mettre une cat�gorie � jour dans la BD
	 * @return l'adresse de la page � afficher
	 */
	public String updateCategorie() {
		this.cat.setPhoto(file.getContents());
		Categorie caOut = caService.updateCategorieService(this.cat);
		if (caOut != null) {
			// r�cup�rer la nouvelle liste de la BD
			List<Categorie> newListeCat = caService.getAllCategorieService();

			// mettre � jour la liste dans l'attribut du MB
			listeCat = newListeCat;
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La mise � jour a �chou�."));
			return "updateCategorie";
		}
	}

	/**
	 * M�thode pour rechercher des produits par leur cat�gorie
	 * @return l'adresse de la page � afficher
	 */
	public String rechercherProduitsByCat() {
		// R�cup�ration de la cat�gorie � partir de la DB
		cat = caService.getByIdCategorieService(this.cat);
		// Appel de la m�thode
		this.listeProd = prService.getProdByCategorie(cat);
		this.indice = true;

		return "listeProduitByCat";

	}

	/**
	 * M�thode pour changer l'affichage de formulaires dans la vue recherche cat�gorie
	 * @return l'adresse de la page � afficher
	 */
	public void changeType(ValueChangeEvent e) {
		this.catSelector = true;
		this.idSelector = false;
	}

}
