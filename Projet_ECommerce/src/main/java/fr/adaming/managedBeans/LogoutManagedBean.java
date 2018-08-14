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
	 * Constructeur vide n�cessaire � un ManagedBean
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
	 * Cette m�thode permet de r�cup�rer automatiquement la Session
	 * L'annotation @PostConstruct permet de r�aliser cette op�ration d�s le lancement
	 */
	// m�thode @PostConstruct
	@PostConstruct
	public void init() {

		// r�cup�rer la session
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

	}

	// M�thode logout
	/**
	 * M�thode pour supprimer la session en cours et ramener � la page login
	 * @return l'adresse du login
	 */
	public String executeLogout() {
		maSession.invalidate();
		return "login";
	}

}
