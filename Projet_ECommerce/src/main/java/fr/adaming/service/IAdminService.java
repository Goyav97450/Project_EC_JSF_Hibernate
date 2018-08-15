package fr.adaming.service;

import fr.adaming.model.Admin;

/**
 * @author Ewen L'annotation @Local permet au conteneur EJB de comprendre que
 *         les classes impl�mentant cette interface seront des EJB session avec
 *         une port�e <i>locale</i>
 */
public interface IAdminService {
	/**
	 * <b>isExist</b> Cette m�thode permet de v�rifier qu'un Admin existe bien
	 * dans la base de donn�es via son ID et mot de passe, pour lui autoriser
	 * l'acc�s aux fonctionnalit�s admin.
	 * 
	 * @param l'Admin
	 *            dont on cherche � confirmer l'existence
	 * @return l'Admin dont l'existence est valid�e.
	 */
	public Admin isExistService(Admin a);
}
