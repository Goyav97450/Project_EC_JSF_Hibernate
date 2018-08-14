package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe du model Etudiant
 * @author Toto
 *
 */

@Entity
@Table(name="clients")
public class Client implements Serializable{

	//Attributs
	/**
	 * L'id de l'objet Client, c'est aussi la clé primaire dans la DB
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cl")
	private Long idClient;
	/**
	 * Le nom de l'objet Client
	 */
	private String nomClient;
	/**
	 * L'adresse de l'objet Client
	 */
	private String adresse;
	/**
	 * L'email de l'objet Client
	 */
	private String email;
	/**
	 * Le numéro de téléphone de l'objet Client
	 */
	private String tel;
	
	/**
	 * Transormation de l'association UML en JAVA entre un objet Client et une liste de Commande
	 */
	@OneToMany(mappedBy="client")
	private List<Commande> listCommande;
	
	//Constructeurs
	/**
	 * Constructeur vide
	 */
	public Client() {
		super();
	}
	
	/**
	 * Constructeur sans id utilisé pour l'ajout d'un objet CLient dans la DB
	 * @param nomClient
	 * @param adresse
	 * @param email
	 * @param tel
	 * @param listCommande
	 */
	public Client(String nomClient, String adresse, String email, String tel, List<Commande> listCommande) {
		super();
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
		this.listCommande = listCommande;
	}

	/**
	 * Constructeur avec id utilisé pour les méthodes de modifications et de récupération d'un objet Client dans la DB
	 * @param idClient
	 * @param nomClient
	 * @param adresse
	 * @param email
	 * @param tel
	 * @param listCommande
	 */
	public Client(Long idClient, String nomClient, String adresse, String email, String tel,
			List<Commande> listCommande) {
		super();
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
		this.listCommande = listCommande;
	}

	//Getter/Setter
	/**
	 * @return the id of the current Client
	 */
	public Long getIdClient() {
		return idClient;
	}

	/**
	 * @param set the id of the current Client
	 */
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	/**
	 * @return the name of the current Client
	 */
	public String getNomClient() {
		return nomClient;
	}
	/**
	 * @param name the current Client
	 */
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	/**
	 * @return the adresse of the current Client
	 */
	public String getAdresse() {
		return adresse;
	}
	/**
	 * @param set the adresse of the current Client
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	/**
	 * @return the mail of the current Client
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param set the mail of the current Client
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the tel of the current Client
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param set the tel of the current
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return the list of command associated with the current Client
	 */
	public List<Commande> getListCommande() {
		return listCommande;
	}
	/**
	 * @param set the list of command associated to the current Client
	 */
	public void setListCommande(List<Commande> listCommande) {
		this.listCommande = listCommande;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", nomClient=" + nomClient + ", adresse=" + adresse + ", email=" + email
				+ ", tel=" + tel + ", listCommande=" + listCommande + "]";
	}
	
	
}
