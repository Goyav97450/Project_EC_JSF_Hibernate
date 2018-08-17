package fr.adaming.dao;


import fr.adaming.model.Categorie;
import fr.adaming.model.Commande;

/**
 * @author Thibault
 * Interface Dao des m�thodes relier aux commandes
 */

public interface ICommandeDao {
	
	
	/**<b>saveCommande</b>
	 * Cette m�thode perment d'enregistrer une commande dans la DB
	 * @param la Commande � enregistrer
	 * @return un entier qui vaut 1 si l'ajout a fonctionn� et 0 si l'ajout a �chou�
	 */
	public Commande saveCommande(Commande co);
	
	/**<b>saveCommande</b>
	 * Cette m�thode perment d'enregistrer une commande dans la DB
	 * @param la Commande � enregistrer
	 * @return un entier qui vaut 1 si l'ajout a fonctionn� et 0 si l'ajout a �chou�
	 */
	public Commande getCommandeByClient(Commande co);
	
	/**<b>getCatByNom</b>
	 * Cette m�thode permet de r�cup�rer un cat�gorie par son nom
	 * @param Nom de la cat�gorie recherch�e
	 * @return la cat�gorie correspondant
	 */
	public Categorie getCatByNom(String rech);
}
