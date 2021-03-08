package modele;
import java.net.URL;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controleur.Global;

/**
 * Gestion de la boule
 *
 */
public class Boule extends Objet implements Global, Runnable{

	/**
	 * Collection de murs
	 */
	private Collection lesMurs;
	/**
	 * instance de JeuServeur pour la communication
	 */
	private JeuServeur jeuServeur ;
	/**
	 * joueur qui lance la boule
	 */
	private Joueur attaquant ;
	/**
	 * Constructeur
	 */
	public Boule(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		super.jLabel = new JLabel();
		URL resource = getClass().getClassLoader().getResource(BOULE);
		super.jLabel.setIcon(new ImageIcon(resource));		
		super.jLabel.setBounds(0, 0, LARGEUR_BOULE, HAUTEUR_BOULE);
		super.jLabel.setVisible(false);
	}
	
	/**
	 * Tire d'une boule
	 */

	public void tireBoule(Joueur unJoueur, Collection <Mur> lesMurs) {
	this.lesMurs = lesMurs;
	this.attaquant = unJoueur;

	//positionnement de la boule
	if (attaquant.getOrientation() == DROITE) {
		posX = attaquant.getPosX()+LARGEURPERSO+1;
	}else {
		posX = attaquant.getPosX()-LARGEUR_BOULE-1;
	}
	posY = attaquant.getPosY() + HAUTEURPERSO/2 ;
	
	new Thread(this).start();
	}
	
	@Override 
	public void run() {
		// envoi du son de la boule
		this.jeuServeur.envoi(FIGHT);
		this.attaquant.affiche(MARCHE, 1);
		this.jLabel.setVisible(true);
		Joueur victime = null;
		int lePas; //définir le sens du pas selonl'orientation du personnage
		if (this.attaquant.getOrientation() == DROITE) {
			lePas = PAS;
		}else {
			lePas = -PAS;
		}
		// envoi boule
		do {
			posX += lePas;
			this.jLabel.setBounds(posX, posY, LARGEUR_BOULE, HAUTEUR_BOULE);
			this.jeuServeur.envoiJeuATous();
			Collection lesJoueurs = this.jeuServeur.getlesJoueur();
			victime = (Joueur)super.toucheCollectionObjets(lesJoueurs);
		} while(posX >= 0 && posX + LARGEUR_BOULE <= LARGEUR_ARENE 
				&& victime == null
				&& this.toucheCollectionObjets(lesMurs) == null) ;
		// verifier si il y a une victime
		if (victime != null) {
			// envoie l'information du son à jouer au jeu serveur
			this.jeuServeur.envoi(HURT);
			victime.perteVie();
			attaquant.gainVie();
			//boucle pour animation du joueur blessé
			for (int k = 1;  k <= NBETAPESTOUCHE; k++) {
				this.pause(80, 0);
				victime.affiche(TOUCHE, k);
			}
			if (victime.estMort()) {
				// envoie l'information du son à jouer au jeu serveur
				this.jeuServeur.envoi(DEATH);
				//boucle pour animation du joueur qui meurt
				for (int k = 1;  k <= NBETAPESMORT; k++) {
					this.pause(80, 0);
					victime.affiche(MORT, k);
					
					
				}
			}else {
				victime.affiche(MARCHE, 1);	
			}
		}	
	this.jLabel.setVisible(false);
	this.jeuServeur.envoiJeuATous();
	}

	public void pause( long millis, int nanos) {
		try {
			Thread.sleep(millis, nanos);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}























