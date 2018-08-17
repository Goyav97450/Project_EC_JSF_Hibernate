package fr.adaming.service;

import fr.adaming.model.Client;

/**
 * @author Thibault
 * Interface Service des méthodes relier aux clients
 */

public interface IClientService {
		
	/**<b>saveClient</b>
	 * Cette méthode permet à un Client de s'enregistrer dans la DB pour pouvoir passer une commande
	 * @param le Client à enregistrer
	 * @return un entier qui vaut 1 si l'ajout a fonctionné et 0 si l'ajout a échoué
	 */
	public int saveClient(Client cl);
	
	
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
