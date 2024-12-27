package um.tds.appChat.ventanas;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;


import um.tds.appChat.utils.RoundButtonUI;
import um.tds.appChat.dominio.ContactoIndividual;
import um.tds.appChat.dominio.Usuario;
import um.tds.appChat.singletons.AppChat;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private PanelContactos panelContactos;
	private AppChat app = AppChat.INSTANCE;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.setVisible(true);
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
	
	public Principal(JFrame frame) {
		initialize();
		this.setLocationRelativeTo(frame);
	}
	public void mostrar() {
		this.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setBounds(100, 100, 1280, 720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		this.getContentPane().add(panelNorte, BorderLayout.NORTH);
		
		
		panelContactos = new PanelContactos(app.getUsuarioActual().getContactos());
		this.getContentPane().add(panelContactos, BorderLayout.WEST);
		
		
		JPanel panelCentro = new JPanel();
		this.getContentPane().add(panelCentro, BorderLayout.CENTER);
		
		
		ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/addContact.png"));
		Image iconoEscalado = icono.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		JButton añadirContacto = new JButton(new ImageIcon(iconoEscalado));
		añadirContacto.setUI(new RoundButtonUI());
		añadirContacto.addActionListener(e -> {
			PanelAñadirContacto panel = new PanelAñadirContacto();
            int result = JOptionPane.showConfirmDialog(this, panel, "Panel Añadir Contacto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
            	ContactoIndividual c= app.agregarContacto(panel.getNombreContacto(), panel.getTelefonoContacto());
            	if(c!=null) {
            		JOptionPane.showMessageDialog(this, "Contacto añadido correctamente", "", JOptionPane.INFORMATION_MESSAGE);
            		panelContactos.addContact(c);
            		this.revalidate();
            		this.repaint();
            	}
            	else {
            		JOptionPane.showMessageDialog(this, "No se ha podido añadir el contacto", "Error", JOptionPane.ERROR_MESSAGE);
            	}
            }
		});
		panelNorte.add(añadirContacto);
		
		
	    // Crear el JPanel para el usuario
	    JPanel panelUsuario = new JPanel();
	    panelUsuario.setLayout(new FlowLayout(FlowLayout.LEFT));

	    // Añadir nombre del usuario
	    JLabel nombreUsuario = new JLabel(app.getUsuarioActual().getNombre());
	    panelUsuario.add(nombreUsuario);
	    
	    // Añadir foto del usuario
	    JLabel fotoUsuario = new JLabel();
	    ImageIcon iconoUsuario = new ImageIcon(getClass().getResource(app.getUsuarioActual().getUrlImagen()));
	    Image imagenEscalada = iconoUsuario.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	    fotoUsuario.setIcon(new ImageIcon(imagenEscalada));
	    panelUsuario.add(fotoUsuario);
	    
	    panelUsuario.setBorder(BorderFactory.createLineBorder(new Color(20,20,20), 2));
	   

	    // Añadir MouseListener para abrir el panel de edición
	    JFrame frame = this;
	    panelUsuario.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	        	Registro ventanaRegistro = new Registro(frame,app.getUsuarioActual());
				frame.setVisible(false);
				ventanaRegistro.mostrar();
				nombreUsuario.setText(app.getUsuarioActual().getNombre());
				ImageIcon iconoUsuario = new ImageIcon(getClass().getResource(app.getUsuarioActual().getUrlImagen()));
				Image imagenEscalada = iconoUsuario.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				fotoUsuario.setIcon(new ImageIcon(imagenEscalada));
				
	        }
	    });

	    panelNorte.add(panelUsuario);
		
		
	}
	
	
	 // Clase interna para el panel con dos campos de texto
    private class PanelAñadirContacto extends JPanel {
        private static final long serialVersionUID = 1L;
        private JTextField textField1;
        private JTextField textField2;
        public PanelAñadirContacto() {
        	setLayout(new GridBagLayout());
            setPreferredSize(new Dimension(350, 150)); // Tamaño personalizado
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);  // Espacio entre componentes
            
            // JLabel que ocupa la primera fila completa
            JLabel labelTitulo = new JLabel("Añade tu contacto");
            labelTitulo.setAlignmentX(CENTER_ALIGNMENT);
            labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
            labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            add(labelTitulo, gbc);
            
            // Primer campo de texto
            JLabel label1 = new JLabel("Nombre:");
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.NONE;
            add(label1, gbc);
            
            textField1 = new JTextField();
            textField1.setPreferredSize(new Dimension(200, 30)); // Ajustar tamaño del campo de texto
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(textField1, gbc);
            
            // Segundo campo de texto
            JLabel label2 = new JLabel("Teléfono:");
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.NONE;
            add(label2, gbc);
            
            textField2 = new JTextField();
            textField2.setPreferredSize(new Dimension(200, 30)); // Ajustar tamaño del campo de texto
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(textField2, gbc);
        }
        public String getNombreContacto() {
        	return textField1.getText();
        }

		public String getTelefonoContacto() {
			return textField2.getText();
		}
    }
  
	
}
