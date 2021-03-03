package modele;
import controleur.Global;

import java.net.URL;
import java.util.Random ;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * Gestion des murs
 *
 */
public class Mur extends Objet implements Global {
	
	private int largeur = LARGEUR_MUR ;
	private int hauteur = HAUTEUR_MUR ;
	

	/**
	 * Constructeur
	 */
	public Mur() {
	posX = (new Random()).nextInt((LARGEUR_ARENE - LARGEUR_MUR) - 1);
	posY = (new Random()).nextInt((HAUTEUR_ARENE - HAUTEUR_MUR) - 1);
	
	super.jLabel = new JLabel();
	URL resource = getClass().getClassLoader().getResource(MUR);
	super.jLabel.setIcon(new ImageIcon(resource));		
	super.jLabel.setBounds(posX, posY, LARGEUR_MUR, HAUTEUR_MUR);
	
	}

	
	

}
