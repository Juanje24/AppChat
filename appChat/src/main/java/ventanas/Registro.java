package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JPasswordField;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Registro {

	private JFrame frame;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro window = new Registro();
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
	public Registro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 892, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{281, 300, 98, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblApellidos.insets = new Insets(15, 0, 5, 5);
		gbc_lblApellidos.gridx = 0;
		gbc_lblApellidos.gridy = 0;
		frame.getContentPane().add(lblApellidos, gbc_lblApellidos);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.gridwidth = 3;
		gbc_textField_4.insets = new Insets(15, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 0;
		frame.getContentPane().add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 1;
		frame.getContentPane().add(lblNombre, gbc_lblNombre);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 1;
		frame.getContentPane().add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblMvil = new JLabel("Móvil");
		GridBagConstraints gbc_lblMvil = new GridBagConstraints();
		gbc_lblMvil.anchor = GridBagConstraints.EAST;
		gbc_lblMvil.insets = new Insets(0, 0, 5, 5);
		gbc_lblMvil.gridx = 0;
		gbc_lblMvil.gridy = 2;
		frame.getContentPane().add(lblMvil, gbc_lblMvil);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		frame.getContentPane().add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
		gbc_lblContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrasea.anchor = GridBagConstraints.EAST;
		gbc_lblContrasea.gridx = 0;
		gbc_lblContrasea.gridy = 3;
		frame.getContentPane().add(lblContrasea, gbc_lblContrasea);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 3;
		frame.getContentPane().add(passwordField, gbc_passwordField);
		
		JLabel lblRepite = new JLabel("Repite");
		GridBagConstraints gbc_lblRepite = new GridBagConstraints();
		gbc_lblRepite.insets = new Insets(0, 0, 5, 5);
		gbc_lblRepite.gridx = 2;
		gbc_lblRepite.gridy = 3;
		frame.getContentPane().add(lblRepite, gbc_lblRepite);
		
		passwordField_1 = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 3;
		gbc_passwordField_1.gridy = 3;
		frame.getContentPane().add(passwordField_1, gbc_passwordField_1);
		
		JLabel lblFecha = new JLabel("Fecha");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.anchor = GridBagConstraints.EAST;
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 0;
		gbc_lblFecha.gridy = 4;
		frame.getContentPane().add(lblFecha, gbc_lblFecha);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 4;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblSaludo = new JLabel("Saludo");
		GridBagConstraints gbc_lblSaludo = new GridBagConstraints();
		gbc_lblSaludo.anchor = GridBagConstraints.EAST;
		gbc_lblSaludo.insets = new Insets(0, 0, 5, 5);
		gbc_lblSaludo.gridx = 0;
		gbc_lblSaludo.gridy = 5;
		frame.getContentPane().add(lblSaludo, gbc_lblSaludo);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.ipady = 20;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 5;
		frame.getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblFoto = new JLabel("Foto");
		GridBagConstraints gbc_lblFoto = new GridBagConstraints();
		gbc_lblFoto.insets = new Insets(0, 0, 5, 5);
		gbc_lblFoto.gridx = 2;
		gbc_lblFoto.gridy = 5;
		frame.getContentPane().add(lblFoto, gbc_lblFoto);
		
		JButton btnSubirFoto = new JButton("Subir foto");
		GridBagConstraints gbc_btnSubirFoto = new GridBagConstraints();
		gbc_btnSubirFoto.insets = new Insets(0, 0, 5, 5);
		gbc_btnSubirFoto.gridx = 3;
		gbc_btnSubirFoto.gridy = 5;
		frame.getContentPane().add(btnSubirFoto, gbc_btnSubirFoto);
		
		JButton btnRegistrar = new JButton("Registrar");
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnRegistrar.gridx = 1;
		gbc_btnRegistrar.gridy = 6;
		frame.getContentPane().add(btnRegistrar, gbc_btnRegistrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 6;
		frame.getContentPane().add(btnCancelar, gbc_btnCancelar);
	}

}