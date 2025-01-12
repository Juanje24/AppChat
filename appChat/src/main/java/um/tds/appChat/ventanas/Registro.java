package um.tds.appChat.ventanas;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JPasswordField;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.toedter.calendar.JDateChooser;

import um.tds.appChat.dominio.Usuario;
import um.tds.appChat.singletons.AppChat;
import um.tds.appChat.utils.RoundButtonUI;
import um.tds.appChat.utils.Utils;

import java.io.File;
import java.time.ZoneId;
import java.util.List;
import javax.swing.JTextArea;


public class Registro extends JDialog {

	private static final long serialVersionUID = 1L;
	private JFrame ventanaPadre;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField campoMovil;
	private JTextField campoApellidos;
	private JTextField campoNombre;
	private String path = "";
	private Usuario u=null;

	/**
	 * Create the application.
	 */
	public Registro(JFrame padre, Usuario u) {
		super(padre, "Modifica tu usuario", true);
		this.ventanaPadre=padre;
		this.setBounds(100, 100, 987, 550);
		this.setLocationRelativeTo(padre);
		this.u=u;
		initialize();
		
	}

	public Registro(JFrame padre) {
		super(padre, "Registro - AppChat", true);
		this.ventanaPadre=padre;
		this.setBounds(100, 100, 987, 550);
		this.setLocationRelativeTo(padre);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @return 
	 */
	public void mostrar() {
		this.setVisible(true);
	}
	private void initialize() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{177, 300, 98, 188, 43, 0};
		gridBagLayout.rowHeights = new int[]{15, 40, 40, 40,40, 40, 150, 40, 0, 20};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(15, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 1;
		this.getContentPane().add(lblNombre, gbc_lblNombre);
		
		campoNombre = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.gridwidth = 3;
		gbc_textField_4.insets = new Insets(15, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 1;
		this.getContentPane().add(campoNombre, gbc_textField_4);
		campoNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos: ");
		GridBagConstraints gbc_lblNo = new GridBagConstraints();
		gbc_lblNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNo.gridx = 0;
		gbc_lblNo.gridy = 2;
		this.getContentPane().add(lblApellidos, gbc_lblNo);
		
		campoApellidos = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 3;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 2;
		this.getContentPane().add(campoApellidos, gbc_textField_3);
		campoApellidos.setColumns(10);
		
		JLabel lblMvil = new JLabel("Móvil: ");
		GridBagConstraints gbc_lblMvil = new GridBagConstraints();
		gbc_lblMvil.insets = new Insets(0, 0, 5, 5);
		gbc_lblMvil.gridx = 0;
		gbc_lblMvil.gridy = 3;
		this.getContentPane().add(lblMvil, gbc_lblMvil);
		
		campoMovil = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 3;
		this.getContentPane().add(campoMovil, gbc_textField_2);
		campoMovil.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
		gbc_lblContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrasea.gridx = 0;
		gbc_lblContrasea.gridy = 4;
		this.getContentPane().add(lblContrasea, gbc_lblContrasea);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 4;
		this.getContentPane().add(passwordField, gbc_passwordField);
		
		JLabel lblRepite = new JLabel("Repite: ");
		GridBagConstraints gbc_lblRepite = new GridBagConstraints();
		gbc_lblRepite.insets = new Insets(0, 0, 5, 5);
		gbc_lblRepite.gridx = 2;
		gbc_lblRepite.gridy = 4;
		this.getContentPane().add(lblRepite, gbc_lblRepite);
		
		passwordField_1 = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 3;
		gbc_passwordField_1.gridy = 4;
		this.getContentPane().add(passwordField_1, gbc_passwordField_1);
		
		JLabel lblFecha = new JLabel("Fecha de nacimiento:");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 0;
		gbc_lblFecha.gridy = 5;
		this.getContentPane().add(lblFecha, gbc_lblFecha);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.gridx = 1;
		gbc_dateChooser.gridy = 5;
		this.getContentPane().add(dateChooser, gbc_dateChooser);
		
		JLabel lblSubirFoto = new JLabel("Seleccione una imagen ");
		GridBagConstraints gbc_lblSubirFoto = new GridBagConstraints();
		gbc_lblSubirFoto.insets = new Insets(0, 0, 5, 5);
		gbc_lblSubirFoto.gridx = 3;
		gbc_lblSubirFoto.gridy = 5;
		this.getContentPane().add(lblSubirFoto, gbc_lblSubirFoto);
		
		JLabel lblSaludo = new JLabel("Saludo (opcional):");
		GridBagConstraints gbc_lblSaludo = new GridBagConstraints();
		gbc_lblSaludo.insets = new Insets(0, 0, 5, 5);
		gbc_lblSaludo.gridx = 0;
		gbc_lblSaludo.gridy = 6;
		this.getContentPane().add(lblSaludo, gbc_lblSaludo);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);	
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.ipady = 40;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.HORIZONTAL;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 6;
		this.getContentPane().add(textArea, gbc_textArea);
		
		JLabel lblFoto = new JLabel("Foto:");
		GridBagConstraints gbc_lblFoto = new GridBagConstraints();
		gbc_lblFoto.insets = new Insets(0, 0, 5, 5);
		gbc_lblFoto.gridx = 2;
		gbc_lblFoto.gridy = 6;
		this.getContentPane().add(lblFoto, gbc_lblFoto);
		
		
		JButton btnSubirFoto = new JButton("Subir foto");
		btnSubirFoto.setUI(new RoundButtonUI());
		btnSubirFoto.addActionListener(e-> {
				PanelArrastraImagen panelArrastraImagen = new PanelArrastraImagen(ventanaPadre);
				List<File> imagenes = panelArrastraImagen.showDialog();
				if (imagenes != null && !imagenes.isEmpty() && imagenes.get(0) != null) {
					path = Utils.getRutaResourceFromFile(imagenes.get(0));
					ImageIcon iconoImagen = new ImageIcon(getClass().getResource(path));
					Image imagenEscalada = iconoImagen.getImage().
					getScaledInstance(100, 100, Image.SCALE_SMOOTH);
					lblSubirFoto.setIcon(new ImageIcon(imagenEscalada));
					lblSubirFoto.setText("");
					panelArrastraImagen.setVisible(false);
					panelArrastraImagen.dispose();
				}
		});
		GridBagConstraints gbc_btnSubirFoto = new GridBagConstraints();
		gbc_btnSubirFoto.insets = new Insets(0, 0, 5, 5);
		gbc_btnSubirFoto.gridx = 3;
		gbc_btnSubirFoto.gridy = 6;
		this.getContentPane().add(btnSubirFoto, gbc_btnSubirFoto);
		if(u==null) {
			JButton btnRegistrar = new JButton("Registrar");
			btnRegistrar.setUI(new RoundButtonUI());
			btnRegistrar.addActionListener(e -> {
				if (!passwordCheck(passwordField.getPassword(), passwordField_1.getPassword())) {
					JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
	                }
				else if(campoNombre.getText().isEmpty() || campoApellidos.getText().isEmpty() || campoMovil.getText().isEmpty()
						|| passwordField.getPassword().length==0 || dateChooser.getDate()==null) {
					JOptionPane.showMessageDialog(this, "Por favor, rellene los campos obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					if (path.equals("")) {
			            path = Utils.getRutaResourceFromString("src/main/resources/iconos/userDefault.png");
					}
					if(AppChat.INSTANCE.registrarUsuario(campoNombre.getText(), campoApellidos.getText(), new String(passwordField.getPassword()),
							dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), campoMovil.getText(), path ,textArea.getText())) {
						JOptionPane.showMessageDialog(this, "Usuario registrado correctamente", "Registro", JOptionPane.INFORMATION_MESSAGE);
						this.setVisible(false);
					    this.dispose();
					    ventanaPadre.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(this, "El teléfono ya está registrado", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
			gbc_btnRegistrar.insets = new Insets(0, 0, 5, 5);
			gbc_btnRegistrar.gridx = 1;
			gbc_btnRegistrar.gridy = 7;
			this.getContentPane().add(btnRegistrar, gbc_btnRegistrar);
		}
		else {
			//Recuperamos los datos del usuario actual
			campoNombre.setText(u.getNombre());
			campoApellidos.setText(u.getApellido());
			campoMovil.setText(u.getTelefono());
			textArea.setText(u.getSaludo());
			dateChooser.setDate(java.sql.Date.valueOf(u.getBirthday()));
			path = u.getUrlImagen();
			ImageIcon iconoImagen = new ImageIcon(getClass().getResource(path));
			Image imagenEscalada = iconoImagen.getImage();
			Image imagenEscalada2 = imagenEscalada.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			lblSubirFoto.setIcon(new ImageIcon(imagenEscalada2));
			lblSubirFoto.setText("");
			
			
			JButton btnRegistrar = new JButton("Modificar");
			btnRegistrar.setUI(new RoundButtonUI());
			btnRegistrar.addActionListener(e -> {
				if (!passwordCheck(passwordField.getPassword(), passwordField_1.getPassword())) {
					JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
	                }
				else if(campoNombre.getText().isEmpty() || campoApellidos.getText().isEmpty() || campoMovil.getText().isEmpty()
						|| passwordField.getPassword().length==0 || dateChooser.getDate()==null  || path == null) {
					JOptionPane.showMessageDialog(this, "Por favor, rellene los campos obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					AppChat.INSTANCE.actualizarUsuario(campoNombre.getText(), campoApellidos.getText(), new String(passwordField.getPassword()),
							dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), campoMovil.getText(), path ,textArea.getText());
					JOptionPane.showMessageDialog(this, "Usuario modificado correctamente", "Registro", JOptionPane.INFORMATION_MESSAGE);
					this.setVisible(false);
				    this.dispose();
				    ventanaPadre.setVisible(true);
				    ventanaPadre.revalidate();
				    ventanaPadre.repaint();
				    ventanaPadre.validate();
				}
			});
			GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
			gbc_btnRegistrar.insets = new Insets(0, 0, 5, 5);
			gbc_btnRegistrar.gridx = 1;
			gbc_btnRegistrar.gridy = 7;
			this.getContentPane().add(btnRegistrar, gbc_btnRegistrar);
			
		}
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setUI(new RoundButtonUI(SystemColor.textHighlight,new Color(255,100,100)));
        
		btnCancelar.addActionListener( e -> {
		    this.setVisible(false);
		    this.dispose();
		    ventanaPadre.setVisible(true);
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 7;
		this.getContentPane().add(btnCancelar, gbc_btnCancelar);
		
		if(u!=null) {
			
		}
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

