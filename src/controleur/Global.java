/**
 * 
 */
package controleur;

import java.awt.List;
import java.util.ArrayList;

/**
 * Global contient les constantes du programme
 * @author emds
 *
 */
public interface Global {
	
	/**
	 * N° du port d'écoute du serveur
	 */
	int PORT = 6666;
	/**
	 * Nombre de personnages différents
	 */
	int NBPERSOS = 3;
	/**
	 * Caractère de séparation dans un chemin de fichiers
	 */
	String CHEMINSEPARATOR = "/";
	/**
	 * Chemin du dossier des images de fonds
	 */
	String CHEMINFONDS = "fonds"+CHEMINSEPARATOR;
	/**
	 * Chemin du dossier de l'image de la boule
	 */
	String CHEMINBOULES = "boules"+CHEMINSEPARATOR;
	/**
	 * Chemin du dossier de l'image du mur
	 */
	String CHEMINMURS = "murs"+CHEMINSEPARATOR;
	/** 
	 * Début du nom des images des personnages
	 */
	String PERSO = "perso";			
	/**
	 * Chemin du dossier des images des personnages
	 */
	String CHEMINPERSONNAGES = "personnages"+CHEMINSEPARATOR;
	/**
	 * Chemin du dossier des sons
	 */
	String CHEMINSONS = "sons"+CHEMINSEPARATOR;
	/**
	 * Chemin de l'image de fond de la vue ChoixJoueur
	 */
	String FONDCHOIX = CHEMINFONDS+"fondchoix.jpg";
	/**
	 * Chemin de l'image de fond de la vue Arene
	 */
	String FONDARENE = CHEMINFONDS+"fondarene.jpg";
	/**
	 * Extension des fichiers des images des personnages
	 */
	String EXTFICHIERPERSO = ".gif";
	/**
	 * Chemin de l'image de la boule
	 */
	String BOULE = CHEMINBOULES+"boule.gif";
	/**
	 * Chemin de l'image du mur
	 */
	String MUR = CHEMINMURS+"mur.gif";
	/**
	 * chemin son
	 */
	String CHEMINSON = "sons"+CHEMINSEPARATOR;	
	/**
	 * extension fichier son
	 */
	String EXTFICHIERSON = ".wav" ;
	/**
	 * chemin son bienevenue
	 */
	String SONBIENVENUE = CHEMINSON+"welcome"+EXTFICHIERSON ;
	/**
	 * chemin son précédent
	 */
	String SONPRECEDENT = CHEMINSON+"precedent"+EXTFICHIERSON ;
	/**
	 * chemin son précédent
	 */
	String SONSUIVANT = CHEMINSON+"suivant"+EXTFICHIERSON ;
	/**
	 * chemin son go
	 */
	String SONGO= CHEMINSON+"go"+EXTFICHIERSON ;
	/**
	 * chemin son boule lancée
	 */
	String SONBOULE = CHEMINSON+"fight"+EXTFICHIERSON ;
	/**
	 * chemin son joueur blessé
	 */
	String SONBLESSE = CHEMINSON+"hurt"+EXTFICHIERSON ;
	/**
	 * chemin son joueur mort
	 */
	String SONMORT = CHEMINSON+"death"+EXTFICHIERSON ;
	/**
	 * tableau des sons de la vue arene
	 */
	String[] TABSON =  {SONBOULE, SONBLESSE, SONMORT} ;
	/**
	 * numéro correspondant au son FIGHT
	 */
	int FIGHT = 0;
	/**
	 * numéro correspondant au son HURT
	 */
	int HURT = 1;
	/**
	 * numéro correspondant au son DEATH
	 */
	int DEATH = 2;
	/**
	 * orde de jouer un son du combat
	 */
	String JOUE_SON = "joue un son";

