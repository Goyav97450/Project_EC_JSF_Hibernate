package fr.adaming.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;

/**
 * @author Ewen Classe implémentant l'interface ICategorieDao L'annotation
 *         Repository sert à déclarer le DAO comme un bean de spring IoC.
 */
@Repository
public class CategorieDaoImpl implements ICategorieDao {
	/**
	 * Déclaration de l'attribut sessionFactory L'annotation @Autowired permet
	 * d'automatiser l'injection de dépendances.
	 */
	@Autowired
	private SessionFactory sf;

	/**
	 * Setter pour l'injection de dépendance
	 * 
	 * @param sf
	 *            correspond à la SessionFactory d'hibernate
	 */
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public int addCategorie(Categorie ca) {
		// récupérer la session
		Session s = sf.getCurrentSession();

		// Requete HQL pour obtenir la liste des catégories
		String reqListCategorie = "FROM Categorie";

		// Création de la requête
		Query query = s.createQuery(reqListCategorie);

		// Récupération de la liste des catégories
		@SuppressWarnings("unchecked")
		List<Categorie> oldListCategorie = query.list();

		// Inscription de la catégorie dans la base de données
		s.save(ca);

		// Création de la requête pour obtenir la nouvelle liste des catégories
		Query queryNew = s.createQuery(reqListCategorie);

		// Récupération de la liste des catégories
		@SuppressWarnings("unchecked")
		List<Categorie> newListCategorie = queryNew.list();

		// Comparaison des deux listes pour confirmer l'ajout
		if (oldListCategorie.size() != newListCategorie.size()) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<Categorie> getAllCategorie() {
		// récupérer la session
		Session s = sf.getCurrentSession();

		// Requete HQL pour obtenir la liste des produits
		String reqListCategorie = "FROM Categorie";
		// Création de la requête
		Query query = s.createQuery(reqListCategorie);
		// Récupération de la liste des catégories
		@SuppressWarnings("unchecked")
		List<Categorie> listCategorie = query.list();
		// Assignation de l'image à la catégorie
		for (Categorie ca : listCategorie) {
			ca.setImage("data:image/png);base64," + Base64.encodeBase64String(ca.getPhoto()));
		}

		return listCategorie;
	}

	@Override
	public int deleteCategorie(Categorie ca) {
		// récupérer la session
		Session s = sf.getCurrentSession();
		// Suppression de la catégorie grâce à la méthode remove de l'Entity
		// Manager
		s.delete(ca);
		return 1;
	}

	@Override
	public Categorie getByIdCategorie(Categorie ca) {
		try {
			// récupérer la session
			Session s = sf.getCurrentSession();

			// Récupération de la catégorie dans la base de données et stockage
			// dans un objet
			Categorie cat = (Categorie) s.get(Categorie.class, ca.getIdCategorie());
			// Assignation de l'image à la catégorie
			cat.setImage("data:image/png);base64," + Base64.encodeBase64String(cat.getPhoto()));
			return cat;
		} catch (NullPointerException ex) {

			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateCategorie(Categorie ca) {
		// récupérer la session
		Session s = sf.getCurrentSession();

		// Requête HQL
		String req = "UPDATE Categorie ca SET ca.description=:pDes, ca.nomCategorie=:pNom, ca.photo=:pPho WHERE ca.idCategorie=:pIdca";

		// récup du query
		Query query = s.createQuery(req);

		// Paramétrage
		query.setParameter("pDes", ca.getDescription());
		query.setParameter("pNom", ca.getNomCategorie());
		query.setParameter("pPho", ca.getPhoto());
		query.setParameter("pIdca", ca.getIdCategorie());

		int verif = query.executeUpdate();

		return verif;
	}

	@Override
	public Categorie getCatByNom(String rech) {
		try {
			// récupérer la session
			Session s = sf.getCurrentSession();
			// Création d'une requête HQL
			String req = "FROM Categorie cat WHERE cat.nomCategorie LIKE :pNom";

			// Récupération d'une query
			Query query = s.createQuery(req);

			// Paramétrages
			String nom = '%' + rech + '%';
			query.setParameter("pNom", nom);

			// récupération du résultat
			Categorie cat = (Categorie) query.uniqueResult();
			// assignation de l'image à la catégorie
			cat.setImage("data:image/png);base64," + Base64.encodeBase64String(cat.getPhoto()));
			return cat;

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String> getAllCatId() {
		// récupérer la session
		Session s = sf.getCurrentSession();
		
		// Requete hQL pour obtenir la liste des ID catégories
		String reqListCategorie = "SELECT ca.idCategorie FROM Categorie as ca";
		
		// Récupération d'une query
		Query query = s.createQuery(reqListCategorie);
		
		// récupération du résultat
		@SuppressWarnings("unchecked")
		List<String> listIdCategorie = query.list();

		return listIdCategorie;
	}

}
