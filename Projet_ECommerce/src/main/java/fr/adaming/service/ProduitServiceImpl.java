package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

/**
 * @author Ewen L'annotation @Service permet de déclarer le bean service au
 *         conteneur Spring IoC.
 */
@Service("prService")
@Transactional
public class ProduitServiceImpl implements IProduitService {
	/**
	 * Transformation de l'association entre service et dao
	 * L'annotation @Autowired permet de réaliser l'injection automatique des
	 * dépendances.
	 */
	@Autowired
	private IProduitDao prDao;

	/**
	 * Le setter pour l'injection de dépendances
	 * 
	 * @param aDao
	 *            l'Interface avec laquelle on fait le lien.
	 */
	public void setPrDao(IProduitDao prDao) {
		this.prDao = prDao;
	}

	@Override
	public Produit addProduitService(Produit pr, Categorie ca) {
		pr.setCa(ca);
		return prDao.addProduit(pr);
	}

	@Override
	public List<Produit> getAllProduitService() {
		return prDao.getAllProduit();
	}

	@Override
	public int deleteProduitService(Produit pr) {

		return prDao.deleteProduit(pr);
	}

	@Override
	public Produit getByIdProduitService(Produit pr) {
		return prDao.getByIdProduit(pr);
	}

	@Override
	public int updateProduitService(Produit pr) {
		return prDao.updateProduit(pr);
	}

	@Override
	public List<Produit> getProdByCategorie(Categorie cat) {
		return prDao.getProdByCategorie(cat);
	}

	@Override
	public List<Produit> getProdByKeyWord(String keyWord) {
		return prDao.getProdByKeyWord(keyWord);
	}

	@Override
	public List<String> getAllProdIdService() {
		return prDao.getAllProdId();
	}

	public List<String> getAllProdNom() {
		return prDao.getAllProdNom();
	}

	@Override
	public int attribuerOffre(Produit pr) {
		return prDao.attribuerOffre(pr);
	}

}
