package vue;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Global;
import modele.Objet;

/**
 * frame de l'arène du jeu
 * @author emds
 *
 */
public class Arene extends JFrame implements Global {

	/**
	 * Panel général
	 */
	private JPanel contentPane;
	/**
	 * Zone de saisie du t'chat
	 */
	private JTextField txtSaisie;
	/**
	 * Zone d'affichage du t'chat
	 */
	private JTextArea txtChat ;
	/**
	 * Zone d'affichage des murs
	 */
	private JPanel jpnMurs ;
	
	/**
	 * Zone d'affichage des personnages
	 */
	private JPanel jpnJeu ;
	/**
	 * getter panel mur
	 *@return
	 */
	public JPanel getJpnMurs() {
		return jpnMurs ;
	}
	
	/**
	 *setter panel mur
	 *@param jpnMurs
	 */
	public void setJpnMurs(JPanel info) {
		this.jpnMurs.add(info);
		info.repaint();
	}
		
	/**
	 * getter panel Jeu
	 *@return
	 */
	public JPanel getJpnJeu() {
		return jpnJeu ;
	}
	
	/**
	 *setter panel mur
	 *@param jpnMurs
	 */
	public void setJpnJeu(JPanel info) {
		this.jpnJeu.removeAll();
		this.jpnJeu.add(info);
		info.repaint();
	}

	/**
	 * Ajout Murs
	 */
	public void ajoutMurs (Object unMur) {
		jpnMurs.add((JLabel)unMur);
		jpnMurs.repaint();
	}
	
	/**
	 * Ajout Label Jeu
	 */
	public void ajoutLabelJeu (JLabel lblJeu) {
		jpnJeu.add(lblJeu);
		jpnJeu.repaint();
	}
	
	/**
	 * Create the frame.
	 */
	public Arene() {
		// Dimension de la frame en fonction de son contenu
		this.getContentPane().setPreferredSize(new Dimension(800, 600 + 25 + 140));
	    this.pack();
	    // interdiction de changer la taille
		this.setResizable(false);
		
		
		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jpnJeu = new JPanel();
		jpnJeu.setBounds(POSX_PAVES, POSY_PAVES, LARGEUR_ARENE, HAUTEUR_ARENE);
		jpnJeu.setLayout(null);
		contentPane.add(jpnJeu);
		jpnJeu.setOpaque(false);
		
		
		jpnMurs = new JPanel();
		jpnMurs.setBounds(POSX_PAVES,POSY_PAVES, LARGEUR_ARENE, HAUTEUR_ARENE);
		jpnMurs.setLayout(null);
		contentPane.add(jpnMurs);
		jpnMurs.setOpaque(false);
		
	
		txtSaisie = new JTextField();
		txtSaisie.setBounds(0, 600, 800, 25);
		contentPane.add(txtSaisie);
		txtSaisie.setColumns(10);
		
		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setBounds(0, 625, 800, 140);
		contentPane.add(jspChat);
		
		txtChat = new JTextArea();
		jspChat.setViewportView(txtChat);
		
		JLabel lblFond = new JLabel("");
		URL resource = getClass().getClassLoader().getResource(FONDARENE);
		lblFond.setIcon(new ImageIcon(resource));		
		lblFond.setBounds(0, 0, 800, 600);
		contentPane.add(lblFond);
		
	}

}
