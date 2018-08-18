package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "produits")
public class Produit implements Serializable {

	private static final long serialVersionUID = 1L;

	// Attributs
	/**
	 * L'ID du produit
	 * 
	 * @Id permet d'indiquer l'identifiant clé primaire du produit tel qu'il
	 *     sera dans la base de données. La colonne est renommée id_pr.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pr")
	private long idProduit;
	/**
	 * Le nom du produit
	 */
	private String designation;
	/**
	 * La description du produit
	 */
	private String description;
	/**
	 * Le prix du produit
	 */
	private double prix;
	/**
	 * La quantité en stock du produit
	 */
	private int quantite;

	/**
	 * 
	 * @Transient evite que l'attribut selectionne ne soit integre a la base de
	 *            donnees.
	 */
	@Transient
	private String image;

	/**
	 * 
	 * @Transient evite que l'attribut selectionne ne soit integre a la base de
	 *            donnees.
	 */
	@Transient
	private boolean selectionne;

	/**
	 * 
	 * @Lob permet de confirmer que la photo est une blob dans la base SQL
	 */
	@Lob
	private byte[] photo;

	/**
	 * 
	 * @Transient evite que l'attribut selectionne ne soit integre a la base de
	 *            donnees.
	 */
	private String offre="Non";

	// Transformation de l'association UML en Java
	/**
	 * On indique grâce à ManyToOne la relation entre la Classe Produit et la
	 * classe Categorie.
	 */
	@ManyToOne
	@JoinColumn(name = "ca_id", referencedColumnName = "id_ca")
	private Categorie ca;

	@OneToMany(mappedBy = "pr")
	private List<LigneCommande> listeLignes;

	// Constructeurs
	/**
	 * Le constructeur avec ID de Produit
	 * 
	 * @param regroupe
	 *            tous les paramètres de produit y compris son identifiant.
	 */
	public Produit(long idProduit, String designation, String description, double prix, int quantite,
			boolean selectionne, byte[] photo, String offre) {
		super();
		this.idProduit = idProduit;
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
		this.offre = offre;
	}

	/**
	 * Le constructeur sans ID de Produit
	 * 
	 * @param regroupe
	 *            tous les paramètres de produit, sauf son identifiant.
	 */
	public Produit(String designation, String description, double prix, int quantite, boolean selectionne, byte[] photo,
			String offre) {
		super();
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
		this.offre = offre;
	}

	/**
	 * Le constructeur vide de Produit
	 * 
	 */
	public Produit() {
		super();
	}

	// Getters and Setters

	/**
	 * Un getter pour l'ID du produit
	 * 
	 * @return idProduit l'identifiant unique du produit
	 */
	public long getIdProduit() {
		return idProduit;
	}

	/**
	 * Un setter pour l'ID du produit
	 * 
	 * @param idProduit
	 *            l'identifiant du produit à modifier.
	 */
	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}

	/**
	 * Un getter pour la designation du produit
	 * 
	 * @return designation un nom désignant le produit
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * Un setter pour la désignation du produit
	 * 
	 * @param designation
	 *            la designation du produit a modifier.
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * Un getter pour la description du produit
	 * 
	 * @return une description du produit
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Un setter pour la description du produit
	 * 
	 * @param la
	 *            description du produit à modifier.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Un getter pour la description du produit
	 * 
	 * @return le prix du produit
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * Un setter pour la description du produit
	 * 
	 * @param le
	 *            prix du produit à modifier
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * Un getter pour la quantité de produits
	 * 
	 * @return le nombre d'occurence de produits à récupérer.
	 */
	public int getQuantite() {
		return quantite;
	}

	/**
	 * Un setter pour la quantité de produits
	 * 
	 * @param quantite
	 *            le nombre de produit à sélectionner.
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	/**
	 * Un getter pour le statut du produit
	 * 
	 * @return si le produit est selectionne ou non
	 */
	public boolean isSelectionne() {
		return selectionne;
	}

	/**
	 * Un setter pour le statut du produit
	 * 
	 * @param choisir
	 *            de selectionner le produit ou non
	 * 
	 */
	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}

	/**
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * @param photo
	 *            the photo to set
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * Un getter pour la categorie du produit
	 * 
	 * @return la categorie du produit
	 */
	public Categorie getCa() {
		return ca;
	}

	/**
	 * Un setter pour l'image du produit
	 * 
	 * @param la
	 *            categorie du produit a entrer
	 */
	public void setCa(Categorie ca) {
		this.ca = ca;
	}

	/**
	 * Un getter pour la liste des lignes de commande
	 * 
	 * @return renvoie les lignes de commande contenant le produit
	 */
	public List<LigneCommande> getListeLignes() {
		return listeLignes;
	}

	/**
	 * Un getter pour la liste des lignes de commande
	 * 
	 * @param La
	 *            ligne de commande à modifier
	 */
	public void setListeLignes(List<LigneCommande> listeLignes) {
		this.listeLignes = listeLignes;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return le statut du produit (en solde ou non)
	 */
	public String getOffre() {
		return offre;
	}

	/**
	 * @param le
	 *            produit dont on veut changer le statut
	 */
	public void setOffre(String offre) {
		this.offre = offre;
	}

	// ToString
	@Override
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", designation=" + designation + ", description=" + description
				+ ", prix=" + prix + ", quantite=" + quantite + ", selectionne=" + selectionne + ", photo=" + photo
				+ ", offre=" + offre + "]";
	}
}
