package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

/**
 * @author Ewen Classe impl�mentant l'interface ICategorieDao
 *         L'annotation @Stateless permet au conteneur EJB de comprendre que
 *         c'est un EJB session de type <i>Stateless</i>
 */
@Stateless
public class CategorieDaoImpl implements ICategorieDao {

	/**
	 * R�cup�ration d'un entityManager � partir de la PersistenteUnit gr�ce �
	 * l'annotation @PersistenceContext
	 */
	@PersistenceContext(unitName = "Projet_Ecormmerce")
	private EntityManager em;

	@Override
	public int addCategorie(Categorie ca) {
		// Requete JPQL pour obtenir la liste des cat�gories
		String reqListCategorie = "SELECT ca FROM Categorie as ca";

		// Cr�ation de la requ�te
		Query query = em.createQuery(reqListCategorie);

		// R�cup�ration de la liste des cat�gories
		List<Categorie> oldListCategorie = query.getResultList();

		// Inscription de la cat�gorie dans la base de donn�es
		em.persist(ca);

		// Cr�ation de la requ�te pour obtenir la nouvelle liste des cat�gories
		Query queryNew = em.createQuery(reqListCategorie);

		// R�cup�ration de la liste des cat�gories
		List<Categorie> newListCategorie = queryNew.getResultList();

		// Comparaison des deux listes pour confirmer l'ajout
		if (oldListCategorie.size() != newListCategorie.size()) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<Categorie> getAllCategorie() {
		// Requete JPQL pour obtenir la liste des produits
		String reqListCategorie = "SELECT ca FROM Categorie as ca";
		// Cr�ation de la requ�te
		Query query = em.createQuery(reqListCategorie);
		// R�cup�ration de la liste des cat�gories
		List<Categorie> listCategorie = query.getResultList();
		// Assignation de l'image � la cat�gorie
		for (Categorie ca : listCategorie) {
			ca.setImage("data:image/png);base64," + Base64.encodeBase64String(ca.getPhoto()));
		}

		return listCategorie;
	}

	@Override
	public int deleteCategorie(Categorie ca) {
		// Suppression de la cat�gorie gr�ce � la m�thode remove de l'Entity
		// Manager
		em.remove(ca);
		return 1;
	}

	@Override
	public Categorie getByIdCategorie(Categorie ca) {
		try {
			// R�cup�ration de la cat�gorie dans la base de donn�es et stockage
			// dans un objet
			Categorie cat = em.find(Categorie.class, ca.getIdCategorie());
			// Assignation de l'image � la cat�gorie
			cat.setImage("data:image/png);base64," + Base64.encodeBase64String(cat.getPhoto()));
			return cat;
		} catch (NullPointerException ex) {

			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Categorie updateCategorie(Categorie ca) {
		// R�cup�ration de la cat�gorie dans la base de donn�es
		Categorie caDB = em.find(Categorie.class, ca.getIdCategorie());
		caDB = ca;

		// Fusion de la cat�gorie avec la base de donn�es
		em.merge(caDB);

		// R�cup�ration de la nouvelle cat�gorie.
		Categorie caOut = em.find(Categorie.class, caDB.getIdCategorie());

		return caOut;
	}

	@Override
	public Categorie getCatByNom(String rech) {
		try {// Cr�ation d'une requ�te JPQL
			String req = "SELECT cat FROM Categorie cat WHERE cat.nomCategorie LIKE :pNom";

			// R�cup�ration d'une query
			Query query = em.createQuery(req);

			// Param�trages
			String nom = '%' + rech + '%';
			query.setParameter("pNom", nom);

			// r�cup�ration du r�sultat
			Categorie cat = (Categorie) query.getSingleResult();
			// assignation de l'image � la cat�gorie
			cat.setImage("data:image/png);base64," + Base64.encodeBase64String(cat.getPhoto()));
			return cat;

		} catch (NoResultException | NonUniqueResultException ex) {

			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String> getAllCatId() {
		// Requete JPQL pour obtenir la liste des produits
		String reqListCategorie = "SELECT ca.idCategorie FROM Categorie as ca";
		// R�cup�ration d'une query
		Query query = em.createQuery(reqListCategorie);
		// r�cup�ration du r�sultat
		List<String> listIdCategorie = query.getResultList();

		return listIdCategorie;
	}

}
