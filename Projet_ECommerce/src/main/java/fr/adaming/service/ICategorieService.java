package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

/**
 * @author Ewen 
 * L'annotation @Local permet au conteneur EJB de comprendre que
 *         les classes implémentant cette interface seront des EJB session avec
 *         une portée <i>locale</i>
 */

@Local
public interface ICategorieService {
	/**
	 * <b>addCategorie</b> Cette méthode permet à un admin d'ajouter une
	 * catégorie à la base de données.
	 * 
	 * @param la
	 *            catégorie qu'on veut ajouter à la base de données
	 * @return un int généré par la requête pour confirmer l'ajout.
	 */
	public int addCategorieService(Categorie ca);

	/**
	 * <b>getAllCategorie</b> Cette méthode permet à un admin de récupérer la
	 * liste complète des categories.
	 * 
	 * @return la liste des categories existantes en base de donnée
	 */
	public List<Categorie> getAllCategorieService();

	/**
	 * <b>deleteCategorie</b> Cette méthode permet à un admin de supprimer une
	 * categorie de la base de données
	 * 
	 * @param la
	 *            categorie qu'on veut supprimer de la base de données
	 * @return la categorie qu'on a supprimé de la base, pour confirmer la
	 *         suppression
	 */
	public int deleteCategorieService(Categorie ca);

	/**
	 * <b>updateCategorie</b> Cette méthode permet à un admin de mettre à jour
	 * une categorie de la base de données
	 * 
	 * @param le
	 *            produit qu'on veut mettre à jour dans la base de données
	 * @return le produit qu'on a mis à jour dans la base, pour confirmer la
	 *         mise à jour
	 */
	public Categorie updateCategorieService(Categorie ca);

	/**
	 * <b>getByIdCategorie</b> Cette méthode permet à un admin de chercher une
	 * categorie spécifique dans la base de donnée par son identifiant
	 * 
	 * @param le
	 *            produit qu'on recherche
	 * @return le produit correspondant à notre paramètre de recherche
	 */
	public Categorie getByIdCategorieService(Categorie ca);

	/**
	 * <b>getCatByNom</b> Cette méthode permet de récupérer un catégorie par son
	 * nom
	 * 
	 * @param Nom
	 *            de la catégorie recherchée
	 * @return la catégorie correspondant
	 */
	public Categorie getCatByNomService(String rech);
	
	/**<b>getAllCatId</b>
	 * Cette méthode permet de récupérer un catégorie par son nom
	 * @param Nom de la catégorie recherchée
	 * @return la catégorie correspondant
	 */
	public List<String> getAllCatIdService();
}
