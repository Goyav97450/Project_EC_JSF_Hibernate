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
 * Classe impl�mentant l'interface IClientDao
 * L'annotation @Repository permet au conteneur SpringIoC d'indentifier cette classe en tant que Bean
 */
@Repository
public class ClientDaoImpl implements IClientDao {

	/**
	 * D�claration de l'attribut sessionFactory L'annotation @Autowired permet
	 * d'automatiser l'injection de d�pendances.
	 */
	@Autowired
	private SessionFactory sf;
	
	/**
	 * Setter pour utiliser pour l'injection de d�pendance
	 * 
	 * @param sf
	 *            correspond � la SessionFactory d'hibernate
	 */
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}


	@Override
	public int saveClient(Client cl) {
		//R�cup�ration de la session hibernate
		Session s = sf.getCurrentSession();
		try {
			//Appel de la m�thode modif
			s.saveOrUpdate(cl);
			
			return 1;
		} catch (Exception HibernateException) {			
			return 0;
		}
	}


	@Override
	public Client getClientByMail(Client cl) {
		//R�cup�ration de la session hibernate
		Session s = sf.getCurrentSession();
		
		//Cr�ation de la requ�te HQL
		String req = "FROM Client cl WHERE cl.mail=:pMail";
		
		//Cr�ation d'une query Hibernate
		Query query = s.createQuery(req);
		
		//Param�trages de la requp�te
		String param ="%" + cl.getEmail() + "%";
		query.setParameter("pMail", param);
		
		return (Client) query.uniqueResult();
	}


	@Override
	public Client getClientById(Client cl) {
		//R�cup�ration de la session hibernate
		Session s = sf.getCurrentSession();
		
		//Appel de la m�thode get		
		return (Client) s.get(Client.class, cl.getIdClient());
	}
	
	
}
