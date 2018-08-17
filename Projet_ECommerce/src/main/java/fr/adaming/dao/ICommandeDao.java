package fr.adaming.dao;


import fr.adaming.model.Categorie;
import fr.adaming.model.Commande;

/**
 * @author Thibault
 * Interface Dao des méthodes relier aux commandes
 */

public interface ICommandeDao {
	
	
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
	public Commande getCommandeByClient(Commande co);
	
	/**<b>getCatByNom</b>
	 * Cette méthode permet de récupérer un catégorie par son nom
	 * @param Nom de la catégorie recherchée
	 * @return la catégorie correspondant
	 */
	public Categorie getCatByNom(String rech);
}
