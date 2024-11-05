package um.tds.appChat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JPasswordField;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;


public class Registro {

	private JFrame frame;
	private Inicio ventanaLogin;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
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
		frame.setVisible(true);
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 987, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Registro - AppChat");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{177, 300, 98, 188, 43, 0};
		gridBagLayout.rowHeights = new int[]{15, 40, 40, 40,40, 40, 150, 40, 20};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblApellidos = new JLabel("Nombre: ");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.insets = new Insets(15, 0, 5, 5);
		gbc_lblApellidos.gridx = 0;
		gbc_lblApellidos.gridy = 1;
		frame.getContentPane().add(lblApellidos, gbc_lblApellidos);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.gridwidth = 3;
		gbc_textField_4.insets = new Insets(15, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 1;
		frame.getContentPane().add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNombre = new JLabel("Apellidos: ");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 2;
		frame.getContentPane().add(lblNombre, gbc_lblNombre);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 3;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 2;
		frame.getContentPane().add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblMvil = new JLabel("Móvil: ");
		GridBagConstraints gbc_lblMvil = new GridBagConstraints();
		gbc_lblMvil.insets = new Insets(0, 0, 5, 5);
		gbc_lblMvil.gridx = 0;
		gbc_lblMvil.gridy = 3;
		frame.getContentPane().add(lblMvil, gbc_lblMvil);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 3;
		frame.getContentPane().add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
		gbc_lblContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrasea.gridx = 0;
		gbc_lblContrasea.gridy = 4;
		frame.getContentPane().add(lblContrasea, gbc_lblContrasea);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 4;
		frame.getContentPane().add(passwordField, gbc_passwordField);
		
		JLabel lblRepite = new JLabel("Repite: ");
		GridBagConstraints gbc_lblRepite = new GridBagConstraints();
		gbc_lblRepite.insets = new Insets(0, 0, 5, 5);
		gbc_lblRepite.gridx = 2;
		gbc_lblRepite.gridy = 4;
		frame.getContentPane().add(lblRepite, gbc_lblRepite);
		
		passwordField_1 = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 3;
		gbc_passwordField_1.gridy = 4;
		frame.getContentPane().add(passwordField_1, gbc_passwordField_1);
		
		JLabel lblFecha = new JLabel("Fecha de nacimiento:");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 0;
		gbc_lblFecha.gridy = 5;
		frame.getContentPane().add(lblFecha, gbc_lblFecha);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("d/MM/YYYY");
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.gridx = 1;
		gbc_dateChooser.gridy = 5;
		frame.getContentPane().add(dateChooser, gbc_dateChooser);
		
		JLabel lblSubirFoto = new JLabel("Inserte una imagen ");
		GridBagConstraints gbc_lblSubirFoto = new GridBagConstraints();
		gbc_lblSubirFoto.insets = new Insets(0, 0, 5, 5);
		gbc_lblSubirFoto.gridx = 3;
		gbc_lblSubirFoto.gridy = 5;
		frame.getContentPane().add(lblSubirFoto, gbc_lblSubirFoto);
		
		JLabel lblSaludo = new JLabel("Saludo:");
		GridBagConstraints gbc_lblSaludo = new GridBagConstraints();
		gbc_lblSaludo.insets = new Insets(0, 0, 5, 5);
		gbc_lblSaludo.gridx = 0;
		gbc_lblSaludo.gridy = 6;
		frame.getContentPane().add(lblSaludo, gbc_lblSaludo);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);	
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.ipady = 40;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.HORIZONTAL;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 6;
		frame.getContentPane().add(textArea, gbc_textArea);
		
		JLabel lblFoto = new JLabel("Foto:");
		GridBagConstraints gbc_lblFoto = new GridBagConstraints();
		gbc_lblFoto.insets = new Insets(0, 0, 5, 5);
		gbc_lblFoto.gridx = 2;
		gbc_lblFoto.gridy = 6;
		frame.getContentPane().add(lblFoto, gbc_lblFoto);
		
		JLabel foto=new JLabel();
		JButton btnSubirFoto = new JButton("Subir foto");
		btnSubirFoto.addActionListener(e-> {
			JFileChooser chooser = new JFileChooser();
			if (chooser.showOpenDialog(frame)==JFileChooser.APPROVE_OPTION) {
				File currentFile = chooser.getSelectedFile();
				ImageIcon icon = new ImageIcon(currentFile.getPath()); 
				Image fotoEscalada = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
				foto.setIcon(new ImageIcon(fotoEscalada));
				GridBagConstraints gbc_lblFotoSubida = new GridBagConstraints();
				gbc_lblFotoSubida.insets = new Insets(0, 0, 5, 5);
				gbc_lblFotoSubida.gridx = 3;
				gbc_lblFotoSubida.gridy = 5;
				frame.getContentPane().add(foto, gbc_lblFotoSubida);
				frame.getContentPane().remove(lblSubirFoto);
				frame.revalidate();
				frame.repaint();	
			}
			
		});
		GridBagConstraints gbc_btnSubirFoto = new GridBagConstraints();
		gbc_btnSubirFoto.insets = new Insets(0, 0, 5, 5);
		gbc_btnSubirFoto.gridx = 3;
		gbc_btnSubirFoto.gridy = 6;
		frame.getContentPane().add(btnSubirFoto, gbc_btnSubirFoto);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnRegistrar.gridx = 1;
		gbc_btnRegistrar.gridy = 7;
		frame.getContentPane().add(btnRegistrar, gbc_btnRegistrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener( e -> {
		    frame.setVisible(false);
		    frame.dispose();
		    ventanaLogin.mostrar();
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 7;
		frame.getContentPane().add(btnCancelar, gbc_btnCancelar);
	}

}

