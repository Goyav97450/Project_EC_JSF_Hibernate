package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
public class ProduitDaoImpl implements IProduitDao {
	/**
	 * Récupération d'un entityManager à partir de la PersistenteUnit grâce à
	 * l'annotation @PersistenceContext
	 */
	@PersistenceContext(unitName = "Projet_Ecormmerce")
	private EntityManager em;

	@Override
	public int addProduit(Produit pr) {

		// Requete JPQL pour obtenir la liste des produits
		String reqListProduit = "SELECT pr FROM Produit as pr";
		// Création de la requête
		Query query = em.createQuery(reqListProduit);
		// Récupération de la liste des produits
		List<Produit> oldListProduit = query.getResultList();
		// Inscription du produits dans la base de données
		em.persist(pr);

		// Requete JPQL pour obtenir la liste des produits avec l'ajout
		String reqNewListProduit = "SELECT pr FROM Produit as pr";
		Query queryNew = em.createQuery(reqNewListProduit);
		// Récupération de la liste des produits
		List<Produit> newListProduit = queryNew.getResultList();
		// Comparaison des deux listes pour confirmer l'ajout
		if (oldListProduit.size() != newListProduit.size()) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public List<Produit> getAllProduit() {
		// Requete JPQL pour obtenir la liste des produits
		String reqListProduit = "SELECT pr FROM Produit as pr";
		// Création de la requête
		Query query = em.createQuery(reqListProduit);
		// Récupération de la liste des produits
		List<Produit> listProduit = query.getResultList();

		for (Produit pr : listProduit) {
			pr.setImage("data:image/png);base64," + Base64.encodeBase64String(pr.getPhoto()));
		}

		return listProduit;
	}

	@Override
	public int deleteProduit(Produit pr) {
		// Requete JPQL pour obtenir supprimer le produit en paramètre
		String req = "DELETE FROM Produit as pr WHERE pr.idProduit=:pIdp";
		// Création de la requête
		Query query = em.createQuery(req);

		// Paramétrages
		query.setParameter("pIdp", pr.getIdProduit());

		return query.executeUpdate();

	}

	@Override
	public Produit getByIdProduit(Produit pr) {
		try {// Récupération du produit dans la base de données et stockage
				// dans un objet
			Produit prFound = em.find(Produit.class, pr.getIdProduit());
			// Assignation de l'image au produit
			prFound.setImage("data:image/png);base64," + Base64.encodeBase64String(prFound.getPhoto()));
			return prFound;
		} catch (NullPointerException ex) {

			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateProduit(Produit pr) {
		// Requête JPQL
		String req = "UPDATE Produit pr SET pr.description=:pDes, pr.designation=:pDesi, pr.photo=:pPho, pr.prix=:pPrix, pr.quantite=:pQua WHERE pr.idProduit=:pIdp";

		// Récupérer le query
		Query query = em.createQuery(req);

		// paramètres
		query.setParameter("pDes", pr.getDescription());
		query.setParameter("pDesi", pr.getDesignation());
		query.setParameter("pPho", pr.getPhoto());
		query.setParameter("pPrix", pr.getPrix());
		query.setParameter("pQua", pr.getQuantite());
		query.setParameter("pIdp", pr.getIdProduit());

		int verif = query.executeUpdate();
		return verif;
	}

	public List<Produit> getProdByCategorie(Categorie cat) {
		try {// Création d'une requête JPQL
			String req = "SELECT p FROM Produit p WHERE p.ca.idCategorie=:pCat";

			// Récupération d'une query
			Query query = em.createQuery(req);

			// Paramétrage de la query
			query.setParameter("pCat", cat.getIdCategorie());

			// Récupération de la liste
			List<Produit> listProduit = query.getResultList();

			for (Produit pr : listProduit) {
				pr.setImage("data:image/png);base64," + Base64.encodeBase64String(pr.getPhoto()));
			}

			return listProduit;
		} catch (NullPointerException ex) {

			ex.printStackTrace();
		}
		return null;
	}

	public List<Produit> getProdByKeyWord(String keyWord) {
		// Création d'une requête JPQL
		String req = "SELECT p FROM Produit p WHERE p.designation LIKE ?1 OR p.description LIKE ?2";

		// Récupération d'une query
		Query query = em.createQuery(req);

		// Paramétrage de la query
		// Passage de l'entrée en expression like
		String rech = '%' + keyWord + '%';
		query.setParameter(1, rech);
		query.setParameter(2, rech);

		// Récupération de la liste
		List<Produit> listProduit = query.getResultList();

		for (Produit pr : listProduit) {
			pr.setImage("data:image/png);base64," + Base64.encodeBase64String(pr.getPhoto()));
		}
		return listProduit;
	}

	@Override
	public List<String> getAllProdId() {
		// Requete JPQL pour obtenir la liste des ID produit
		String reqListCategorie = "SELECT pr.idProduit FROM Produit as pr";
		// Récupération d'une query
		Query query = em.createQuery(reqListCategorie);
		// Récupération de la liste
		List<String> listIdProduit = query.getResultList();

		return listIdProduit;
	}

	@Override
	public List<String> getAllProdNom() {
		// Requete JPQL pour obtenir la liste des ID produit
		String reqListCategorie = "SELECT pr.designation FROM Produit as pr";
		// Récupération d'une query
		Query query = em.createQuery(reqListCategorie);
		// Récupération de la liste
		List<String> listIdProduit = query.getResultList();

		return listIdProduit;
	}
}
