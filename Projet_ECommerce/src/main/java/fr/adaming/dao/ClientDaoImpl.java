package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Produit;

/**
 * @author Thibault
 * Classe implémentant l'interface IClientDao
 * L'annotation @Repository permet au conteneur SpringIoC d'indentifier cette classe en tant que Bean
 */
@Repository
public class ClientDaoImpl implements IClientDao {

	/**
	 * Déclaration de l'attribut sessionFactory L'annotation @Autowired permet
	 * d'automatiser l'injection de dépendances.
	 */
	@Autowired
	private SessionFactory sf;
	
	/**
	 * Setter pour utiliser pour l'injection de dépendance
	 * 
	 * @param sf
	 *            correspond à la SessionFactory d'hibernate
	 */
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}


	@Override
	public int saveClient(Client cl) {
		//Récupération de la session hibernate
		Session s = sf.getCurrentSession();
		try {
			//Appel de la méthode modif
			s.saveOrUpdate(cl);
			
			return 1;
		} catch (Exception HibernateException) {			
			return 0;
		}
	}


	@Override
	public Client getClientByMail(Client cl) {
		//Récupération de la session hibernate
		Session s = sf.getCurrentSession();
		
		//Création de la requête HQL
		String req = "FROM Client cl WHERE cl.mail=:pMail";
		
		//Création d'une query Hibernate
		Query query = s.createQuery(req);
		
		//Paramétrages de la requpête
		String param ="%" + cl.getEmail() + "%";
		query.setParameter("pMail", param);
		
		return (Client) query.uniqueResult();
	}


	@Override
	public Client getClientById(Client cl) {
		//Récupération de la session hibernate
		Session s = sf.getCurrentSession();
		
		//Appel de la méthode get		
		return (Client) s.get(Client.class, cl.getIdClient());
	}
	
	
}
