package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

/**
 * @author Ewen Fondrillon Interface Dao des méthodes reliées aux
 *         fonctionnalités Categories L'annotation @Local permet au conteneur
 *         EJB de comprendre que les classes implémentant cette interface seront
 *         des EJB session avec une portée <i>locale</i>
 */
@Local
public interface ICategorieDao {

	/**
	 * <b>addCategorie</b> Cette méthode permet à un admin d'ajouter une
	 * catégorie à la base de données.
	 * 
	 * @param la
	 *            catégorie qu'on veut ajouter à la base de données
	 * @return un int généré par la requête pour confirmer l'ajout.
	 */
	public int addCategorie(Categorie ca);

	/**
	 * <b>getAllCategorie</b> Cette méthode permet à un admin de récupérer la
	 * liste complète des categories.
	 * 
	 * @return la liste des categories existantes en base de donnée
	 */
	public List<Categorie> getAllCategorie();

	/**
	 * <b>deleteCategorie</b> Cette méthode permet à un admin de supprimer une
	 * categorie de la base de données
	 * 
	 * @param la
	 *            categorie qu'on veut supprimer de la base de données.
	 * @return un int retourné par la requête pour confirmer la suppression.
	 */
	public int deleteCategorie(Categorie ca);

	/**
	 * <b>updateCategorie</b> Cette méthode permet à un admin de mettre à jour
	 * une categorie de la base de données
	 * 
	 * @param le
	 *            produit qu'on veut mettre à jour dans la base de données
	 * @return le produit qu'on a mis à jour dans la base, pour confirmer la
	 *         mise à jour.
	 */
	public Categorie updateCategorie(Categorie ca);

	/**
	 * <b>getByIdCategorie</b> Cette méthode permet à un admin de chercher une
	 * categorie spécifique dans la base de donnée par son identifiant
	 * 
	 * @param le
	 *            produit qu'on recherche
	 * @return le produit correspondant à notre paramètre de recherche
	 */
	public Categorie getByIdCategorie(Categorie ca);

	/**
	 * <b>getCatByNom</b> Cette méthode permet de récupérer un catégorie par son
	 * nom
	 * 
	 * @param Nom
	 *            de la catégorie recherchée
	 * @return la catégorie correspondant
	 */
	public Categorie getCatByNom(String rech);

	/**
	 * <b>getAllCatId</b> Cette méthode permet de récupérer un catégorie par son
	 * nom
	 * 
	 * @param Nom
	 *            de la catégorie recherchée
	 * @return la catégorie correspondant
	 */
	public List<String> getAllCatId();
}
