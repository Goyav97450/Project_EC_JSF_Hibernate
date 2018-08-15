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
 * @author Ewen Classe impl�mentant l'interface ICategorieDao L'annotation
 *         Repository sert � d�clarer le DAO comme un bean de spring IoC.
 */
@Repository
public class CategorieDaoImpl implements ICategorieDao {
	/**
	 * D�claration de l'attribut sessionFactory L'annotation @Autowired permet
	 * d'automatiser l'injection de d�pendances.
	 */
	@Autowired
	private SessionFactory sf;

	/**
	 * Setter pour l'injection de d�pendance
	 * 
	 * @param sf
	 *            correspond � la SessionFactory d'hibernate
	 */
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public int addCategorie(Categorie ca) {
		// r�cup�rer la session
		Session s = sf.getCurrentSession();

		// Requete HQL pour obtenir la liste des cat�gories
		String reqListCategorie = "FROM Categorie";

		// Cr�ation de la requ�te
		Query query = s.createQuery(reqListCategorie);

		// R�cup�ration de la liste des cat�gories
		@SuppressWarnings("unchecked")
		List<Categorie> oldListCategorie = query.list();

		// Inscription de la cat�gorie dans la base de donn�es
		s.save(ca);

		// Cr�ation de la requ�te pour obtenir la nouvelle liste des cat�gories
		Query queryNew = s.createQuery(reqListCategorie);

		// R�cup�ration de la liste des cat�gories
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
		// r�cup�rer la session
		Session s = sf.getCurrentSession();

		// Requete HQL pour obtenir la liste des produits
		String reqListCategorie = "FROM Categorie";
		// Cr�ation de la requ�te
		Query query = s.createQuery(reqListCategorie);
		// R�cup�ration de la liste des cat�gories
		@SuppressWarnings("unchecked")
		List<Categorie> listCategorie = query.list();
		// Assignation de l'image � la cat�gorie
		for (Categorie ca : listCategorie) {
			ca.setImage("data:image/png);base64," + Base64.encodeBase64String(ca.getPhoto()));
		}

		return listCategorie;
	}

	@Override
	public int deleteCategorie(Categorie ca) {
		// r�cup�rer la session
		Session s = sf.getCurrentSession();
		// Suppression de la cat�gorie gr�ce � la m�thode remove de l'Entity
		// Manager
		s.delete(ca);
		return 1;
	}

	@Override
	public Categorie getByIdCategorie(Categorie ca) {
		try {
			// r�cup�rer la session
			Session s = sf.getCurrentSession();

			// R�cup�ration de la cat�gorie dans la base de donn�es et stockage
			// dans un objet
			Categorie cat = (Categorie) s.get(Categorie.class, ca.getIdCategorie());
			// Assignation de l'image � la cat�gorie
			cat.setImage("data:image/png);base64," + Base64.encodeBase64String(cat.getPhoto()));
			return cat;
		} catch (NullPointerException ex) {

			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateCategorie(Categorie ca) {
		// r�cup�rer la session
		Session s = sf.getCurrentSession();

		// Requ�te HQL
		String req = "UPDATE Categorie ca SET ca.description=:pDes, ca.nomCategorie=:pNom, ca.photo=:pPho WHERE ca.idCategorie=:pIdca";

		// r�cup du query
		Query query = s.createQuery(req);

		// Param�trage
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
			// r�cup�rer la session
			Session s = sf.getCurrentSession();
			// Cr�ation d'une requ�te HQL
			String req = "FROM Categorie cat WHERE cat.nomCategorie LIKE :pNom";

			// R�cup�ration d'une query
			Query query = s.createQuery(req);

			// Param�trages
			String nom = '%' + rech + '%';
			query.setParameter("pNom", nom);

			// r�cup�ration du r�sultat
			Categorie cat = (Categorie) query.uniqueResult();
			// assignation de l'image � la cat�gorie
			cat.setImage("data:image/png);base64," + Base64.encodeBase64String(cat.getPhoto()));
			return cat;

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String> getAllCatId() {
		// r�cup�rer la session
		Session s = sf.getCurrentSession();
		
		// Requete hQL pour obtenir la liste des ID cat�gories
		String reqListCategorie = "SELECT ca.idCategorie FROM Categorie as ca";
		
		// R�cup�ration d'une query
		Query query = s.createQuery(reqListCategorie);
		
		// r�cup�ration du r�sultat
		@SuppressWarnings("unchecked")
		List<String> listIdCategorie = query.list();

		return listIdCategorie;
	}

}
