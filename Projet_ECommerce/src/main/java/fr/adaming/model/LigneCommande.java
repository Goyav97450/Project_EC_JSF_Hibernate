package fr.adaming.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ligneCommande")
public class LigneCommande implements Serializable {

	private static final long serialVersionUID = 1L;

	// Attributs
	/**
	 * Les attributs de la classe Ligne de Commande. 
	 * @Id permet d'indiquer l'identifiant clé primaire de la Ligne de Commande tel qu'il sera
	 * dans la base de données..
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCommande;
	/**
	 * La quantité de produit dans la ligne de commande
	 */
	private int quantite;
	/**
	 * Le prix du produit dans la ligne de commande
	 */
	private double prix;

	// Transformation de l'association UML en JAVA
	/**
	 * On indique grâce à ManyToOne la relation entre la Classe Ligne Commande
	 * et la classe Produit.
	 */
	@ManyToOne
	@JoinColumn(name = "co_id", referencedColumnName = "id_co")
	private Commande commande;

	/**
	 * On indique grâce à ManyToOne la relation entre la Classe Ligne Commande
	 * et la classe Commande.
	 */
	@ManyToOne
	@JoinColumn(name = "pr_id", referencedColumnName = "id_pr")
	private Produit pr;

	// Constructeurs
	/**
	 * Le constructeur avec ID de la Ligne de Commande
	 * 
	 * @param regroupe
	 *            tous les paramètres de la ligne de commande y compris son identifiant.
	 */
	public LigneCommande(int idCommande, int quantite, int prix) {
		super();
		this.idCommande = idCommande;
		this.quantite = quantite;
		this.prix = prix;
	}

	/**
	 * Le constructeur sans ID de Ligne de Commande
	 * 
	 * @param regroupe
	 *            tous les paramètres de la ligne de commande, sauf son identifiant.
	 */
	public LigneCommande(int quantite, int prix) {
		super();
		this.quantite = quantite;
		this.prix = prix;
	}

	/**
	 * Le constructeur vide de la Ligne de Commande
	 * 
	 */
	public LigneCommande() {
		super();
	}

	// Getters and Setters
	/**
	 * Un getter pour l'ID du produit
	 * 
	 * @return l'Id de la ligne de commande
	 */
	public int getIdCommande() {
		return idCommande;
	}

	/**
	 * Un setter pour l'ID du produit
	 * 
	 * @param l'ID
	 *            de la ligne de commande à modifier
	 */
	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	/**
	 * Un getter pour la quantité de produit dans la ligne de commande
	 * 
	 * @return la quantité de produit dans la ligne de commande
	 */
	public int getQuantite() {
		return quantite;
	}

	/**
	 * Un setter pour la quantité de produit dans la ligne de commande
	 * 
	 * @param la
	 *            quantite a modifier dans la ligne de commande
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	/**
	 * Un getter pour le prix total de dans la ligne de commande
	 * 
	 * @return le prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * Un setter pour le prix total de la ligne de commande
	 * 
	 * @param le
	 *            prix à modifier
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * Un getter pour la commande associée à la ligne
	 * 
	 * @return la commande
	 */
	public Commande getCommande() {
		return commande;
	}

	/**
	 * Un setter pour la commande associée à la ligne
	 * 
	 * @param la
	 *            commande associée à la ligne de commande
	 */
	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	/**
	 * Un getter pour le produit associé à la ligne de commande
	 * 
	 * @return l'ID du produit associé à la ligne de commande, défini dans les
	 *         annotations
	 */
	public Produit getPr() {
		return pr;
	}

	/**
	 * Un setter pour le produit associé à la ligne de commande
	 * 
	 * @param l'ID
	 *            du produit associé à la ligne de commande
	 */
	public void setPr(Produit pr) {
		this.pr = pr;
	}

	// ToString
	@Override
	public String toString() {
		return "LigneCommande [idCommande=" + idCommande + ", quantite=" + quantite + ", prix=" + prix + "]";
	}

}
