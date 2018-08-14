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
 * L'annotation @Local permet au conteneur EJB de comprendre que les classes impl�mentant cette interface
 * seront des EJB session avec une port�e <i>locale</i>
 */
@Local
public interface IClientService {

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
	
	/**<b>ajoutProdPanier</b>
	 * Cette m�thode permet au client d'ajouter des produits � son panier
	 * @param prod : le produit � ajouter
	 * @param q : la quantit� de produit � ajouter
	 * @return un entier qui vaut 1 si l'ajout a fonctionn� et 0 sinon
	 */
	public LigneCommande ajoutProdPanier (Produit prod, int q);
	
	/**<b>supprProdPanier</b>
	 * Cette m�thode permet de supprimer un produit du panier
	 * @param prod : le produit a supprim�
	 * @return une entier qui vaut 1 si la suppression a r�ussi 0 sinon
	 */
	public int supprProdPanier (Produit prod, Panier panier);
	
	/**<b>sendMail</b>
	 * Cette m�thode permet d'envoyer un email � partir de l'application
	 * @param cl le client destinataire du mail
	 * @param co : la commande valid�e
	 */
	public void sendMail(ByteArrayOutputStream output, Client cl, Commande co);
	
	/**<b>createPDF</b>
	 * Cette m�thode permet de cr�er un fichier PDF r�capitulatif de la commande
	 * @param output
	 * @param co la commande valid�
	 * @param cl le client associ� � la commande
	 * @throws DocumentException
	 */
	public void createPDF(OutputStream output, Commande co, Client cl);
}
