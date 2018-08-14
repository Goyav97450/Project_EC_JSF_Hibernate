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
 * Interface Dao des méthodes relier aux clients
 * L'annotation @Local permet au conteneur EJB de comprendre que les classes implémentant cette interface
 * seront des EJB session avec une portée <i>locale</i>
 */
@Local
public interface IClientDao {
	
	/**<b>getAllCategorie</b>
	 * Cette méthode permet à un Client de récupérer la liste des catégories depuis la DB
	 * @return la liste des catégories présentent dans la DB
	 */
	public List<Categorie> getAllCategorie();
	
	/**<b>getProdByCategorie</b>
	 * Cette méthode permet à un Client de récupérer la liste de produits filtrés d'après une catégorie depuis la DB
	 * @param la catégorie choisie pour filtrer la recherche
	 * @return la liste des produits appartenant à la catégorie
	 */
	public List<Produit> getProdByCategorie(Categorie cat);
	
	/**<b>getProdByKeyWord</b>
	 * Cette méthode permet à un Client de récupérer la liste des produits filtrés par mots clés
	 * @param le mot clé utilisé pour filtrer la recherche
	 * @return la liste de produits contenant les mots clés
	 */
	public List<Produit> getProdByKeyWord (String keyWord);
	
	/**<b>saveClient</b>
	 * Cette méthode permet à un Client de s'enregistrer dans la DB pour pouvoir passer une commande
	 * @param le Client à enregistrer
	 * @return un entier qui vaut 1 si l'ajout a fonctionné et 0 si l'ajout a échoué
	 */
	public Client saveClient(Client cl);
	
	/**<b>saveCommande</b>
	 * Cette méthode perment d'enregistrer une commande dans la DB
	 * @param la Commande à enregistrer
	 * @return un entier qui vaut 1 si l'ajout a fonctionné et 0 si l'ajout a échoué
	 */
	public Commande saveCommande(Commande co);
	
	/**<b>getCatByNom</b>
	 * Cette méthode permet de récupérer un catégorie par son nom
	 * @param Nom de la catégorie recherchée
	 * @return la catégorie correspondant
	 */
	public Categorie getCatByNom(String rech);
}
