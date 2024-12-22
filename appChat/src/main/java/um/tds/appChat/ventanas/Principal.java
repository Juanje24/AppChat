package um.tds.appChat.ventanas;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import um.tds.appChat.dominio.Contacto;
import um.tds.appChat.dominio.ContactoIndividual;
import um.tds.appChat.dominio.Usuario;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class Principal extends JFrame {

	private JFrame frame;
	private PanelContactos panelContactos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}
	public void mostrar() {
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		frame.getContentPane().add(panelNorte, BorderLayout.NORTH);
		
		
		panelContactos = new PanelContactos();
		frame.getContentPane().add(panelContactos, BorderLayout.WEST);
		
		
		JPanel panelCentro = new JPanel();
		frame.getContentPane().add(panelCentro, BorderLayout.CENTER);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.addItem("Prueba");
		comboBox.addItem("Prueba2");
		panelCentro.add(comboBox);
		
		JButton btnChatear = new JButton("Chatear");
		panelCentro.add(btnChatear);
		
	}
}
