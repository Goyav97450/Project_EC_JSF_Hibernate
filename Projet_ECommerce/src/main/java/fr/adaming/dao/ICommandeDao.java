package fr.adaming.dao;


import java.util.List;

import fr.adaming.model.Client;
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
	public List<Commande> getCommandeByClient(Client cl);
	
}