	/**
	 * état marche du personnage
	 */
	String MARCHE = "marche";
	/**
	 * état touché du personnage
	 */
	String TOUCHE = "touche";
	/**
	 * état mort du personnage
	 */
	String MORT = "mort";
	/**
	 * Caractère de séparation dans les chaines transférées
	 */
	String STRINGSEPARE = "~";
	/**
	 * orientation du personnage
	 */
	String DIRECTION = "d" ;
	/**
	 * Message "connexion" envoyé par la classe Connection
	 */
	String CONNEXION = "connexion";
	/**
	 * Message "réception" envoyé par la classe Connection
	 */
	String RECEPTION = "réception";
	/**
	 * Message "déconnexion" envoyé par la classe Connection
	 */
	String DECONNEXION = "déconnexion";
	/**
	 * Message "pseudo" envoyé pour la création d'un joueur
	 */
	String PSEUDO = "pseudo";
	/**
	 * vie de départ pour tous les joueurs
	 */
	int MAXVIE = 10 ;
	/**
	 * gain de points de vie lors d'une attaque
	 */
	int GAIN = 1 ; 
	/**
	 * perte de points de vie lors d'une attaque
	 */
	int PERTE = 2 ; 
	/**
	 * largeur du joueur (label)
	 */
	int LARGEURPERSO= 35;
	/**
	 * hauteur du joueur (label)
	 */
	int HAUTEURPERSO = 35;
	/**
	 * hauteur du message accompagnant le joueur
	 */
	int HAUTEUR_LBLMESSAGE = 8;
	/**
	 * hauteur du mur
	 */
	int HAUTEUR_MUR = 35 ;
	/**
	 * largeur du mur
	 */
	int LARGEUR_MUR = 34 ;
	/**
	 * hauteur d'une boule
	 */
	int HAUTEUR_BOULE = 17 ;
	/**
	 * largeur d'une boule
	 */
	int LARGEUR_BOULE = 17 ;
	/**
	 * hauteur fenêtre Arene (sans le tchat)
	 */
	int HAUTEUR_ARENE = 600 ;
	/**
	 * largeur arène
	 */
	int LARGEUR_ARENE = 800 ;
	/**
	 * nombre de murs
	 */
	int NB_MURS = 20 ;
	/**
	 * ordre pour ajouter un mur dans l'arene du serveur
	 */
	String AJOUT_MUR = "ajout mur" ;
	/**
	 * ordre pour ajouter le panel mur dans l'arène du client 
	 */
	String AJOUT_PANEL_MUR = "ajout panel murs" ;
	/**
	 * axe des ordonnées
	 */
	String X = "x" ;
	/**
	 * axe des abscices
	 */
	String Y = "y" ;
	/**
	 * position X début des pavés
	 */
	int POSX_PAVES = 0 ;
	/**
	 ** position Y début des pavés
	 */
	int POSY_PAVES = 0 ;
	/**
	 * ordre pour ajouter le label jeu dans l'arene du serveur
	 */
	String AJOUT_LBLJEU = "ajout jlabel jeu";
	/**
	 * ordre pour ajouter le panel mur dans l'arène du client
	 */
	String AJOUT_PNLJEU = "ajout panel jeu" ;
	/**
	 * ordre pour ajouter texte Saisie dans text tchat
	 */
	String AJOUT_TEXTCHAT = "ajout text chat" ;
	/**
	 * ordre pour ajouter un message dans le tchat 
	 */
	String TCHAT = "tchat" ;
	/**
	 * séparateur dans tchat entre pseudo et message
	 */
	String FLECHE = ">" ;
	/**
	 * ordre pour ajouter une phrase dans le chat
	 */
	String AJOUT_PHRASE = "ajout phrase" ;
	/**
	 * odre de modifier le tchat
	 */
	String MODIF_TCHAT = "modif tchat" ;
	/**
	 * type de jeu client
	 */
	String CLIENT = "client";
	/**
	 * type de jeu serveur
	 */
	String SERVEUR = "serveur";
	/**
	 * information à faire apparaitre dans le chat arrivé nouveau joueur
	 */
	String ARRIVE = " vient de se connecter ";
	/**
	 * étoile encadrant nouveau message arrivé
	 */
	String ETOILES = "***" ;
	/**
	 * ordre action
	 */
	String ACTION = "action";
	/**
	 * taille d'un déplacement de personnage
	 */
	int PAS = 10 ;
	/**
	 * nombre d'étapes de marche des personnages
	 */
	Integer NBETAPESMARCHE = 4;
	/**
	 * nombre d'étapes de touché des personnages
	 */
	Integer NBETAPESTOUCHE = 2;
	/**
	 * nombre d'étapes de mort des personnages
	 */
	Integer NBETAPESMORT = 2  ;
	
	/**
	 * orientation des joueur pour chemins image perso
	 */
	Integer GAUCHE = 0;
	Integer DROITE = 1;
	
}	





