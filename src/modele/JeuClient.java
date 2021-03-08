package modele;
import controleur.Global;

import javax.swing.JPanel;

import controleur.Controle;
import outils.connexion.Connection;

/**
 * Gestion du jeu côté client
 *
 */
public class JeuClient extends Jeu implements Global {
	
	/**
	 * objet de connexion pour communiquer avec le serveur
	 */
	private Connection connection;
	private Boolean mursOK = false;
	
	/**
	 * Controleur
	 * @param controle instance du contrôleur pour les échanges
	 */
	public JeuClient(Controle controle) {
		super.controle = controle;
	}
	
	@Override
	public void connexion(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void reception(Connection connection, Object info) {
		if (info instanceof JPanel) { 
			if (!mursOK) {
				controle.evenementJeuClient(AJOUT_PANEL_MUR, info);
				mursOK = true;
			}else {
				controle.evenementJeuClient(AJOUT_PNLJEU, info);	
			}
		} else if (info instanceof String) {
				controle.evenementJeuClient(MODIF_TCHAT, info);
		} else if (info instanceof Integer) {
			this.controle.evenementJeuClient(JOUE_SON, info);
		}
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers le serveur
	 * fais appel une fois à l'envoi dans la classe Jeu
	 * @param info information à envoyer au serveur
	 */
	public void envoi(String info) {
		super.envoi(this.connection, info);
	}

}
