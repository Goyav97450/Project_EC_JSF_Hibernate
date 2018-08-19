package fr.adaming.managedBeans;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.IProduitService;

/**
 * @author Thibault ManagedBean des activit�s propres au panier
 */
@ManagedBean(name = "paMB")
@RequestScoped
public class PanierManagedBean {

	// Attributs
	/**
	 * Attribut client qui servira au client pour s'enregistrer
	 */
	private Client cl;
	/**
	 * Attribut commande qui servira � enregistrer/afficher des commandes
	 */
	private Commande co;
	/**
	 * Attribut commande qui servira � afficher le panier et y rajouter des
	 * produits
	 */
	private Panier panier;
	/**
	 * Attribut produit servant � l'affichage des produits
	 */
	private Produit prod;
	/**
	 * Attribut lignedecommande qui va servir a remplir le panier et a pass�
	 * commande.
	 */
	private LigneCommande lc;
	/**
	 * Attribut servant au client � choisir la quantit� de produit � ajouter au
	 * panier
	 */
	private int q;
	private List<String> listNomProd;
	private HttpSession sessionCl;
	private String nom;

	// Transformation de l'association UML en Java
	/**
	 * Transformation de l'association UML en JAVA permet
	 * d'�tablir un couplage faible entre les services.
	 */
	@ManagedProperty(value = "#{clService}")
	private IClientService clService;

	// Setter obligatoire pour l'injection de d�pendance en JSF (utilisant
	// @ManagedProperty)
	public void setClService(IClientService clService) {
		this.clService = clService;
	}
	
	/**
	 * Transformation de l'association UML en JAVA L'annotation @EJB permet
	 * d'�tablir un couplage faible entre les services.
	 */
	@ManagedProperty(value = "#{prService}")
	private IProduitService prService;

	// Setter obligatoire pour l'injection de d�pendance en JSF (utilisant
	// @ManagedProperty)
	public void setPrService(IProduitService prService) {
		this.prService = prService;
	}
	
	/**
	 * Transformation de l'association UML en JAVA L'annotation @ManagedProperty permet
	 * d'�tablir un couplage faible entre les services.
	 */
	@ManagedProperty(value = "#{coService}")
	private ICommandeService coService;
	
	// Setter obligatoire pour l'injection de d�pendance en JSF (utilisant
	// @ManagedProperty)
	public void setCoService(ICommandeService coService) {
		this.coService = coService;
	}
	/**
	 * 
	 */
	public PanierManagedBean() {
		super();
		this.cl = new Client();
		this.co = new Commande();
		this.prod = new Produit();
		this.lc = new LigneCommande();
		this.panier = new Panier();
	}

