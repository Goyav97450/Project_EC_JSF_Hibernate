package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

/**
 * @author Thibault Classe impl�mentant l'interface ICommandeDao
 *         L'annotation @Repository permet au conteneur SpringIoC d'indentifier
 *         cette classe en tant que Bean
 */
@Repository
public class CommandeDaoImpl implements ICommandeDao{

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
	public Commande saveCommande(Commande co) {
		//R�cup�ration de la session
		Session s = sf.getCurrentSession();
		
		//Enregistrement de la commande dans DB
		s.save(co);
		
		return co;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Commande> getCommandeByClient(Client cl) {
		//R�cup�ration d'une session Hibernate
		Session s = sf.getCurrentSession();
		
		//Cr�ation d'une requ�te HQL
		String req = "FROM Commande co WHERE co.client.idClient=:pId";
		
		//R�cup�ration d'une query hibernate
		Query query = s.createQuery(req);
		
		//param�trages de la requ�te
		query.setParameter("pId", cl.getIdClient());
		
		//R�cup�ration de la requ�te		
		return query.list();
	}

}
