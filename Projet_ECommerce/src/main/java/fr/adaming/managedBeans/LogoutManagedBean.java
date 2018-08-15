package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Admin;

/**
 * @author Ewen ManagedBean correspondant au Logout
 */
@ManagedBean(name = "logout")
@SessionScoped
public class LogoutManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Attributs
	/**
	 * Attribut Session contenant les informations de la session, en particulier
	 * l'Admin
	 */
	private Admin a;

	/**
	 * Constructeur vide nécessaire à un ManagedBean
	 */
	public LogoutManagedBean() {
		super();
	}

	// Getters and Setters
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
	 * Cette méthode permet de récupérer automatiquement la Session
	 * L'annotation @PostConstruct permet de réaliser cette opération dès le
	 * lancement
	 */
	// méthode @PostConstruct
	@PostConstruct
	public void init() {
		this.a = (Admin) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("aSession");
	}

	// Méthode logout
	/**
	 * Méthode pour supprimer la session admin en cours et ramener à la page login
	 * 
	 * @return l'adresse du login
	 */
	public String executeLogout() {
		;
		return "login";
	}

}
