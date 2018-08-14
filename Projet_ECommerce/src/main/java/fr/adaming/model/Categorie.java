package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "categories")
public class Categorie implements Serializable {

	private static final long serialVersionUID = 1L;

	// Attributs
	/**
	 * L'ID de la catégorie
	 * 
	 * @Id permet d'indiquer l'identifiant clé primaire du produit tel qu'il
	 *     sera dans la base de données. La colonne est renommée id_ca.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ca")
	private long idCategorie;
	
	/**
	 * Le nom de la catégorie
	 */
	private String nomCategorie;

	/**
	 * La photo de la catégorie
	 * @Lob permet de confirmer que la photo est une blob dans la base SQL
	 */
	@Lob
	private byte[] photo;
	
	/**
	 * La description de la catégorie
	 */
	private String description;

	/**
	 * L'image dans laquelle on stocke la photo pour pouvoir l'afficher dans la vue.
	 * @Transient evite que l'attribut selectionne ne soit integre a la base de
	 *            donnees.
	 */
	@Transient
	private String image;

	// Transformation de l'association UML en Java
	/**
	 * On indique grâce à OneToMany la relation entre la Classe Ligne Categorie
	 * et la classe Produit.
	 */
	@OneToMany(mappedBy = "ca", cascade=CascadeType.REMOVE)
	private List<Produit> listeProduits;

	// Constructeurs
	/**
	 * Le constructeur avec ID de Categorie
	 * 
	 * @param regroupe
	 *            tous les paramètres de la Commande y compris son identifiant.
	 */
	public Categorie(long idCategorie, String nomCategorie, byte[] photo, String description) {
		super();
		this.idCategorie = idCategorie;
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
	}

	/**
	 * Le constructeur sans ID de Commande
	 * 
	 * @param regroupe
	 *            tous les paramètres de la Commande, sauf son identifiant.
	 */
	public Categorie(String nomCategorie, byte[] photo, String description) {
		super();
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
	}

	/**
	 * Le constructeur vide de la Commande
	 * 
	 */
	public Categorie() {
		super();
	}

	// Getters and Setters

	/**
	 * Un getter pour l'ID de la catégorie
	 * 
	 * @return l'ID de la catégorie
	 */
	public long getIdCategorie() {
		return idCategorie;
	}

	/**
	 * Un setter pour l'ID de la Catégorie
	 * 
	 * @param l'ID
	 *            de la catégorie à entrer
	 */
	public void setIdCategorie(long idCategorie) {
		this.idCategorie = idCategorie;
	}

	/**
	 * Un getter pour le nom de la catégorie
	 * 
	 * @return the nomCategorie
	 */
	public String getNomCategorie() {
		return nomCategorie;
	}

	/**
	 * Un setter pour le nom de la catégorie
	 * 
	 * @param le
	 *            nom de la catégorie à entrer
	 */
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	/**
	 * Un getter pour l'image de la catégorie
	 * 
	 * @return l'image représentant la catégorie
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * Un setter pour l'image du produit
	 * 
	 * @param l'image
	 *            à entrer qui représentera la catégorie.
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * Un getter pour la description de la catégorie
	 * 
	 * @return la description de la catégorie
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Un setter pour la description de la catégorie
	 * 
	 * @param la
	 *            description à entrer pour la catégorie
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Un getter pour la liste des produits associés à la catégorie
	 * 
	 * @return la liste des produits de cette catégorie
	 */
	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	/**
	 * Un setter pour l'image du produit
	 * 
	 * @param la
	 *            liste des produits qui composeront la categorie
	 */
	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
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

	// toString
	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", nomCategorie=" + nomCategorie + ", photo=" + photo
				+ ", description=" + description + "]";
	}
}
