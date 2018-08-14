package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
/**
 * @author Ewen ManagedBean correspondant au Logout
 */
@ManagedBean(name = "logout")
@SessionScoped
public class LogoutManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Attributs
	/**
	 * Attribut Session contenant les informations de la session, en particulier l'Admin
	 */
	private HttpSession maSession;

	/**
	 * Constructeur vide nécessaire à un ManagedBean
	 */
	public LogoutManagedBean() {
		super();
	}

	// Getters and Setters
	/**
	 * @return la session en cours
	 */
	public HttpSession getMaSession() {
		return maSession;
	}
	
	/**
	 * @param la session
	 */
	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}

	/**
	 * Cette méthode permet de récupérer automatiquement la Session
	 * L'annotation @PostConstruct permet de réaliser cette opération dès le lancement
	 */
	// méthode @PostConstruct
	@PostConstruct
	public void init() {

		// récupérer la session
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

	}

	// Méthode logout
	/**
	 * Méthode pour supprimer la session en cours et ramener à la page login
	 * @return l'adresse du login
	 */
	public String executeLogout() {
		maSession.invalidate();
		return "login";
	}

}
