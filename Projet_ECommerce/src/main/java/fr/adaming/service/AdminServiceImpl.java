package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IAdminDao;
import fr.adaming.model.Admin;
/**
 * @author Ewen L'annotation @Stateful définit la classe comme un EJB
 *         Session qui sera istanciable plusieurs fois
 */
@Stateful
public class AdminServiceImpl implements IAdminService{

	/**
	 * Transformation de l'association entre service et dao L'annotation @EJB
	 * sert à définir le partage des tâches
	 */
	@EJB
	private IAdminDao aDao;
	
	public Admin isExistService(Admin a) {
		
		return aDao.isExist(a);
	}

	
	
}
