package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

/**
 * @author Ewen Fondrillon Interface Dao des méthodes reliées aux
 *         fonctionnalités Produits
 */
public interface IProduitDao {
	/**
	 * <b>addProduit</b> Cette méthode permet à un admin d'ajouter un produit à
	 * la base de données.
	 * 
	 * @param le
	 *            produit qu'on veut ajouter à la base de données
	 * @return un int généré par la requête pour confirmer l'ajout.
	 */
	public Produit addProduit(Produit pr);

	/**
	 * <b>getAllProduit</b> Cette méthode permet à un admin de récupérer la
	 * liste complète des produits.
	 * 
	 * @return la liste des produits existants en base de donnée
	 */
	public List<Produit> getAllProduit();

	/**
	 * <b>deleteProduit</b> Cette méthode permet à un admin de supprimer un
	 * produit de la base de données
	 * 
	 * @param le
	 *            produit qu'on veut supprimer de la base de données
	 * @return un int généré par la requête pour confirmer la suppression.
	 */
	public int deleteProduit(Produit pr);

	/**
	 * <b>getByIdProduit</b> Cette méthode permet à un admin de chercher un
	 * produit spécifique dans la base de donnée par son identifiant
	 * 
	 * @param le
	 *            produit qu'on recherche
	 * @return le produit correspondant à notre paramètre de recherche
	 */
	public Produit getByIdProduit(Produit pr);

	/**
	 * <b>updateProduit</b> Cette méthode permet à un admin de mettre à jour un
	 * produit de la base de données
	 * 
	 * @param le
	 *            produit qu'on veut mettre à jour dans la base de données
	 * @return le produit qu'on a mis à jour dans la base, pour confirmer la
	 *         mise à jour
	 */
	public int updateProduit(Produit pr);

	/**
	 * <b>getProdByCategorie</b> Cette méthode permet à un Admin de récupérer la
	 * liste de produits filtrés d'après une catégorie depuis la DB
	 * 
	 * @param la
	 *            catégorie choisie pour filtrer la recherche
	 * @return la liste des produits appartenant à la catégorie
	 */
	public List<Produit> getProdByCategorie(Categorie cat);

	/**
	 * <b>getProdByKeyWord</b> Cette méthode permet à un Admin de récupérer la
	 * liste des produits filtrés par mots clés
	 * 
	 * @param le
	 *            mot clé utilisé pour filtrer la recherche
	 * @return la liste de produits contenant les mots clés
	 */
	public List<Produit> getProdByKeyWord(String keyWord);

	/**
	 * <b>getAllProdId</b> Cette méthode permet de récupérer la liste des ID de tous les
	 * produits.
	 * 
	 * @return Une liste des ID de tous les produits dans la base de données.
	 */
	public List<String> getAllProdId();

	/**
	 * <b>getAllProdNom</b>Cette méthode permet de récupérer la liste des noms de tous les
	 * produits.
	 * 
	 * @return Une liste des noms de tous les produits dans la base de données.
	 */
	public List<String> getAllProdNom();
}
