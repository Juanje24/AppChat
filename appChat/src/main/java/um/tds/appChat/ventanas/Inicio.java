package um.tds.appChat.ventanas;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import um.tds.appChat.singletons.AppChat;
import um.tds.appChat.utils.RoundButtonUI;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Inicio extends JFrame{

	private static final long serialVersionUID = 1L;
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private Panel panel_2;
	private Panel panel_3;
	private JLabel lblTelfono;
	private JLabel lblContrasea;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPanel panelNorte;


	/**
	 * Create the application.
	 */
	public Inicio() {
		super();
		this.setTitle("Login - AppChat");
		this.setBounds(100, 100, 768, 612);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @return 
	 */

	private void initialize() {
		
		this.setTitle("Login - AppChat");
		this.setBounds(100, 100, 768, 612);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		
		panelNorte = new JPanel();
		this.getContentPane().add(panelNorte, BorderLayout.NORTH);
		ImageIcon icon = new ImageIcon(getClass().getResource("/iconos/logo.png")); 
		Image iconoEscalado = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
	    JLabel lblImage = new JLabel(new ImageIcon(iconoEscalado));
	    panelNorte.add(lblImage);
	    lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblAppchat = new JLabel("AppChat");
		panelNorte.add(lblAppchat);
		lblAppchat.setHorizontalAlignment(SwingConstants.CENTER);
		lblAppchat.setFont(new Font("Dialog", Font.BOLD, 34));
		
		JPanel panelCentro = new JPanel();
		panelCentro.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Login", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		this.getContentPane().add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
		
		panel_2 = new Panel();
		panelCentro.add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{20, 100, 0, 50, 0};
		gbl_panel_2.rowHeights = new int[]{50, 50, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		lblTelfono = new JLabel("Teléfono: ");
		GridBagConstraints gbc_lblTelfono = new GridBagConstraints();
		gbc_lblTelfono.insets = new Insets(0, 0, 0, 5);
		gbc_lblTelfono.gridx = 1;
		gbc_lblTelfono.gridy = 1;
		panel_2.add(lblTelfono, gbc_lblTelfono);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panel_2.add(textField, gbc_textField);
		textField.setColumns(10);
		
		panel_3 = new Panel();
		panelCentro.add(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{20, 100, 0, 50, 0};
		gbl_panel_3.rowHeights = new int[]{0, 50, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		lblContrasea = new JLabel("Contraseña: ");
		GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
		gbc_lblContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrasea.gridx = 1;
		gbc_lblContrasea.gridy = 1;
		panel_3.add(lblContrasea, gbc_lblContrasea);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 1;
		panel_3.add(passwordField, gbc_passwordField);
		
		JPanel panelSur = new JPanel();
		this.getContentPane().add(panelSur, BorderLayout.SOUTH);
		FlowLayout fl_panelSur = new FlowLayout(FlowLayout.CENTER, 5, 5);
		panelSur.setLayout(fl_panelSur);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setUI(new RoundButtonUI());
		btnRegistrar.addActionListener(e -> {
			Registro ventanaRegistro = new Registro(this);
			this.setVisible(false);
			ventanaRegistro.mostrar();
			
		});
		panelSur.add(btnRegistrar);
		panelSur.add(Box.createRigidArea(new Dimension(100,10)));
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setUI(new RoundButtonUI(SystemColor.textHighlight,new Color(255,100,100)));
		btnCancelar.addActionListener( e -> {
		    this.setVisible(false);
		    this.dispose();
		});
		panelSur.add(btnCancelar);
		
		btnAceptar = new JButton("Iniciar sesión");
		btnAceptar.setUI(new RoundButtonUI());
		panelSur.add(btnAceptar);
		btnAceptar.addActionListener(e -> iniciarSesion());
		passwordField.addActionListener(e -> iniciarSesion());
	}
	private void iniciarSesion() {
	    if (AppChat.INSTANCE.login(textField.getText(), new String(passwordField.getPassword()))) {
	        new Principal(this).mostrar();
	        this.setVisible(false);
	        this.dispose();
	    } else {
	        JOptionPane.showMessageDialog(this, "Teléfono o contraseña incorrectos", "Warning", JOptionPane.WARNING_MESSAGE);
	    }
	}

}
