package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

/**
 * @author Ewen 
 * L'annotation @Local permet au conteneur EJB de comprendre que
 *         les classes impl�mentant cette interface seront des EJB session avec
 *         une port�e <i>locale</i>
 */

@Local
public interface ICategorieService {
	/**
	 * <b>addCategorie</b> Cette m�thode permet � un admin d'ajouter une
	 * cat�gorie � la base de donn�es.
	 * 
	 * @param la
	 *            cat�gorie qu'on veut ajouter � la base de donn�es
	 * @return un int g�n�r� par la requ�te pour confirmer l'ajout.
	 */
	public int addCategorieService(Categorie ca);

	/**
	 * <b>getAllCategorie</b> Cette m�thode permet � un admin de r�cup�rer la
	 * liste compl�te des categories.
	 * 
	 * @return la liste des categories existantes en base de donn�e
	 */
	public List<Categorie> getAllCategorieService();

	/**
	 * <b>deleteCategorie</b> Cette m�thode permet � un admin de supprimer une
	 * categorie de la base de donn�es
	 * 
	 * @param la
	 *            categorie qu'on veut supprimer de la base de donn�es
	 * @return la categorie qu'on a supprim� de la base, pour confirmer la
	 *         suppression
	 */
	public int deleteCategorieService(Categorie ca);

	/**
	 * <b>updateCategorie</b> Cette m�thode permet � un admin de mettre � jour
	 * une categorie de la base de donn�es
	 * 
	 * @param le
	 *            produit qu'on veut mettre � jour dans la base de donn�es
	 * @return le produit qu'on a mis � jour dans la base, pour confirmer la
	 *         mise � jour
	 */
	public Categorie updateCategorieService(Categorie ca);

	/**
	 * <b>getByIdCategorie</b> Cette m�thode permet � un admin de chercher une
	 * categorie sp�cifique dans la base de donn�e par son identifiant
	 * 
	 * @param le
	 *            produit qu'on recherche
	 * @return le produit correspondant � notre param�tre de recherche
	 */
	public Categorie getByIdCategorieService(Categorie ca);

	/**
	 * <b>getCatByNom</b> Cette m�thode permet de r�cup�rer un cat�gorie par son
	 * nom
	 * 
	 * @param Nom
	 *            de la cat�gorie recherch�e
	 * @return la cat�gorie correspondant
	 */
	public Categorie getCatByNomService(String rech);
	
	/**<b>getAllCatId</b>
	 * Cette m�thode permet de r�cup�rer un cat�gorie par son nom
	 * @param Nom de la cat�gorie recherch�e
	 * @return la cat�gorie correspondant
	 */
	public List<String> getAllCatIdService();
}
