package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Client;

/**
 * @author Thibault Interface Service des m�thodes relier aux clients
 */

public interface IClientService {

	/**
	 * <b>saveClient</b> Cette m�thode permet � un Client de s'enregistrer dans
	 * la DB pour pouvoir passer une commande
	 * 
	 * @param le
	 *            Client � enregistrer
	 * @return un entier qui vaut 1 si l'ajout a fonctionn� et 0 si l'ajout a
	 *         �chou�
	 */
	public int saveClient(Client cl);

	/**
	 * <b>getClientByMail</b> Cette m�thode permet de r�cup�rer un client par
	 * son adresse mail
	 * 
	 * @param le
	 *            Client � retrouver
	 * @return Client de r�cup�rer � partir de la DB
	 */
	public Client getClientByMail(String rech);

	/**
	 * <b>getClientById</b> Cette m�thode permet de r�cup�rer un client par son
	 * ID
	 * 
	 * @param le
	 *            Client � retrouver
	 * @return Client de r�cup�rer � partir de la DB
	 */
	public Client getClientById(Client cl);

	/**
	 * <b>getAllClient</b> Cette m�thode permet � un admin de r�cup�rer la liste
	 * compl�te des clients.
	 * 
	 * @return la liste des clients existantes en base de donn�e
	 */
	public List<Client> getAllClient();

	/**
	 * <b>getAllCatId</b> Cette m�thode permet de r�cup�rer la liste des ID
	 * cat�gorie
	 * 
	 * @return la liste des noms cat�gories
	 */
	public List<String> getAllClIdService();
}
