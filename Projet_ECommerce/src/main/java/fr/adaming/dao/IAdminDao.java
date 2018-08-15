package fr.adaming.dao;

import fr.adaming.model.Admin;

/**
 * @author Ewen Fondrillon Interface Dao des méthodes reliées aux
 *         fonctionnalités Admin
 */
public interface IAdminDao {
	/**
	 * <b>isExist</b> Cette méthode permet de vérifier qu'un Admin existe bien
	 * dans la base de données via son ID et mot de passe, pour lui autoriser
	 * l'accès aux fonctionnalités admin.
	 * 
	 * @param l'Admin
	 *            dont on cherche à confirmer l'existence
	 * @return l'Admin dont l'existence est validée.
	 */
	public Admin isExist(Admin a);
}
