package um.tds.appChat.ventanas;

import java.util.List;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import um.tds.appChat.utils.RoundButtonUI;
import um.tds.appChat.dominio.Contacto;
import um.tds.appChat.dominio.ContactoIndividual;
import um.tds.appChat.singletons.AppChat;

public class Principal extends JFrame implements ActualizacionVistaListener {

	private static final long serialVersionUID = 1L;
	private PanelContactos panelContactos;
	private AppChat app = AppChat.INSTANCE;
	private Contacto contactoSeleccionado;
	private JPanel panelCentro;
	private JButton serPremium;
	private JFrame frame;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = this;
		this.setTitle("AppChat");
		this.setBounds(100, 100, 1280, 720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(new Color(215, 215, 215));
		this.getContentPane().add(panelNorte, BorderLayout.NORTH);
		
		panelCentro = new PanelChat(this);
		this.getContentPane().add(panelCentro, BorderLayout.CENTER);
		
		panelContactos = new PanelContactos(app.getUsuarioActual().getContactos());
		this.getContentPane().add(panelContactos, BorderLayout.WEST);
		panelContactos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                   contactoSeleccionado = panelContactos.getContactoSeleccionado();
                    if (contactoSeleccionado != null) {
                    	contactoSeleccionado.setLeidos();
                    	AppChat.INSTANCE.leidoEnPersistencia(contactoSeleccionado);
                    	                   	
                        ((PanelChat) panelCentro).setContacto(contactoSeleccionado);
                        frame.revalidate();
                        frame.repaint();
                    }
                }
            }
        });
		
		
		ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/addContact.png"));
		Image iconoEscalado = icono.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		JButton añadirContacto = new JButton(new ImageIcon(iconoEscalado));
		añadirContacto.setUI(new RoundButtonUI());
		añadirContacto.addActionListener(e -> {
			if (contactoSeleccionado != null) {
				 if(contactoSeleccionado.getNombre().equals(contactoSeleccionado.getTelefonoPropio())) {
					mostrarPanelAñadirContactoSinNum();
				 }
				 else {
					 mostrarPanelAñadirContacto();
				 }
			}
			else {
				mostrarPanelAñadirContacto();
			}
			
		});
		panelNorte.add(añadirContacto);
		
		ImageIcon iconoGrupo = new ImageIcon(getClass().getResource("/iconos/addGroup.png"));
		Image iconoGrupoEscalado = iconoGrupo.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		JButton añadirGrupo= new JButton(new ImageIcon(iconoGrupoEscalado));
		añadirGrupo.setUI(new RoundButtonUI());
		añadirGrupo.addActionListener(e->{
			PanelGrupo crearGrupo = new PanelGrupo(this,app.getUsuarioActual().getContactosIndividuales());
			crearGrupo.setVisible(true);
			this.setVisible(false);
		});
		panelNorte.add(añadirGrupo);
		
		ImageIcon iconoBusqueda = new ImageIcon(getClass().getResource("/iconos/search.png"));
		Image iconoBusquedaEscalado = iconoBusqueda.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		JButton buscarMensaje = new JButton(new ImageIcon(iconoBusquedaEscalado));
		buscarMensaje.setUI(new RoundButtonUI());
		buscarMensaje.addActionListener(e -> {
			PanelBusqueda panelBusqueda = new PanelBusqueda(this);
			panelBusqueda.setVisible(true);
			this.setVisible(false);
		});
		panelNorte.add(buscarMensaje);
		
		JButton verContactos = new JButton("Ver contactos");
		verContactos.setFont(new Font("Arial", Font.BOLD, 14));
		verContactos.setUI(new RoundButtonUI());
		verContactos.addActionListener(e -> {
		    PanelVerContactos panelVerContactos = new PanelVerContactos(app.getUsuarioActual().getContactos());
		    JDialog dialog = new JDialog(frame, "Tus contactos", true);
		    dialog.getContentPane().add(panelVerContactos);
		    dialog.setSize(700, 400);
		    dialog.setLocationRelativeTo(frame);
		    dialog.setVisible(true);
		});
		panelNorte.add(verContactos);
		
		serPremium = new JButton("Premium");
		serPremium.setFont(new Font("Arial", Font.BOLD, 14));
		serPremium.setUI(new RoundButtonUI());
		serPremium.addActionListener(e -> {
		    PanelPremium panelPremium = new PanelPremium(this);
		    panelPremium.setVisible(true);
		    this.setVisible(false);
		    
		});
		panelNorte.add(serPremium);
		if(AppChat.INSTANCE.isPremium()) {
			refrescarPremium();
		}
		
		
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
	    
	    
	    ImageIcon iconoLogout = new ImageIcon(getClass().getResource("/iconos/logout.png"));
		Image iconoLogoutEscalado = iconoLogout.getImage().getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH);
		JButton btnLogout= new JButton(new ImageIcon(iconoLogoutEscalado));
		btnLogout.setUI(new RoundButtonUI());
		btnLogout.addActionListener(e->{
			AppChat.INSTANCE.logout();
			Inicio ventanaLogin = new Inicio();
			ventanaLogin.setVisible(true);
			dispose();
		});
		panelNorte.add(btnLogout);
	}
	public void añadirContacto(Contacto c) {
		panelContactos.addContact(c);
		this.setVisible(true);
		this.revalidate();
		this.repaint();
		this.validate();	
	}
	
	public void eliminarContacto(Contacto c) {
		panelContactos.removeContact(c);
        this.setVisible(true);
        this.revalidate();
        this.repaint();
        this.validate();        
	}
	
	public void mostrarPanelAñadirContacto() {
		PanelAñadirContacto panel = new PanelAñadirContacto();
        int result = JOptionPane.showConfirmDialog(this, panel, "Panel Añadir Contacto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
        	ContactoIndividual c= app.agregarContacto(panel.getNombreContacto(), panel.getTelefonoContacto());
        	if(c!=null) {
        		JOptionPane.showMessageDialog(this, "Contacto añadido correctamente", "", JOptionPane.INFORMATION_MESSAGE);
        		añadirContacto(c);
        	}
        	else {
        		JOptionPane.showMessageDialog(this, "No se ha podido añadir el contacto", "Error", JOptionPane.ERROR_MESSAGE);
        	}
        }
	}
	public void mostrarPanelAñadirContactoSinNum() {
			String nuevoNombre = JOptionPane.showInputDialog(
            this,
            "Introduce el nuevo nombre para el contacto:",
            "Añadir nombre",
            JOptionPane.PLAIN_MESSAGE
            );

            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                AppChat.INSTANCE.actualizarNombreContacto(contactoSeleccionado, nuevoNombre);
                revalidate();
                repaint();
                ((PanelChat) panelCentro).setContacto(contactoSeleccionado);
                panelCentro.revalidate();
                panelCentro.repaint();
            }
	}
	public void refrescarPremium() {
		serPremium.setText("Exportar PDF");
		serPremium.setFont(new Font("Arial", Font.BOLD, 14));
		serPremium.setUI(new RoundButtonUI());
		serPremium.removeActionListener(serPremium.getActionListeners()[0]);
		serPremium.addActionListener(e -> {
			JFileChooser directoryChooser = new JFileChooser();
	        directoryChooser.setDialogTitle("Seleccionar directorio para guardar PDF");
	        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

	        int userSelection = directoryChooser.showSaveDialog(frame);

	        if (userSelection == JFileChooser.APPROVE_OPTION) {
	            File selectedDirectory = directoryChooser.getSelectedFile();
	            String nombrePDF= JOptionPane.showInputDialog(frame, "Introduce el nombre del archivo PDF", "Exportar PDF", JOptionPane.PLAIN_MESSAGE);
	            
	            String pdfPath = new File(selectedDirectory, nombrePDF+".pdf").getAbsolutePath();
	          	AppChat.INSTANCE.exportarPDF(contactoSeleccionado, pdfPath);
				
	        }
		    
		});
		this.setVisible(true);
        this.revalidate();
        this.repaint();
        this.validate();
	}
	public void actualizarVista(String nombre, int numMsjs) {
		panelContactos.reemplazarContactos(AppChat.INSTANCE.getUsuarioActual().getContactos());
		JOptionPane.showMessageDialog(this, "Nuevo mensaje de " + nombre + ". Tienes " + numMsjs + " mensajes sin leer", "¡Nuevo mensaje!", JOptionPane.INFORMATION_MESSAGE);
		this.setVisible(true);
		this.revalidate();
		this.repaint();
		this.validate();
		panelContactos.revalidate();
		panelContactos.repaint();
		panelContactos.validate();
		
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
    
 // Clase interna para el panel de ver todos los contactos
    private class PanelVerContactos extends JPanel {
        private static final long serialVersionUID = 1L;

        public PanelVerContactos(List<Contacto> contactos) {
        	JPanel panel = this;
            setLayout(new BorderLayout());
            // Column names for the JTable
            String[] columnNames = {"Foto", "Nombre", "Teléfono", "Saludo"};

            // Data for the JTable
            Object[][] data = new Object[contactos.size()][4];
            for (int i = 0; i < contactos.size(); i++) {
                Contacto contacto = contactos.get(i);
                data[i][0] = new ImageIcon(new ImageIcon(getClass().getResource(contacto.getUrlImagen())).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                data[i][1] = contacto.getNombre();
                data[i][2] = contacto.getTelefonoPropio();
                data[i][3] = contacto.getSaludo();
            }

            // Create the JTable with the data and column names
            JTable table = new JTable(data, columnNames) {
                private static final long serialVersionUID = 1L;

				// Override the method to display images in the first column
                @Override
                public Class<?> getColumnClass(int column) {
                    if (column == 0) {
                        return ImageIcon.class;
                    }
                    return String.class;
                }
            };
            table.setRowHeight(50); // Ajustar la altura de las filas

            // Centrar el texto en las columnas de nombre, teléfono y saludo
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // Centrar la columna de nombre
            table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // Centrar la columna de teléfono
            table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Centrar la columna de saludo
            // Add the JTable to a JScrollPane
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);
            JPanel panelSur = new JPanel();
            panelSur.setLayout(new FlowLayout(FlowLayout.RIGHT));
            JButton botonCerrar = new JButton("Cerrar");
            botonCerrar.setUI(new RoundButtonUI(SystemColor.textHighlight, RoundButtonUI.getRojoCancelar()));
			botonCerrar.addActionListener(e -> {
				SwingUtilities.getWindowAncestor(panel).dispose();
			});
			panelSur.add(botonCerrar);
			add(panelSur, BorderLayout.SOUTH);
        }
    }
    private class PanelPremium extends JDialog {
        private static final long serialVersionUID = 1L;

        public PanelPremium(JFrame parent) {
        	setTitle("Hazte Premium");
        	setLocationRelativeTo(parent);
        	setSize(600, 400);
            setLayout(new BorderLayout());
            
            
            JLabel etiquetaPrecio = new JLabel("Precio: " + AppChat.INSTANCE.getPrecioPremium() + " €");
            etiquetaPrecio.setFont(new Font("Arial", Font.BOLD, 16));
            etiquetaPrecio.setHorizontalAlignment(SwingConstants.CENTER);
            add(etiquetaPrecio, BorderLayout.NORTH);
            
            JComboBox<String> comboBox = new JComboBox<>(AppChat.INSTANCE.getDescuentosAplicables().toArray(new String[0])); 
            JPanel panelCentral = new JPanel();
            panelCentral.add(new JLabel("Selecciona un descuento de entre los aplicables:"));
            panelCentral.add(comboBox);
            
            
            JButton botonPagar = new JButton("Pagar");
            botonPagar.setUI(new RoundButtonUI());
            JButton botonCancelar = new JButton("Cancelar");
            botonCancelar.setUI(new RoundButtonUI(SystemColor.textHighlight, RoundButtonUI.getRojoCancelar()));

            // Panel para los botones
            JPanel panelBotones = new JPanel();
            panelBotones.add(botonPagar);
            panelBotones.add(botonCancelar);

            comboBox.addActionListener(e -> {
                String opcionSeleccionada = (String) comboBox.getSelectedItem();
                double precioConDescuento = AppChat.INSTANCE.getPrecioDescontado(opcionSeleccionada);
                etiquetaPrecio.setText("Precio: " + precioConDescuento + " €");
            });
            
            botonPagar.addActionListener(e -> {
                String opcionSeleccionada = (String) comboBox.getSelectedItem();
                double precioDescuento=AppChat.INSTANCE.hacerPremium(opcionSeleccionada);
                JOptionPane.showMessageDialog(this, "¡Enhorabuena! Ahora eres Premium por " + precioDescuento + " €", "¡Felicidades!", JOptionPane.INFORMATION_MESSAGE);
                ((Principal) parent).refrescarPremium();
                dispose();
            });

            botonCancelar.addActionListener(e -> {
            	parent.setVisible(true);
                dispose();
            });

            // Agregar los paneles al diálogo
            add(panelCentral, BorderLayout.CENTER);
            add(panelBotones, BorderLayout.SOUTH);

        }
    }
	
}
