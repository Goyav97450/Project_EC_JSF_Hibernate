package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Admin;

/**
 * @author Ewen Fondrillon Interface Dao des méthodes reliées aux
 *         fonctionnalités Admin
 * L'annotation Repository sert à déclarer le DAO comme un bean de spring IoC.
 */
@Repository
public class AdminDaoImpl implements IAdminDao {
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

	/**
	 * <b>isExist</b> Cette méthode permet de vérifier qu'un Admin existe bien
	 * dans la base de données via son ID et mot de passe, pour lui autoriser
	 * l'accès aux fonctionnalités admin.
	 * 
	 * @param l'Admin
	 *            dont on cherche à confirmer l'existence
	 * @return l'Admin dont l'existence est validée.
	 */
	@Override
	public Admin isExist(Admin a) {
		// requête HQL
		String req = "FROM Admin a WHERE a.idAdmin=:pIda AND a.mdp=:pMdp";

		// Récupérer la session
		Session s = sf.getCurrentSession();

		// Récupérer le query
		Query query = s.createQuery(req);

		// Passage des params
		query.setParameter("pIda", a.getIdAdmin());
		query.setParameter("pMdp", a.getMdp());

		// Envoyer la requête
		return  (Admin) query.uniqueResult();
	}

}
