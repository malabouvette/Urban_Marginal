package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JLabel;

import controleur.Controle;
import controleur.Global;
import outils.connexion.Connection;

/**
 * Gestion du jeu côté serveur
 *
 */
public class JeuServeur extends Jeu implements Global {



	/**
	 * Collection de murs
	 */
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>() ;
	/**
	 * Dictionnaire de joueurs indexé sur leur objet de connexion
	 */
	private Hashtable<Connection, Joueur> lesJoueurs = new Hashtable<Connection, Joueur>() ;
	
	/**
	 * Constructeur
	 * @param controle instance du contrôleur pour les échanges
	 */
	public JeuServeur(Controle controle) {
		super.controle = controle;
	}
	
	@Override
	public void connexion(Connection connection) {
		this.lesJoueurs.put(connection, new Joueur(this));
	}

	@Override
	public void reception(Connection connection, Object info) {
		String[] infos = ((String)info).split(STRINGSEPARE);
		String ordre = infos[0];
		switch(ordre) {
		case PSEUDO :
			controle.evenementJeuServeur(AJOUT_PANEL_MUR, connection);
			String pseudo = infos[1];
			int numPerso = Integer.parseInt(infos[2]);
			this.lesJoueurs.get(connection).initPerso(pseudo, numPerso, lesJoueurs.values(), lesMurs);
			controle.evenementJeuServeur(AJOUT_PHRASE, ETOILES+ pseudo + ARRIVE + ETOILES );
			break;
		case TCHAT :
			String phrase = infos[1];
			phrase = this.lesJoueurs.get(connection).getPseudo()+ FLECHE + phrase ;
			this.controle.evenementJeuServeur(AJOUT_PHRASE, phrase);
			break;
		}
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers tous les clients
	 * fais appel plusieurs fois à l'envoi de la classe Jeu
	 */
	public void envoi(Object info) {
		for(Connection connection : this.lesJoueurs.keySet()) {
			super.envoi(connection, info);			
		}
		
	}

	/**
	 * Génération des murs
	 */
	public void constructionMurs() {
		for (int k = 0; k < NB_MURS; k++) {
			lesMurs.add(k ,new Mur());
			controle.evenementJeuServeur(AJOUT_MUR, lesMurs.get(k).getjLabel());
		}
			
	}
	
	/** 
	 * ajout du label jeu dans arène par l'intermaédiaire du controleur
	 * @param JLAbel labelJeu
	 * return 
	 */
	public void ajoutLabelJeuArene(JLabel jLabel) {
		controle.evenementJeuServeur(AJOUT_LBLJEU, jLabel);
	}
	
	/**
	 * ajout du panel jeu sur les arene de tous les jeuclients
	 * 
	 */
	public void envoiJeuATous() {
		Collection <Connection> lesConnections = this.lesJoueurs.keySet();
		for (Connection connection : lesConnections) {
			this.controle.evenementJeuServeur(AJOUT_PNLJEU, connection);

		}
	}
}


