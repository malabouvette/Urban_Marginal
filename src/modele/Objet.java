package modele;

import javax.swing.JLabel;

import controleur.Global;

/**
 * Informations communes à tous les objets (joueurs, murs, boules)
 * permet de mémoriser la position de l'objet et de gérer les  collisions
 *
 */
public abstract class Objet implements Global {

	/**
	 * position X de l'objet
	 */
	protected Integer posX ;
	/**
	 * position Y de l'objet
	 */
	protected Integer posY ;
	/**
	 * Jlabel
	 */
	protected JLabel jLabel ;
	/**
	 * largeur objet
	 */
	protected Integer largeur;
	/**
	 * hauteur objet
	 */
	protected Integer hauteur;
	
	 /** 
	  * position Y de la fin de l'objet
	 */
	// protected Integer posYFin ;
	/**
	 * position X de la fin de l'objet
	 */
	//protected Integer posXFin ;
	/**
	 * @return jLabel
	 */
	
	public JLabel getjLabel() {
		return jLabel;	
	}

	
	
	/**
	 * contrôle si l'objet actuel touche l'objet passé en paramètre
	 * @param objet contient l'objet à contrôler
	 * @return true si les 2 objets se touchent
	 */
	public Boolean toucheObjet (Objet objet) {
		if (objet.jLabel==null || objet.jLabel==null) {
			return false ;
		}else{
			return(this.posX+this.jLabel.getWidth()>objet.posX &&
				this.posX<objet.posX+objet.jLabel.getWidth() && 
				this.posY+this.jLabel.getHeight()>objet.posY &&
				this.posY<objet.posY+objet.jLabel.getHeight()) ;
		}	
	}	
}
