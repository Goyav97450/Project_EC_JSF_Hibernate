package fr.adaming.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="commandes")
public class Commande implements Serializable{

	//Attributs
	/**
	 * L'id de l'objet Commande, c'est aussi la clé primaire dans la DB
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_co")
	private Long idCommande;
	/**
	 * La date de création de l'objet Commande
	 */
	@Temporal(TemporalType.DATE)
	private Date dateCommande;
	
	/**
	 * Transormation de l'association UML en JAVA entre un objet Commade et un objet Client
	 */
	@ManyToOne
	@JoinColumn(name="cl_id", referencedColumnName="id_cl")
	private Client client;
	/**
	 * Transormation de l'association UML en JAVA entre un objet Commade et une liste de ligneCommande
	 */
	@OneToMany(mappedBy="commande", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private List<LigneCommande> listeLigne;
	
	//Constructeurs

	/**
	 * Constructeur Vide
	 */
	public Commande() {
		super();
	}
	
	/**
	 * Constructeur sans id utilisé pour la méthode ajout d'une Commande dans la DB
	 * @param dateCommande
	 */
	public Commande(Date dateCommande) {
		super();
		this.dateCommande = dateCommande;
	}

	/**
	 * Cosntructeur avec id utilisé pour les méthodes modifier et récupérer une Commande dans la DB
	 * @param idCommande
	 * @param dateCommande
	 */
	public Commande(Long idCommande, Date dateCommande) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
	}

	//Getter/Setter
	/**
	 * @return the id of the current Commande
	 */
	public Long getIdCommande() {
		return idCommande;
	}
	/**
	 * @param the id of the current Commande
	 */
	public void setIdCommande(Long idCommande) {
		this.idCommande = idCommande;
	}
	/**
	 * @return the date of the current Commande
	 */
	public Date getDateCommande() {
		return dateCommande;
	}
	/**
	 * @param set the date of the current commande
	 */
	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}
	/**
	 * @return the Client associated with the current Commande
	 */
	public Client getClient() {
		return client;
	}
	/**
	 * @param set the Client associated with the current Commande
	 */
	public void setClient(Client client) {
		this.client = client;
	}
	/**
	 * @return the list of LigneCommande associated with the current Commande
	 */
	public List<LigneCommande> getListeLigne() {
		return listeLigne;
	}
	/**
	 * @param set the list of LignCommande associated with the current Commande
	 */
	public void setListeLigne(List<LigneCommande> listeLigne) {
		this.listeLigne = listeLigne;
	}
	
	//Réécriture de ToString
	@Override
	public String toString() {
		return "Commande [idCommande=" + idCommande + ", dateCommande=" + dateCommande + "]";
	}
}
