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
 * Interface Service des méthodes relier aux commandes
 */
public interface ICommandeService {

	/**<b>saveCommande</b>
	 * Cette méthode perment d'enregistrer une commande dans la DB
	 * @param la Commande à enregistrer
	 * @return un entier qui vaut 1 si l'ajout a fonctionné et 0 si l'ajout a échoué
	 */
	public Commande saveCommande(Commande co);
	
	/**<b>saveCommande</b>
	 * Cette méthode perment d'enregistrer une commande dans la DB
	 * @param la Commande à enregistrer
	 * @return un entier qui vaut 1 si l'ajout a fonctionné et 0 si l'ajout a échoué
	 */
	public List<Commande> getCommandeByClient(Client cl);
	
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
