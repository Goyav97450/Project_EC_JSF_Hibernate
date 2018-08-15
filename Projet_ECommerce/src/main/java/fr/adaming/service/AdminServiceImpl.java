package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IAdminDao;
import fr.adaming.model.Admin;

/**
 * @author Ewen L'annotation @Service permet de déclarer le bean service au
 *         conteneur Spring IoC.
 */
@Service("aService")
@Transactional
public class AdminServiceImpl implements IAdminService{

	/**
	 * Transformation de l'association entre service et dao
	 * L'annotation @Autowired permet de réaliser l'injection automatique des
	 * dépendances.
	 */
	@Autowired
	private IAdminDao aDao;

	/** Le setter pour l'injection de dépendances
	 * 
	 * @param aDao l'Interface avec laquelle on fait le lien.
	 */
	public void setaDao(IAdminDao aDao) {
		this.aDao = aDao;
	}

	public Admin isExistService(Admin a) {

		return aDao.isExist(a);
	}

}
