package um.tds.appChat.ventanas;

import java.awt.EventQueue;

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JPasswordField;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;


public class Registro {

	private JDialog dialog;
	private Inicio ventanaLogin;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField campoMovil;
	private JTextField campoApellidos;
	private JTextField campoNombre;
	private String path;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro window = new Registro();
					window.dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Registro(Inicio ventanaLogin) {
		this.ventanaLogin=ventanaLogin;
		initialize();
	}
	public Registro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @return 
	 */
	public void mostrar() {
		dialog.setVisible(true);
	}
	private void initialize() {
		dialog = new JDialog();
		dialog.setBounds(100, 100, 987, 550);
		dialog.setTitle("Registro - AppChat");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{177, 300, 98, 188, 43, 0};
		gridBagLayout.rowHeights = new int[]{15, 40, 40, 40,40, 40, 150, 40, 20};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		dialog.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(15, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 1;
		dialog.getContentPane().add(lblNombre, gbc_lblNombre);
		
		campoNombre = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.gridwidth = 3;
		gbc_textField_4.insets = new Insets(15, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 1;
		dialog.getContentPane().add(campoNombre, gbc_textField_4);
		campoNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos: ");
		GridBagConstraints gbc_lblNo = new GridBagConstraints();
		gbc_lblNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNo.gridx = 0;
		gbc_lblNo.gridy = 2;
		dialog.getContentPane().add(lblApellidos, gbc_lblNo);
		
		campoApellidos = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 3;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 2;
		dialog.getContentPane().add(campoApellidos, gbc_textField_3);
		campoApellidos.setColumns(10);
		
		JLabel lblMvil = new JLabel("Móvil: ");
		GridBagConstraints gbc_lblMvil = new GridBagConstraints();
		gbc_lblMvil.insets = new Insets(0, 0, 5, 5);
		gbc_lblMvil.gridx = 0;
		gbc_lblMvil.gridy = 3;
		dialog.getContentPane().add(lblMvil, gbc_lblMvil);
		
		campoMovil = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 3;
		dialog.getContentPane().add(campoMovil, gbc_textField_2);
		campoMovil.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
		gbc_lblContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrasea.gridx = 0;
		gbc_lblContrasea.gridy = 4;
		dialog.getContentPane().add(lblContrasea, gbc_lblContrasea);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 4;
		dialog.getContentPane().add(passwordField, gbc_passwordField);
		
		JLabel lblRepite = new JLabel("Repite: ");
		GridBagConstraints gbc_lblRepite = new GridBagConstraints();
		gbc_lblRepite.insets = new Insets(0, 0, 5, 5);
		gbc_lblRepite.gridx = 2;
		gbc_lblRepite.gridy = 4;
		dialog.getContentPane().add(lblRepite, gbc_lblRepite);
		
		passwordField_1 = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 3;
		gbc_passwordField_1.gridy = 4;
		dialog.getContentPane().add(passwordField_1, gbc_passwordField_1);
		
		JLabel lblFecha = new JLabel("Fecha de nacimiento:");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 0;
		gbc_lblFecha.gridy = 5;
		dialog.getContentPane().add(lblFecha, gbc_lblFecha);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.gridx = 1;
		gbc_dateChooser.gridy = 5;
		dialog.getContentPane().add(dateChooser, gbc_dateChooser);
		
		JLabel lblSubirFoto = new JLabel("Inserte una imagen ");
		GridBagConstraints gbc_lblSubirFoto = new GridBagConstraints();
		gbc_lblSubirFoto.insets = new Insets(0, 0, 5, 5);
		gbc_lblSubirFoto.gridx = 3;
		gbc_lblSubirFoto.gridy = 5;
		dialog.getContentPane().add(lblSubirFoto, gbc_lblSubirFoto);
		
		JLabel lblSaludo = new JLabel("Saludo:");
		GridBagConstraints gbc_lblSaludo = new GridBagConstraints();
		gbc_lblSaludo.insets = new Insets(0, 0, 5, 5);
		gbc_lblSaludo.gridx = 0;
		gbc_lblSaludo.gridy = 6;
		dialog.getContentPane().add(lblSaludo, gbc_lblSaludo);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);	
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.ipady = 40;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.HORIZONTAL;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 6;
		dialog.getContentPane().add(textArea, gbc_textArea);
		
		JLabel lblFoto = new JLabel("Foto:");
		GridBagConstraints gbc_lblFoto = new GridBagConstraints();
		gbc_lblFoto.insets = new Insets(0, 0, 5, 5);
		gbc_lblFoto.gridx = 2;
		gbc_lblFoto.gridy = 6;
		dialog.getContentPane().add(lblFoto, gbc_lblFoto);
		
		JLabel foto=new JLabel();
		JButton btnSubirFoto = new JButton("Subir foto");
		btnSubirFoto.addActionListener(e-> {
			JFileChooser chooser = new JFileChooser();
			if (chooser.showOpenDialog(dialog)==JFileChooser.APPROVE_OPTION) {
				File currentFile = chooser.getSelectedFile();
				ImageIcon icon = new ImageIcon(currentFile.getPath()); 
				path=currentFile.getAbsolutePath();
				Image fotoEscalada = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
				foto.setIcon(new ImageIcon(fotoEscalada));
				GridBagConstraints gbc_lblFotoSubida = new GridBagConstraints();
				gbc_lblFotoSubida.insets = new Insets(0, 0, 5, 5);
				gbc_lblFotoSubida.gridx = 3;
				gbc_lblFotoSubida.gridy = 5;
				dialog.getContentPane().add(foto, gbc_lblFotoSubida);
				dialog.getContentPane().remove(lblSubirFoto);
				dialog.revalidate();
				dialog.repaint();	
			}
		});
		GridBagConstraints gbc_btnSubirFoto = new GridBagConstraints();
		gbc_btnSubirFoto.insets = new Insets(0, 0, 5, 5);
		gbc_btnSubirFoto.gridx = 3;
		gbc_btnSubirFoto.gridy = 6;
		dialog.getContentPane().add(btnSubirFoto, gbc_btnSubirFoto);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(e -> {
			if (!passwordCheck(passwordField.getPassword(), passwordField_1.getPassword())) {
                System.err.println("Las contraseñas no coinciden");
                }
			else {
				System.out.println("Nombre: "+campoNombre.getText());
				System.out.println("Apellidos: "+campoApellidos.getText());
				System.out.println("Movil: "+campoMovil.getText());
				System.out.print("Contraseña: ");
				System.out.println(passwordField.getPassword());
				System.out.println("Fecha: "+dateChooser.getDate().toString());
				System.out.println("Saludo: "+textArea.getText());
				System.out.println("Foto: "+path);
			}
		});
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnRegistrar.gridx = 1;
		gbc_btnRegistrar.gridy = 7;
		dialog.getContentPane().add(btnRegistrar, gbc_btnRegistrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener( e -> {
		    dialog.setVisible(false);
		    dialog.dispose();
		    ventanaLogin.mostrar();
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 7;
		dialog.getContentPane().add(btnCancelar, gbc_btnCancelar);
	}

	public boolean passwordCheck(char[] password, char[] password2) {
		if (password.length != password2.length) {
			return false;
		}
		for (int i = 0; i < password.length; i++) {
			if (password[i] != password2[i]) {
				return false;
			}
		}
		return true;
		
	}

}

