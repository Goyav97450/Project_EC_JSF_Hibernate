package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

/**
 * @author Ewen L'annotation @Service permet de déclarer le bean service au
 *         conteneur Spring IoC.
 */
@Service("catService")
@Transactional
public class CategorieServiceImpl implements ICategorieService {
	/**
	 * Transformation de l'association entre service et dao
	 * L'annotation @Autowired permet de réaliser l'injection automatique des
	 * dépendances.
	 */
	@Autowired
	private ICategorieDao caDao;

	/**
	 * Le setter pour l'injection de dépendances
	 * 
	 * @param aDao
	 *            l'Interface avec laquelle on fait le lien.
	 */
	public void setCaDao(ICategorieDao caDao) {
		this.caDao = caDao;
	}

	@Override
	public int addCategorieService(Categorie ca) {
		return caDao.addCategorie(ca);
	}

	@Override
	public List<Categorie> getAllCategorieService() {
		return caDao.getAllCategorie();
	}

	@Override
	public int deleteCategorieService(Categorie ca) {
		Categorie caRemove = caDao.getByIdCategorie(ca);
		if (caRemove != null) {
			return caDao.deleteCategorie(caRemove);
		} else {
			return 0;
		}
	}

	@Override
	public int updateCategorieService(Categorie ca) {
		return caDao.updateCategorie(ca);
	}

	@Override
	public Categorie getByIdCategorieService(Categorie ca) {
		return caDao.getByIdCategorie(ca);
	}

	@Override
	public Categorie getCatByNomService(String rech) {

		return caDao.getCatByNom(rech);
	}

	@Override
	public List<String> getAllCatIdService() {
		return caDao.getAllCatId();
	}



}
