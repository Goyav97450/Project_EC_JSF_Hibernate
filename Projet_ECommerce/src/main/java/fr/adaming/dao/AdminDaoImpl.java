package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Admin;

/**
 * @author Ewen Fondrillon Interface Dao des m�thodes reli�es aux
 *         fonctionnalit�s Admin
 * L'annotation Repository sert � d�clarer le DAO comme un bean de spring IoC.
 */
@Repository
public class AdminDaoImpl implements IAdminDao {
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

	/**
	 * <b>isExist</b> Cette m�thode permet de v�rifier qu'un Admin existe bien
	 * dans la base de donn�es via son ID et mot de passe, pour lui autoriser
	 * l'acc�s aux fonctionnalit�s admin.
	 * 
	 * @param l'Admin
	 *            dont on cherche � confirmer l'existence
	 * @return l'Admin dont l'existence est valid�e.
	 */
	@Override
	public Admin isExist(Admin a) {
		// requ�te HQL
		String req = "FROM Admin a WHERE a.idAdmin=:pIda AND a.mdp=:pMdp";

		// R�cup�rer la session
		Session s = sf.getCurrentSession();

		// R�cup�rer le query
		Query query = s.createQuery(req);

		// Passage des params
		query.setParameter("pIda", a.getIdAdmin());
		query.setParameter("pMdp", a.getMdp());

		// Envoyer la requ�te
		return  (Admin) query.uniqueResult();
	}

}
