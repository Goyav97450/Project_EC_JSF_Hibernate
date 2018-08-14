package fr.adaming.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;

	// Attributs
	/**
	 * L'ID de l'Admin
	 * 
	 * @Id permet d'indiquer l'identifiant clé primaire de l'admin tel qu'il
	 *     sera dans la base de données.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_a")
	private long idAdmin;
	
	/**
	 * Le mot de passe de l'Admin
	 */
	private String mdp;

	// Constructeurs
	/**
	 * Le constructeur avec ID de l'Admin
	 * 
	 * @param regroupe
	 *            tous les paramètres de l'Admin, y compris son ID
	 */
	public Admin(long idAdmin, String mdp) {
		super();
		this.idAdmin = idAdmin;
		this.mdp = mdp;
	}

	/**
	 * Le constructeur sans ID de l'Admin
	 * 
	 * @param regroupe
	 *            tous les paramètres de produit, sauf son identifiant.
	 */
	public Admin(String mdp) {
		super();
		this.mdp = mdp;
	}

	/**
	 * Le constructeur avec ID de Produit
	 * 
	 * @param regroupe
	 *            tous les paramètres de produit y compris son identifiant.
	 */
	public Admin() {
		super();
	}

	// Getters and Setters
	/**
	 * Un getter pour l'ID de l'Admin
	 * 
	 * @return l'identifiant unique de l'Admin
	 */
	public long getIdAdmin() {
		return idAdmin;
	}

	/**
	 * Un setter pour l'ID de l'Admin
	 * 
	 * @param l'identifiant de l'Admin qu'on veut selectionner.
	 */
	public void setIdAdmin(long idAdmin) {
		this.idAdmin = idAdmin;
	}

	/**
	 * Un getter pour le mot de passe de l'Admin
	 * 
	 * @return le mod de passe de l'admin
	 */
	public String getMdp() {
		return mdp;
	}

	/**
	 * Un setter pour le mot de passe de l'Admin
	 * 
	 * @param le mod de passe de l'admin a entrer.
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	// ToString
	@Override
	public String toString() {
		return "Admin [idAdmin=" + idAdmin + ", mdp=" + mdp + "]";
	}
}
