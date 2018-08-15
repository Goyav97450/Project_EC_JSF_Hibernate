package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

/**
 * @author Ewen Fondrillon Interface Dao des m�thodes reli�es aux
 *         fonctionnalit�s Produits
 */
public interface IProduitDao {
	/**
	 * <b>addProduit</b> Cette m�thode permet � un admin d'ajouter un produit �
	 * la base de donn�es.
	 * 
	 * @param le
	 *            produit qu'on veut ajouter � la base de donn�es
	 * @return un int g�n�r� par la requ�te pour confirmer l'ajout.
	 */
	public Produit addProduit(Produit pr);

	/**
	 * <b>getAllProduit</b> Cette m�thode permet � un admin de r�cup�rer la
	 * liste compl�te des produits.
	 * 
	 * @return la liste des produits existants en base de donn�e
	 */
	public List<Produit> getAllProduit();

	/**
	 * <b>deleteProduit</b> Cette m�thode permet � un admin de supprimer un
	 * produit de la base de donn�es
	 * 
	 * @param le
	 *            produit qu'on veut supprimer de la base de donn�es
	 * @return un int g�n�r� par la requ�te pour confirmer la suppression.
	 */
	public int deleteProduit(Produit pr);

	/**
	 * <b>getByIdProduit</b> Cette m�thode permet � un admin de chercher un
	 * produit sp�cifique dans la base de donn�e par son identifiant
	 * 
	 * @param le
	 *            produit qu'on recherche
	 * @return le produit correspondant � notre param�tre de recherche
	 */
	public Produit getByIdProduit(Produit pr);

	/**
	 * <b>updateProduit</b> Cette m�thode permet � un admin de mettre � jour un
	 * produit de la base de donn�es
	 * 
	 * @param le
	 *            produit qu'on veut mettre � jour dans la base de donn�es
	 * @return le produit qu'on a mis � jour dans la base, pour confirmer la
	 *         mise � jour
	 */
	public int updateProduit(Produit pr);

	/**
	 * <b>getProdByCategorie</b> Cette m�thode permet � un Admin de r�cup�rer la
	 * liste de produits filtr�s d'apr�s une cat�gorie depuis la DB
	 * 
	 * @param la
	 *            cat�gorie choisie pour filtrer la recherche
	 * @return la liste des produits appartenant � la cat�gorie
	 */
	public List<Produit> getProdByCategorie(Categorie cat);

	/**
	 * <b>getProdByKeyWord</b> Cette m�thode permet � un Admin de r�cup�rer la
	 * liste des produits filtr�s par mots cl�s
	 * 
	 * @param le
	 *            mot cl� utilis� pour filtrer la recherche
	 * @return la liste de produits contenant les mots cl�s
	 */
	public List<Produit> getProdByKeyWord(String keyWord);

	/**
	 * <b>getAllProdId</b> Cette m�thode permet de r�cup�rer la liste des ID de tous les
	 * produits.
	 * 
	 * @return Une liste des ID de tous les produits dans la base de donn�es.
	 */
	public List<String> getAllProdId();

	/**
	 * <b>getAllProdNom</b>Cette m�thode permet de r�cup�rer la liste des noms de tous les
	 * produits.
	 * 
	 * @return Une liste des noms de tous les produits dans la base de donn�es.
	 */
	public List<String> getAllProdNom();
}
