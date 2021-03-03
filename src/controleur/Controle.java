package controleur;

import javax.swing.JLabel;

import java.util.Collection;
import javax.swing.JPanel;

import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import modele.Joueur;
import modele.Objet;
import outils.connexion.AsyncResponse;
import outils.connexion.ClientSocket;
import outils.connexion.Connection;
import outils.connexion.ServeurSocket;
import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;

/**
 * Contrôleur et point d'entrée de l'applicaton 
 * @author emds
 *
 */
public class Controle implements AsyncResponse, Global {

	/**
	 * frame EntreeJeu
	 */
	private EntreeJeu frmEntreeJeu ;
	/**
	 * frame Arene
	 */
	private Arene frmArene;
	/**
	 * frame ChoixJoueur
	 */
	private ChoixJoueur frmChoixJoueur;
	/**
	 * instance du jeu (JeuServeur ou JeuClient)
	 */
	private Jeu leJeu;
	
	/**
	 * Méthode de démarrage
	 * @param args non utilisé
	 */
	public static void main(String[] args) {
		new Controle();
	}
	
	/**
	 * Constructeur
	 */
	private Controle() {
		this.frmEntreeJeu = new EntreeJeu(this) ;
		this.frmEntreeJeu.setVisible(true);
	}
	
	/**
	 * Demande provenant de la vue EntreeJeu
	 * @param info information à traiter
	 */
	public void evenementEntreeJeu(String info) {
		if(info.equals(SERVEUR)) {
			new ServeurSocket(this, PORT);
			this.leJeu = new JeuServeur(this);
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene(this, SERVEUR);
			((JeuServeur)leJeu).constructionMurs();
			this.frmArene.setVisible(true);
		} else {
			new ClientSocket(this, info, PORT);
		}
	}
	
	/**
	 * Informations provenant de la vue ChoixJoueur
	 * @param pseudo le pseudo du joueur
	 * @param numPerso le numéro du personnage choisi par le joueur
	 */
	public void evenementChoixJoueur(String pseudo, int numPerso) {
		this.frmChoixJoueur.dispose();
		this.frmArene.setVisible(true);
		((JeuClient)this.leJeu).envoi(PSEUDO+STRINGSEPARE+pseudo+STRINGSEPARE+numPerso);
	}

	/**
	 * Envoi d'informations vers l'ordinateur distant
	 * @param connection objet de connexion pour l'envoi vers l'ordinateur distant
	 * @param info information à envoyer
	 */
	public void envoi(Connection connection, Object info) {
		connection.envoi(info);
	}
	
	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch(ordre) {
		case CONNEXION :
			if(!(this.leJeu instanceof JeuServeur)) {
				this.leJeu = new JeuClient(this);
				this.leJeu.connexion(connection);
				this.frmEntreeJeu.dispose();
				this.frmArene = new Arene(this, CLIENT);
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);
			} else {
				this.leJeu.connexion(connection);
			}
			break;
		case RECEPTION :
			this.leJeu.reception(connection, info);
			break;
	
		case DECONNEXION :
			break;
		}
		
	}
	/**
	 * méthode evènement Jeu serveur
	 * @param ordre 
	 * @param info
	 */
	public void evenementJeuServeur( String ordre, Object info) {
		switch (ordre) {
		case AJOUT_MUR :
			frmArene.ajoutMurs(info);
			break;
		case AJOUT_PANEL_MUR :
			leJeu.envoi((Connection)info, frmArene.getJpnMurs());
			break;
		case AJOUT_LBLJEU :
			frmArene.ajoutLabelJeu((JLabel)info);
			break;
		case AJOUT_PNLJEU :
			leJeu.envoi((Connection)info, frmArene.getJpnJeu());
			break;
		case AJOUT_PHRASE :
			this.frmArene.ajoutChat((String)info);
			((JeuServeur)leJeu).envoi(frmArene.getTxtChat());
			break;
			
		}
	}

	/**
	 * méthode evènement Jeu Client
	 * @param ordre 
	 * @param info
	 */
	public void evenementJeuClient(String ordre, Object info) {
		// TODO Auto-generated method stub
		switch (ordre) {
		case AJOUT_PANEL_MUR :
			frmArene.setJpnMurs((JPanel)info);
			break;
		case AJOUT_PNLJEU :
			frmArene.setJpnJeu((JPanel)info);
			break;
		case MODIF_TCHAT :
			frmArene.setTxtChat((String)info);
			break;
		}
	}

	/**
	 * méthode évènement arène
	 * 
	 */
	
	public void evenementArene(String info) {
		((JeuClient)this.leJeu).envoi(TCHAT+STRINGSEPARE+info);

	}
}

