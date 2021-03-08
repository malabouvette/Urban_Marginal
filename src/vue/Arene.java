package vue;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controle;
import controleur.Global;
import modele.Objet;
import outils.son.Son;

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
	 * instance du controleur pour communiquer avec lui
	 */
	private Controle controle;
	/**
	 * bolléen pour savoir si il s'agit d'un arène serveur ou client
	 */
	private Boolean client;
	/**
	 * 
	 */
	private Son[] lesSons = new Son[TABSON.length];
	
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
	public void setJpnMurs(JPanel jpnMurs) {
		this.jpnMurs.add(jpnMurs);
		this.jpnMurs.repaint();
	}
		
	/**
	 * getter panel Jeu
	 *@return
	 */
	public JPanel getJpnJeu() {
		return jpnJeu ;
	}
	
	/**
	 *setter panel jeu
	 *@param jpnMurs
	 */
	public void setJpnJeu(JPanel jpnJeu) {
		this.jpnJeu.removeAll();
		this.jpnJeu.add(jpnJeu);
		this.jpnJeu.repaint();
		this.contentPane.requestFocus();
	}
	/**
	 * getter le contenu du texte du chat
	 */
	public String getTxtChat() {
		return txtChat.getText();
	}
	/**
	 * @param txtChat the txtChat to set
	 */
	public void setTxtChat(String txtChat) {
		this.txtChat.setText(txtChat);
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength());
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
		this.jpnJeu.add(lblJeu);
		this.jpnJeu.repaint();
	}
	 /**
	  * joue le son du combat selon appel du controleur
	  */
	public void joueSon(Integer numSon) {
		this.lesSons[numSon].play();
	}
	/**
	 * ajout de la phrase du chat
	 */
	public void ajoutTchat(String phrase) {
		this.txtChat.append(phrase+"\r\n");
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength());
	}
	
	/**
	 * évènement lors de l'appuie de la touche entrée lors de la rédaction d'un message dans chat
	 */
	public void txtSaisie_KeyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!this.txtSaisie.getText().equals("")){
				this.controle.evenementArene(this.txtSaisie.getText());
				this.txtSaisie.setText("");
			}
			this.contentPane.requestFocus();
		}
	}
	/**
	 * Evénement touche pressée sur le panel général
	 * @param e informations sur la touche
	 */
	public void contentPane_KeyPressed(KeyEvent e) {
		int touche = -1;
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT :
		case KeyEvent.VK_RIGHT :
		case KeyEvent.VK_UP :
		case KeyEvent.VK_DOWN :
		case KeyEvent.VK_SPACE :
			touche = e.getKeyCode();
			break;
		}
		// si touche correcte, alors envoi de sa valeur
		if(touche != -1) {
			this.controle.evenementArene(touche);
		}
	}
	
	/**
	 * Create the frame.
	 * @param controle instance du controleur
	 */
	public Arene(Controle controle, String typeJeu) {
		this.client = typeJeu.equals(CLIENT);
	
		// Dimension de la frame en fonction de son contenu
		this.getContentPane().setPreferredSize(new Dimension(800, 600 + 25 + 140));
	    this.pack();
	    // interdiction de changer la taille
		this.setResizable(false);
		
		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		//écoute de touche sur le panel du jeu
		contentPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				contentPane_KeyPressed(e);
			}
		});
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jpnJeu = new JPanel();
		jpnJeu.setBounds(POSX_PAVES,POSY_PAVES,LARGEUR_ARENE, HAUTEUR_ARENE);
		jpnJeu.setLayout(null);
		contentPane.add(jpnJeu);
		jpnJeu.setOpaque(false);
		
		
		jpnMurs = new JPanel();
		jpnMurs.setBounds(POSX_PAVES,POSY_PAVES, LARGEUR_ARENE, HAUTEUR_ARENE);
		jpnMurs.setLayout(null);
		contentPane.add(jpnMurs);
		jpnMurs.setOpaque(false);
		
		if (client) {
			txtSaisie = new JTextField();
			txtSaisie = new JTextField();
			txtSaisie.addKeyListener(new KeyAdapter(){
				@Override
				public void keyPressed(KeyEvent e) {
					txtSaisie_KeyPressed(e);
				}
			});
			txtSaisie.setBounds(0, 600, 800, 25);
			contentPane.add(txtSaisie);
			txtSaisie.setColumns(10);
			
		}
		
		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setBounds(0, 625, 800, 140);
		contentPane.add(jspChat);
		
		txtChat = new JTextArea();
		txtChat.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				contentPane_KeyPressed(e);
			}		
		});
		jspChat.setViewportView(txtChat);
		txtChat.setEditable(false);
		
		JLabel lblFond = new JLabel("");
		URL resource = getClass().getClassLoader().getResource(FONDARENE);
		lblFond.setIcon(new ImageIcon(resource));		
		lblFond.setBounds(0, 0, 800, 600);
		contentPane.add(lblFond);
		
		// gestion du son des combat
		if (client) {
			for (int k=0 ; k<TABSON.length ; k++) {
				lesSons[k] = new Son(getClass().getClassLoader().getResource(TABSON[k])) ;
			}
		}
		
		// récupération de l'instance de Controle
		this.controle = controle;
		
	}

}
