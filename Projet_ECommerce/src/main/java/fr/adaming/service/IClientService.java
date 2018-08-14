/**
 * 
 */
package fr.adaming.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.ejb.Local;

import com.itextpdf.text.DocumentException;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;

/**
 * @author Thibault
 * L'annotation @Local permet au conteneur EJB de comprendre que les classes implémentant cette interface
 * seront des EJB session avec une portée <i>locale</i>
 */
@Local
public interface IClientService {

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
	
	/**<b>ajoutProdPanier</b>
	 * Cette méthode permet au client d'ajouter des produits à son panier
	 * @param prod : le produit à ajouter
	 * @param q : la quantité de produit à ajouter
	 * @return un entier qui vaut 1 si l'ajout a fonctionné et 0 sinon
	 */
	public LigneCommande ajoutProdPanier (Produit prod, int q);
	
	/**<b>supprProdPanier</b>
	 * Cette méthode permet de supprimer un produit du panier
	 * @param prod : le produit a supprimé
	 * @return une entier qui vaut 1 si la suppression a réussi 0 sinon
	 */
	public int supprProdPanier (Produit prod, Panier panier);
	
	/**<b>sendMail</b>
	 * Cette méthode permet d'envoyer un email à partir de l'application
	 * @param cl le client destinataire du mail
	 * @param co : la commande validée
	 */
	public void sendMail(ByteArrayOutputStream output, Client cl, Commande co);
	
	/**<b>createPDF</b>
	 * Cette méthode permet de créer un fichier PDF récapitulatif de la commande
	 * @param output
	 * @param co la commande validé
	 * @param cl le client associé à la commande
	 * @throws DocumentException
	 */
	public void createPDF(OutputStream output, Commande co, Client cl);
}