	@PostConstruct
	public void init() {
		this.listNomProd = prService.getAllProdNom();
		this.sessionCl = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	
	

	/**
	 * @return the cl
	 */
	public Client getCl() {
		return cl;
	}

	/**
	 * @param cl
	 *            the cl to set
	 */
	public void setCl(Client cl) {
		this.cl = cl;
	}

	/**
	 * @return the co
	 */
	public Commande getCo() {
		return co;
	}

	/**
	 * @param co
	 *            the co to set
	 */
	public void setCo(Commande co) {
		this.co = co;
	}

	/**
	 * @return affiche le panier et les donn�es contenant dans le panier client
	 */
	public Panier getPanier() {
		return panier;
	}

	/**
	 * @param set
	 *            le panier du MB n�cessaire pour stocker les courses d'un
	 *            client
	 */
	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	/**
	 * @return le produit choisie
	 */
	public Produit getProd() {
		return prod;
	}

	/**
	 * @param set
	 *            un produit depusi un input du client
	 */
	public void setProd(Produit prod) {
		this.prod = prod;
	}

	/**
	 * @return permet de r�cup�rer une ligne de commande
	 */
	public LigneCommande getLc() {
		return lc;
	}

	/**
	 * @param permet
	 *            de cr�er une ligne de commande � partir des inputs clients
	 */
	public void setLc(LigneCommande lc) {
		this.lc = lc;
	}

	/**
	 * @return affiche la quantit� de produit d'une ligne d ecommande
	 */
	public int getQ() {
		return q;
	}

	/**
	 * @param permet
	 *            de set la quantit� de produit d'une ligne de commande
	 */
	public void setQ(int q) {
		this.q = q;
	}

	/**
	 * @return the listIdProd
	 */
	public List<String> getListNomProd() {
		return listNomProd;
	}

	/**
	 * @param listIdProd
	 *            the listIdProd to set
	 */
	public void setListNomProd(List<String> listNomProd) {
		this.listNomProd = listNomProd;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	// Autres m�thodes
	public String ajoutProdPanier() {
		Panier panierSession = (Panier) sessionCl.getAttribute("panierCl");

		List<LigneCommande> newList = new ArrayList<LigneCommande>();

		if (panierSession != null) {

			if (panierSession.getListeCom() != null) {
				List<LigneCommande> oldList = panierSession.getListeCom();

				System.out.println(oldList.size());
				for (LigneCommande elem : oldList) {
					newList.add(elem);
				}
			}
		}
		Produit prodOut = prService.getProdByKeyWord(nom).get(0);
		lc = coService.ajoutProdPanier(prodOut, q);

		if (lc != null) {
			newList.add(lc);
			panier.setListeCom(newList);

			// Envoie d'un message d'erreur
			sessionCl.setAttribute("panierCl", panier);

			return "panier";
		} else {
			// Envoie d'un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a �chou� veuillez r�essayer"));

			return "ajoutPanier";
		}
	}

	public String supprProdPanier() {
		Panier panierSession = (Panier) sessionCl.getAttribute("panierCl");

		if (panierSession == null | panierSession.getListeCom() == null) {
			// Envoie d'un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La suppression a �chou� veuillez r�essayer"));

			return "panier";
		} else {
			int verif = coService.supprProdPanier(prod, panierSession);

			if (verif != 0) {
				// Envoie d'un message de confirmation
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("La suppression a r�ussi, vous pouvez continuer vos achats"));

				return "panier";
			} else {
				// Envoie d'un message d'erreur
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("La suppression a �chou� veuillez r�essayer"));

				return "panier";
			}
		}
	}

	public String enregistrerCommande() {
		// Enregistrement du client
		int verif = clService.saveClient(cl);
		
		
		if (verif != 0) {
			// Association du client � la commande
			Client clOut = clService.getClientByMail(cl.getEmail());
			co.setClient(clOut);

			// r�cup�ration du panier de la session
			Panier panierSession = (Panier) sessionCl.getAttribute("panierCl");

			// Transfert des lignes de commandes du panier � la commande
			co.setListeLigne(panierSession.getListeCom());

			// Association de la commande aux ligne de commande
			for (LigneCommande lc : panierSession.getListeCom()) {
				lc.setCommande(co);
			}

			// R�cup�ration de la date du jour
			Date date = new Date();

			// Ajout de la date � la commande
			co.setDateCommande(date);

			// Enregistrement de la commande
			Commande coOut = coService.saveCommande(co);

			if (coOut != null) {
				ByteArrayOutputStream output = new ByteArrayOutputStream();

				coService.createPDF(output, coOut, clOut);

				coService.sendMail(output, clOut, coOut);

				// Envoie d'un message de succ�s
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Votre commande a bien �t� enregistr� merci pour vos achats"));

				// Fermeture de la session
				sessionCl.invalidate();

				return "accueil";
			} else {
				// Envoie d'un message d'erreur
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("La commande a �chou� veuillez r�essayer"));

				return "panier";
			}
		} else {
			// Envoie d'un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'enregistrement du client a �chou�"));

			return "enregistrement";
		}

	}
}
