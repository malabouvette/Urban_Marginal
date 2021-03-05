package modele;

import java.util.Collection;

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
	 * @return jLabel
	 */
	
	public JLabel getjLabel() {
		return jLabel;	
	}
	/**
	 * @return posX
	 */
	public int getPosX() {
		return this.posX ;	
	}
	/**
	 * @return posX
	 */
	public int getPosY() {
		return this.posY ;	
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
	
	/**
	 * Vérifie si l'objet actuel touche un des objets de la collection
	 * @param lesObjets collection d'objets (murs, joueurs ou boules)
	 * @return l'objet touché ou null
	 */
	public Objet toucheCollectionObjets (Collection <Objet> lesObjets) {
		for (Objet unObjet : lesObjets) {
			if (!unObjet.equals(this)) {
				if (toucheObjet(unObjet)) {
					return unObjet ;
				}
			}
		}
		return null ;
	}
}
