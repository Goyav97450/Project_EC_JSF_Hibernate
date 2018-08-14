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
 * @author Ewen Classe implémentant l'interface ICategorieDao
 *         L'annotation @Stateless permet au conteneur EJB de comprendre que
 *         c'est un EJB session de type <i>Stateless</i>
 */
@Stateless
public class CategorieDaoImpl implements ICategorieDao {

	/**
	 * Récupération d'un entityManager à partir de la PersistenteUnit grâce à
	 * l'annotation @PersistenceContext
	 */
	@PersistenceContext(unitName = "Projet_Ecormmerce")
	private EntityManager em;

	@Override
	public int addCategorie(Categorie ca) {
		// Requete JPQL pour obtenir la liste des catégories
		String reqListCategorie = "SELECT ca FROM Categorie as ca";

		// Création de la requête
		Query query = em.createQuery(reqListCategorie);

		// Récupération de la liste des catégories
		List<Categorie> oldListCategorie = query.getResultList();

		// Inscription de la catégorie dans la base de données
		em.persist(ca);

		// Création de la requête pour obtenir la nouvelle liste des catégories
		Query queryNew = em.createQuery(reqListCategorie);

		// Récupération de la liste des catégories
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
		// Création de la requête
		Query query = em.createQuery(reqListCategorie);
		// Récupération de la liste des catégories
		List<Categorie> listCategorie = query.getResultList();
		// Assignation de l'image à la catégorie
		for (Categorie ca : listCategorie) {
			ca.setImage("data:image/png);base64," + Base64.encodeBase64String(ca.getPhoto()));
		}

		return listCategorie;
	}

	@Override
	public int deleteCategorie(Categorie ca) {
		// Suppression de la catégorie grâce à la méthode remove de l'Entity
		// Manager
		em.remove(ca);
		return 1;
	}

	@Override
	public Categorie getByIdCategorie(Categorie ca) {
		try {
			// Récupération de la catégorie dans la base de données et stockage
			// dans un objet
			Categorie cat = em.find(Categorie.class, ca.getIdCategorie());
			// Assignation de l'image à la catégorie
			cat.setImage("data:image/png);base64," + Base64.encodeBase64String(cat.getPhoto()));
			return cat;
		} catch (NullPointerException ex) {

			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Categorie updateCategorie(Categorie ca) {
		// Récupération de la catégorie dans la base de données
		Categorie caDB = em.find(Categorie.class, ca.getIdCategorie());
		caDB = ca;

		// Fusion de la catégorie avec la base de données
		em.merge(caDB);

		// Récupération de la nouvelle catégorie.
		Categorie caOut = em.find(Categorie.class, caDB.getIdCategorie());

		return caOut;
	}

	@Override
	public Categorie getCatByNom(String rech) {
		try {// Création d'une requête JPQL
			String req = "SELECT cat FROM Categorie cat WHERE cat.nomCategorie LIKE :pNom";

			// Récupération d'une query
			Query query = em.createQuery(req);

			// Paramétrages
			String nom = '%' + rech + '%';
			query.setParameter("pNom", nom);

			// récupération du résultat
			Categorie cat = (Categorie) query.getSingleResult();
			// assignation de l'image à la catégorie
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
		// Récupération d'une query
		Query query = em.createQuery(reqListCategorie);
		// récupération du résultat
		List<String> listIdCategorie = query.getResultList();

		return listIdCategorie;
	}

}
