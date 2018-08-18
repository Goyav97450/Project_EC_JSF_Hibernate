package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.commons.codec.binary.Base64;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

/**
 * @author Ewen Classe implémentant l'interface ICategorieDao L'annotation
 *         Repository sert à déclarer le DAO comme un bean de spring IoC.
 */
@Repository
public class ProduitDaoImpl implements IProduitDao {
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
	public Produit addProduit(Produit pr) {
		// récupérer la session
		Session s = sf.getCurrentSession();
		s.save(pr);
		return pr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produit> getAllProduit() {
		// récupérer la session
		Session s = sf.getCurrentSession();

		// Requête HQL
		String req = "FROM Produit";

		// récup du query
		Query query = s.createQuery(req);
		List<Produit> listProduit = query.list();

		for (Produit pr : listProduit) {
			pr.setImage("data:image/png);base64," + Base64.encodeBase64String(pr.getPhoto()));
		}
		return listProduit;
	}

	@Override
	public int deleteProduit(Produit pr) {
		// récupérer la session
		Session s = sf.getCurrentSession();

		// Requête HQL
		String req = "DELETE Produit pr WHERE pr.idProduit=:pIdp";

		// récup du query
		Query query = s.createQuery(req);

		// Paramétrage
		query.setParameter("pIdp", pr.getIdProduit());

		int verif = query.executeUpdate();

		return verif;
	}

	@Override
	public Produit getByIdProduit(Produit pr) {
		try {
			// récupérer la session
			Session s = sf.getCurrentSession();

			// Récupération du produit dans la base de données et stockage
			// dans un objet
			Produit pro = (Produit) s.get(Produit.class, pr.getIdProduit());
			// Assignation de l'image au produit
			pro.setImage("data:image/png);base64," + Base64.encodeBase64String(pro.getPhoto()));
			return pro;
		} catch (NullPointerException ex) {

			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateProduit(Produit pr) {
		// récupérer la session
		Session s = sf.getCurrentSession();

		// Requête HQL
		String req = "UPDATE Produit pr SET pr.description=:pDes, pr.designation=:pDesi, pr.photo=:pPho, pr.prix=:pPrix, pr.quantite=:pQua WHERE pr.idProduit=:pIdp";

		// récup du query
		Query query = s.createQuery(req);

		// Paramétrage
		query.setParameter("pDes", pr.getDescription());
		query.setParameter("pDesi", pr.getDesignation());
		query.setParameter("pPho", pr.getPhoto());
		query.setParameter("pPrix", pr.getPrix());
		query.setParameter("pQua", pr.getQuantite());
		query.setParameter("pIdp", pr.getIdProduit());

		int verif = query.executeUpdate();

		return verif;
	}

	@Override
	public List<Produit> getProdByCategorie(Categorie cat) {
		try {
			// récupérer la session
			Session s = sf.getCurrentSession();

			// Création d'une requête HQL
			String req = "FROM Produit p WHERE p.ca.idCategorie=:pCat";

			// Récupération d'une query
			Query query = s.createQuery(req);

			// Paramétrage de la query
			query.setParameter("pCat", cat.getIdCategorie());

			// Récupération de la liste
			@SuppressWarnings("unchecked")
			List<Produit> listProduit = query.list();

			for (Produit pr : listProduit) {
				pr.setImage("data:image/png);base64," + Base64.encodeBase64String(pr.getPhoto()));
			}

			return listProduit;
		} catch (NullPointerException ex) {

			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Produit> getProdByKeyWord(String keyWord) {
		// récupérer la session
		Session s = sf.getCurrentSession();

		// Création d'une requête HQL
		String req = "FROM Produit p WHERE p.designation LIKE :pNom OR p.description LIKE :pDes";

		// Récupération d'une query
		Query query = s.createQuery(req);

		// Paramétrage de la query
		// Passage de l'entrée en expression like
		String rech = '%' + keyWord + '%';
		query.setParameter("pNom", rech);
		query.setParameter("pDes", rech);

		// Récupération de la liste
		@SuppressWarnings("unchecked")
		List<Produit> listProduit = query.list();

		for (Produit pr : listProduit) {
			pr.setImage("data:image/png);base64," + Base64.encodeBase64String(pr.getPhoto()));
		}
		return listProduit;
	}

	@Override
	public List<String> getAllProdId() {
		// récupérer la session
		Session s = sf.getCurrentSession();

		// Requete HQL pour obtenir la liste des ID produit
		String reqListCategorie = "SELECT pr.idProduit FROM Produit as pr";
		// Récupération d'une query
		Query query = s.createQuery(reqListCategorie);
		// Récupération de la liste
		@SuppressWarnings("unchecked")
		List<String> listIdProduit = query.list();

		return listIdProduit;
	}

	@Override
	public List<String> getAllProdNom() {
		// récupérer la session
		Session s = sf.getCurrentSession();

		// Requete HQL pour obtenir la liste des ID produit
		String reqListCategorie = "SELECT pr.designation FROM Produit as pr";
		// Récupération d'une query
		Query query = s.createQuery(reqListCategorie);
		// Récupération de la liste
		@SuppressWarnings("unchecked")
		List<String> listIdProduit = query.list();

		return listIdProduit;
	}

	@Override
	public int attribuerOffre(Produit pr) {
		// récupérer la session
		Session s = sf.getCurrentSession();

		// Requête HQL
		String req = "UPDATE Produit pr SET pr.offre=:pOff, pr.prix=:pPrix WHERE pr.idProduit=:pIdp";

		// récup du query
		Query query = s.createQuery(req);

		// Paramétrage
		query.setParameter("pOff", pr.getOffre());
		query.setParameter("pPrix", pr.getPrix());
		query.setParameter("pIdp", pr.getIdProduit());

		int verif = query.executeUpdate();

		return verif;
	}

}
