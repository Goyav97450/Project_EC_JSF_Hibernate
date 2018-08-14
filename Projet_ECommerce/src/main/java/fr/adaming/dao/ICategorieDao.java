package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

/**
 * @author Ewen Fondrillon Interface Dao des m�thodes reli�es aux
 *         fonctionnalit�s Categories L'annotation @Local permet au conteneur
 *         EJB de comprendre que les classes impl�mentant cette interface seront
 *         des EJB session avec une port�e <i>locale</i>
 */
@Local
public interface ICategorieDao {

	/**
	 * <b>addCategorie</b> Cette m�thode permet � un admin d'ajouter une
	 * cat�gorie � la base de donn�es.
	 * 
	 * @param la
	 *            cat�gorie qu'on veut ajouter � la base de donn�es
	 * @return un int g�n�r� par la requ�te pour confirmer l'ajout.
	 */
	public int addCategorie(Categorie ca);

	/**
	 * <b>getAllCategorie</b> Cette m�thode permet � un admin de r�cup�rer la
	 * liste compl�te des categories.
	 * 
	 * @return la liste des categories existantes en base de donn�e
	 */
	public List<Categorie> getAllCategorie();

	/**
	 * <b>deleteCategorie</b> Cette m�thode permet � un admin de supprimer une
	 * categorie de la base de donn�es
	 * 
	 * @param la
	 *            categorie qu'on veut supprimer de la base de donn�es.
	 * @return un int retourn� par la requ�te pour confirmer la suppression.
	 */
	public int deleteCategorie(Categorie ca);

	/**
	 * <b>updateCategorie</b> Cette m�thode permet � un admin de mettre � jour
	 * une categorie de la base de donn�es
	 * 
	 * @param le
	 *            produit qu'on veut mettre � jour dans la base de donn�es
	 * @return le produit qu'on a mis � jour dans la base, pour confirmer la
	 *         mise � jour.
	 */
	public Categorie updateCategorie(Categorie ca);

	/**
	 * <b>getByIdCategorie</b> Cette m�thode permet � un admin de chercher une
	 * categorie sp�cifique dans la base de donn�e par son identifiant
	 * 
	 * @param le
	 *            produit qu'on recherche
	 * @return le produit correspondant � notre param�tre de recherche
	 */
	public Categorie getByIdCategorie(Categorie ca);

	/**
	 * <b>getCatByNom</b> Cette m�thode permet de r�cup�rer un cat�gorie par son
	 * nom
	 * 
	 * @param Nom
	 *            de la cat�gorie recherch�e
	 * @return la cat�gorie correspondant
	 */
	public Categorie getCatByNom(String rech);

	/**
	 * <b>getAllCatId</b> Cette m�thode permet de r�cup�rer un cat�gorie par son
	 * nom
	 * 
	 * @param Nom
	 *            de la cat�gorie recherch�e
	 * @return la cat�gorie correspondant
	 */
	public List<String> getAllCatId();
}
