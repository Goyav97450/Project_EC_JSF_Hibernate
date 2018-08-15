package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Admin;
import fr.adaming.service.IAdminService;

/**
 * @author Ewen ManagedBean correspondant à la vue Admin
 */
@ManagedBean(name = "aMB")
@SessionScoped
public class AdminManagedBean implements Serializable {

	// Attributs
	/**
	 * Attribut Admin qui servira à l'admin pour se connecter
	 */
	private Admin a;
	/**
	 * Attribut loggedin qui servira conserver le statut de l'admin dans la
	 * session et afficher une confirmation de connection
	 */
	private boolean loggedIn = false;

	// Transformation de l'association UML en Java
	/**
	 * Transformation de l'association UML en JAVA L'annotation @ManagedProperty permet
	 * d'établir un couplage faible entre vue et service tout en conservant une connexion.
	 */
	@ManagedProperty(value = "#{aService}")
	private IAdminService aService;

	// Setter obligatoire pour l'injection de dépendance en JSF (utilisant
	// @ManagedProperty)
	public void setaService(IAdminService aService) {
		this.aService = aService;
	}

	// Constructeur
	/**
	 * Constructeur vide nécessaire à un ManagedBean
	 */
	public AdminManagedBean() {
		this.a = new Admin();
	}

	/**
	 * @return l'admin s'étant connecté
	 */
	public Admin getA() {
		return a;
	}

	/**
	 * @param set
	 *            l'admin du MB pour pouvoir l'enregistrer dans la session
	 */
	public void setA(Admin a) {
		this.a = a;
	}

	/**
	 * @return le statut de l'admin
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * @param permet
	 *            d'enregistrer le statut de l'admin
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	// Méthodes
	// Autres méthodes correspondant au cas d'utilisation Admin
	/**
	 * Méthode pour vérifier l'existence de l'admin dans la BD et lui permettre
	 * de se connecter
	 * 
	 * @return
	 */
	public String connexion() {
		// Appel de la méthode service
		Admin aExist = aService.isExistService(a);

		if (a != null) {
			// si l'admin existe bien, il peut se connecter
			this.loggedIn = true;
			// inscription de l'admin dans la session, ainsi que son statut
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("aSession", aExist);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedIn", loggedIn);
			return "accueilAdmin";
		} else {
			// si l'admin n'existe pas, renvoyer un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("ID ou mot de passe invalide, veuillez réessayer."));
			return "login";
		}
	}

}
