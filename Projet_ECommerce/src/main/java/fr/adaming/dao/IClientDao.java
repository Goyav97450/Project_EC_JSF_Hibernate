package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Client;

/**
 * @author Thibault
 * Interface Dao des méthodes relier aux clients
 */

public interface IClientDao {
		
	/**<b>saveClient</b>
	 * Cette méthode permet à un Client de s'enregistrer dans la DB pour pouvoir passer une commande
	 * @param le Client à enregistrer
	 * @return un entier qui vaut 1 si l'ajout a fonctionné et 0 si l'ajout a échoué
	 */
	public int saveClient(Client cl);
	
	/**
	 * <b>getAllClient</b> Cette méthode permet à un admin de récupérer la
	 * liste complète des clients.
	 * 
	 * @return la liste des clients existantes en base de donnée
	 */
	public List<Client> getAllClient();
	
	/**<b>getClientByMail</b>
	 * Cette méthode permet de récupérer un client par son adresse mail
	 * @param le Client à retrouver
	 * @return Client de récupérer à partir de la DB
	 */
	public Client getClientByMail(Client cl);
	
	/**<b>getClientById</b>
	 * Cette méthode permet de récupérer un client par son ID
	 * @param le Client à retrouver
	 * @return Client de récupérer à partir de la DB
	 */
	public Client getClientById(Client cl);
}