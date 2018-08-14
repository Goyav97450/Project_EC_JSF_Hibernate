package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.Admin;

/**
 * @author Ewen Fondrillon
 * Interface Dao des m�thodes reli�es aux fonctionnalit�s Admin
 */
@Local
public interface IAdminDao {

	/**<b>isExist</b>
	 * Cette m�thode permet de v�rifier qu'un Admin existe bien dans la base de donn�es
	 * via son ID et mot de passe, pour lui autoriser l'acc�s aux fonctionnalit�s admin.
	 * @param l'Admin dont on cherche � confirmer l'existence
	 * @return l'Admin dont l'existence est valid�e.
	 */
	public Admin isExist(Admin a);

}
