package fr.adaming.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;

/**
 * @author Thibault
 * Interface Service des m�thodes relier aux commandes
 */
public interface ICommandeService {

	/**<b>saveCommande</b>
	 * Cette m�thode perment d'enregistrer une commande dans la DB
	 * @param la Commande � enregistrer
	 * @return un entier qui vaut 1 si l'ajout a fonctionn� et 0 si l'ajout a �chou�
	 */
	public Commande saveCommande(Commande co);
	
	/**<b>saveCommande</b>
	 * Cette m�thode perment d'enregistrer une commande dans la DB
	 * @param la Commande � enregistrer
	 * @return un entier qui vaut 1 si l'ajout a fonctionn� et 0 si l'ajout a �chou�
	 */
	public List<Commande> getCommandeByClient(Client cl);
	
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
