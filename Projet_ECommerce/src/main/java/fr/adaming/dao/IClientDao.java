/**
 * 
 */
package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Produit;

/**
 * @author Thibault
 * Interface Dao des m�thodes relier aux clients
 * L'annotation @Local permet au conteneur EJB de comprendre que les classes impl�mentant cette interface
 * seront des EJB session avec une port�e <i>locale</i>
 */
@Local
public interface IClientDao {
	
	/**<b>getAllCategorie</b>
	 * Cette m�thode permet � un Client de r�cup�rer la liste des cat�gories depuis la DB
	 * @return la liste des cat�gories pr�sentent dans la DB
	 */
	public List<Categorie> getAllCategorie();
	
	/**<b>getProdByCategorie</b>
	 * Cette m�thode permet � un Client de r�cup�rer la liste de produits filtr�s d'apr�s une cat�gorie depuis la DB
	 * @param la cat�gorie choisie pour filtrer la recherche
	 * @return la liste des produits appartenant � la cat�gorie
	 */
	public List<Produit> getProdByCategorie(Categorie cat);
	
	/**<b>getProdByKeyWord</b>
	 * Cette m�thode permet � un Client de r�cup�rer la liste des produits filtr�s par mots cl�s
	 * @param le mot cl� utilis� pour filtrer la recherche
	 * @return la liste de produits contenant les mots cl�s
	 */
	public List<Produit> getProdByKeyWord (String keyWord);
	
	/**<b>saveClient</b>
	 * Cette m�thode permet � un Client de s'enregistrer dans la DB pour pouvoir passer une commande
	 * @param le Client � enregistrer
	 * @return un entier qui vaut 1 si l'ajout a fonctionn� et 0 si l'ajout a �chou�
	 */
	public Client saveClient(Client cl);
	
	/**<b>saveCommande</b>
	 * Cette m�thode perment d'enregistrer une commande dans la DB
	 * @param la Commande � enregistrer
	 * @return un entier qui vaut 1 si l'ajout a fonctionn� et 0 si l'ajout a �chou�
	 */
	public Commande saveCommande(Commande co);
	
	/**<b>getCatByNom</b>
	 * Cette m�thode permet de r�cup�rer un cat�gorie par son nom
	 * @param Nom de la cat�gorie recherch�e
	 * @return la cat�gorie correspondant
	 */
	public Categorie getCatByNom(String rech);
}
