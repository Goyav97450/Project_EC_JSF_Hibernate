package fr.adaming.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



import fr.adaming.dao.IClientDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;

/**
 * @author Thibault L'annotation @Stateful définit la classe comme un EJB
 *         Session qui sera istanciable plusieurs fois
 */
@Stateful
public class ClientServiceImpl implements IClientService {

	/**
	 * Transformation de l'association entre service et dao L'annotation @EJB
	 * sert à définir le partage des tâches
	 */
	@EJB
	private IClientDao clDao;

	@Override
	public List<Categorie> getAllCategorie() {

		return clDao.getAllCategorie();
	}

	@Override
	public List<Produit> getProdByCategorie(Categorie cat) {

		return clDao.getProdByCategorie(cat);
	}

	@Override
	public List<Produit> getProdByKeyWord(String keyWord) {

		return clDao.getProdByKeyWord(keyWord);
	}

	@Override
	public Client saveClient(Client cl) {

		return clDao.saveClient(cl);
	}

	@Override
	public Commande saveCommande(Commande co) {

		return clDao.saveCommande(co);
	}

	@Override
	public Categorie getCatByNom(String rech) {

		return clDao.getCatByNom(rech);
	}

	@Override
	public LigneCommande ajoutProdPanier(Produit prod, int q) {

		LigneCommande LC = new LigneCommande();
		if (q <= prod.getQuantite()) {
			LC.setPr(prod);
			LC.setQuantite(q);
			LC.setPrix(prod.getPrix() * q);
			return LC;
		} else {
			return null;
		}

	}

	@Override
	public int supprProdPanier(Produit prod, Panier panier) {

		for (LigneCommande lc : panier.getListeCom()) {
			if (lc.getPr().getIdProduit() == prod.getIdProduit()) {
				panier.getListeCom().remove(lc);

				return 1;
			}
		}

		return 0;
	}

	public void sendMail(ByteArrayOutputStream output, Client cl, Commande co) {

		final String username = "thibault.sch974@gmail.com";
		final String password = "22oct1993";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		// Get Session object.
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			//Création d'un mimeBodypart contenant le text du mail
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText("Mr/M " + cl.getNomClient() + "," + "\n\n Votre commande N° : " + co.getIdCommande()
			+ " a bien été enregistrée, merci de votre confiance.");
			
			byte[] bytes = output.toByteArray();
			
			//Création d'un mimeBodypart contenant le PDF
			DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
			MimeBodyPart pdfBodypart = new MimeBodyPart();
			pdfBodypart.setDataHandler(new DataHandler(dataSource));
			pdfBodypart.setFileName("recu"+co.getIdCommande()+".pdf");
			
			//Création d'un MimeMultiPart
			MimeMultipart mimeMultiPart = new MimeMultipart();
			mimeMultiPart.addBodyPart(textBodyPart);
			mimeMultiPart.addBodyPart(pdfBodypart);
			
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(username));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(cl.getEmail()));

			// Set Subject: header field
			message.setSubject("Récapitulatif de la commande N° : " + co.getIdCommande());
			message.setContent(mimeMultiPart);

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void createPDF(OutputStream output, Commande co, Client cl) {
		// Calcul du prix de la commande
		double total = 0;

		for (LigneCommande lc : co.getListeLigne()) {
			total = total + lc.getPrix();
		}

		// Création du PDF
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, output);

			document.open();

			document.addTitle("Reçu de la commande : " + co.getIdCommande());
			document.addSubject("Reçu de la commande : " + co.getIdCommande());
			document.addAuthor("Marchambulant");

			Paragraph pa1 = new Paragraph();
			pa1.add(new Chunk("Mr/M " + cl.getNomClient() + "," + "\n\n Votre commande N° : " + co.getIdCommande()
					+ " a bien été enregistrée, merci de votre confiance."));

			Paragraph pa2 = new Paragraph();
			pa2.add(new Chunk("Le prix de votre commande est : " + total + " €."));
			document.add(pa1);
			document.add(pa2);

			document.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
