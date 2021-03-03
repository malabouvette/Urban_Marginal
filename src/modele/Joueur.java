package modele;

import java.util.ArrayList;
import java.awt.Font;
import java.awt.font.*;
import java.net.URL;
import java.util.Collection;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controleur.Global;
import outils.connexion.Connection;

/**
 * Gestion des joueurs
 *
 */
public class Joueur extends Objet implements Global {


	/**
	 * pseudo saisi
	 */
	private String pseudo ;
	/**
	 * n° correspondant au personnage (avatar) pour le fichier correspondant
	 */
	private int numPerso ; 
	/**
	 * instance de JeuServeur pour communiquer avec lui
	 */
	private JeuServeur jeuServeur ;
	/**
	 * numéro d'étape dans l'animation (de la marche, touché ou mort)
	 */
	private int etape ;
	/**
	 * la boule du joueur
	 */
	private Boule boule ;
	/**
	 * vie restante du joueur
	 */
	private int vie ; 
	/**
	 * tourné vers la gauche (0) ou vers la droite (1)
	 */
	private int orientation ;
	/**
	 * JLabel message perso
	 * contient pseudo et vie perso
	 * affiché en dessous du perso
	 */
	private JLabel lblMessage;
	
	/**
	 * collection de murs
	 */
	private Collection <Mur> lesMurs ;
	
	/**
	 * collection de Joueur
	 */
	private Collection <Joueur> lesJoueurs ;
	/**
	 * getter le pseudo du joueur
	 * @return pseudo
	 */
	public String getPseudo() {
		return this.pseudo;
	}
	
	
	/**
	 * Constructeur
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		vie = MAXVIE ;
		etape = 1 ;
		orientation = 0;
		
	}

	/**
	 * Initialisation d'un joueur (pseudo et numéro, calcul de la 1ère position, affichage, création de la boule)
	 * @param numPerso numéro du personnage
	 * @param pseudo pseudo du joueur
	 */
	public void initPerso(String pseudo, int numPerso, Collection <Joueur> lesJoueurs, ArrayList <Mur> lesMurs) {
		this.pseudo = pseudo;
		this.numPerso = numPerso;
		//System.out.println("joueur "+pseudo+" - num perso "+numPerso+" créé");
		
	
		 super.jLabel = new JLabel();
		
		// appel première position
		premierePosition(lesJoueurs, lesMurs);
		
		lblMessage = new JLabel(pseudo +" : "+ vie , JLabel.CENTER);
		lblMessage.setBounds(this.posX - 10, this.posY - HAUTEUR_LBLMESSAGE, LARGEURPERSO + 10, HAUTEUR_LBLMESSAGE);
		lblMessage.setFont(new Font("Dialog", Font.PLAIN, 8));
		lblMessage.setVisible(true);
		
		
		
		// appel de l'insertion du label joueur (objets)
		jeuServeur.ajoutLabelJeuArene(jLabel);
		// appel de l'insertion du label message
		jeuServeur.ajoutLabelJeuArene(lblMessage);
		
		// afficher le personnage
		affiche(MARCHE, etape);
		
	}

	/**
	 * Calcul de la première position aléatoire du joueur (sans chevaucher un autre joueur ou un mur)
	 */
	private void premierePosition(Collection <Joueur> lesJoueurs, Collection <Mur> lesMurs) {
		jLabel.setBounds(0,0,LARGEURPERSO,HAUTEURPERSO);
		do { 
			posX = (new Random()).nextInt((LARGEUR_ARENE - LARGEURPERSO));
			posY = (new Random()).nextInt((HAUTEUR_ARENE - HAUTEURPERSO + HAUTEUR_LBLMESSAGE));
		}while (this.toucheJoueur(lesJoueurs) || this.toucheMur(lesMurs)); {	
		}
		
		
	}
	
	/**
	 * Affiche le personnage et son message
	 * @param etape Etape dans le mouvement du personnage
	 * @param etat etat du personnage : "marche", "touche", "mort"
	 */
	public void affiche(String etat, int etape) {
		// positionnement du personnage et affectation de la bonne image
		super.jLabel.setBounds(posX, posY, LARGEURPERSO, HAUTEURPERSO);
		String chemin = CHEMINPERSONNAGES+PERSO+this.numPerso+etat+etape+"d"+this.orientation+EXTFICHIERPERSO;
		URL resource = getClass().getClassLoader().getResource(chemin);
		super.jLabel.setIcon(new ImageIcon(resource));
		// positionnement et remplissage du message sous le perosnnage
		this.lblMessage.setBounds(posX-10, posY+HAUTEURPERSO, LARGEURPERSO+10, HAUTEUR_LBLMESSAGE);
		this.lblMessage.setText(pseudo+" : "+vie);
		// demande d'envoi à tous des modifications d'affichage
		this.jeuServeur.envoiJeuATous();
	}

	/**
	 * Gère une action revue et qu'il faut afficher (déplacement, tire de boule...)
	 */
	public void action() {
	}

	/**
	 * Gère le déplacement du personnage
	 */
	private void deplace() { 
	}

	/**
	 * Contrôle si le joueur touche un des autres joueurs
	 * @return true si deux joueurs se touchent
	 */
	private Boolean toucheJoueur(Collection <Joueur> lesJoueurs) {
		for (Joueur unJoueur :lesJoueurs){
			if (!unJoueur.equals(this)){	
				if (super.toucheObjet(unJoueur)){
					return true ;
				}
			}
		} 
		return false;
	}
	

	/**
	* Contrôle si le joueur touche un des murs
	 * @return true si un joueur touche un mur
	 */
	private Boolean toucheMur(Collection <Mur> lesMurs) {
		for (Mur unMur : lesMurs) {
			if (super.toucheObjet(unMur)){
				return true;
			}	
		}
		return false;
	} 

	
	/**
	 * Gain de points de vie après avoir touché un joueur
	 */
	public void gainVie() {
	}
	
	/**
	 * Perte de points de vie après avoir été touché 
	 */
	public void perteVie() {
	}
	
	/**
	 * vrai si la vie est à 0
	 * @return true si vie = 0
	 */
	public Boolean estMort() {
		return null;
	}
	
	/**
	 * Le joueur se déconnecte et disparait
	 */
	public void departJoueur() {
	}

	
	
	
}
