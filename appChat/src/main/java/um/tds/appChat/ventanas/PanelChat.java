package um.tds.appChat.ventanas;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import tds.BubbleText;
import um.tds.appChat.dominio.Contacto;
import um.tds.appChat.dominio.Grupo;
import um.tds.appChat.dominio.Mensaje;
import um.tds.appChat.singletons.AppChat;
import um.tds.appChat.utils.RoundButtonUI;
import um.tds.appChat.utils.Utils;

public class PanelChat extends JPanel {
	private static final int ALTURA_BURBUJA = 82;
    private static final long serialVersionUID = 1L;
    private JFrame principal;
    private List<Mensaje> mensajes;
    private String nombreReceptor;
    private JTextArea areaTexto;
    private JButton botonEmoticonos;
    private JButton botonEnviar;
    int emoji=-1;
    private int altura;
    private JPanel panelMensajes;
    private JScrollPane scrollPane;

    /**
     * Create the application.
     */

    public PanelChat(JFrame principal) {
    	this.principal = principal;
    	initialize(null);
	}
    
	private void initialize(Contacto contacto) {
		boolean isGrupo;
		if(contacto instanceof Grupo) {
			isGrupo=true;
		}else {
			isGrupo=false;
		}
        setLayout(new BorderLayout());
        JPanel panel = this;
        // Parte superior: Foto, nombre y botón de tres puntos
        JPanel panelSuperior = new JPanel(new BorderLayout());
        if (contacto != null) {

	        JLabel fotoUsuario = new JLabel();
		    ImageIcon iconoUsuario = new ImageIcon(getClass().getResource(contacto.getUrlImagen()));
		    Image imagenEscalada = iconoUsuario.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		    fotoUsuario.setIcon(new ImageIcon(imagenEscalada));
	        JLabel etiquetaNombre = new JLabel(nombreReceptor);
	        etiquetaNombre.setFont(new Font("Arial", Font.BOLD, 16));
	        JButton botonOpciones = new JButton("⋮");
	        botonOpciones.setUI(new RoundButtonUI());
	        
	        botonOpciones.addActionListener(e -> {
	            JPopupMenu menu = new JPopupMenu();
	
	            // Opción "Cambiar nombre"
	            JMenuItem itemCambiarNombre = new JMenuItem("Cambiar nombre");
	            itemCambiarNombre.addActionListener(ev -> {
	                String nuevoNombre = JOptionPane.showInputDialog(
	                    this,
	                    "Introduce el nuevo nombre para el contacto:",
	                    "Cambiar nombre",
	                    JOptionPane.PLAIN_MESSAGE
	                );
	
	                if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
	                    nombreReceptor = nuevoNombre.trim();
	                    AppChat.INSTANCE.actualizarNombreContacto(contacto, nuevoNombre);
	                    etiquetaNombre.setText(nuevoNombre);
	                    SwingUtilities.getWindowAncestor(this).revalidate();
	                    SwingUtilities.getWindowAncestor(this).repaint();
	                    panel.revalidate();
	                    panel.repaint(); 
	                    
	                }
	            });
	            JMenuItem itemCambiar = new JMenuItem("Gestionar miembros");
	
				if (isGrupo) {
		         // Opción "Cambiar nombre"
		           
		            itemCambiar.addActionListener(ev -> {
		                  PanelGrupo panelGrupo = new PanelGrupo(principal,  AppChat.INSTANCE.getUsuarioActual().getContactosIndividuales(), (Grupo) contacto);
		                  panelGrupo.setVisible(true);
		            });
				}
	
	            // Opción "Eliminar"
	            JMenuItem itemEliminar = new JMenuItem("Eliminar");
	            itemEliminar.addActionListener(ev -> {
	                int confirmacion = JOptionPane.showConfirmDialog(
	                    this,
	                    "¿Estás seguro de que quieres eliminar este contacto?",
	                    "Confirmar eliminación",
	                    JOptionPane.YES_NO_OPTION,
	                    JOptionPane.WARNING_MESSAGE
	                );
	
	                if (confirmacion == JOptionPane.YES_OPTION) {
						AppChat.INSTANCE.eliminarContacto(contacto);
						((Principal) principal).eliminarContacto(contacto);
						revalidate();
						repaint();
						principal.revalidate();
						principal.repaint();
						
	                }
	            });
	
	            // Añade las opciones al menú
	            menu.add(itemCambiarNombre);
	            if (isGrupo) {
		        	   menu.add(itemCambiar);
		           }
	            menu.add(itemEliminar);
	           
	
	            // Muestra el menú contextual
	            menu.show(botonOpciones, 0, botonOpciones.getHeight());
	        });
	        etiquetaNombre.setHorizontalAlignment(SwingConstants.CENTER); // Texto centrado horizontalmente
	        etiquetaNombre.setVerticalAlignment(SwingConstants.CENTER);   // Texto centrado verticalmente
	        botonOpciones.setPreferredSize(new Dimension(60, 50));
	        panelSuperior.add(fotoUsuario, BorderLayout.WEST);
	        panelSuperior.add(etiquetaNombre, BorderLayout.CENTER);
	        panelSuperior.add(botonOpciones, BorderLayout.EAST);
        }
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(panelSuperior, BorderLayout.NORTH);

     // Parte central: Panel para mostrar los mensajes
        panelMensajes = new JPanel();
        panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.Y_AXIS));
        panelMensajes.setPreferredSize(new Dimension(250, 600));
        scrollPane = new JScrollPane(panelMensajes);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        if (contacto != null) {
            for (Mensaje mensaje : mensajes) {
                panelMensajes.add(Utils.getBubbleFromMensaje(mensaje, panel));
            }
        }      
        
        add(scrollPane, BorderLayout.CENTER);
        altura = panelMensajes.getComponentCount() * ALTURA_BURBUJA; // Suponiendo que cada burbuja de mensaje tiene una altura de 50 píxeles
        panelMensajes.setPreferredSize(new Dimension(panelMensajes.getWidth(), altura));
        revalidate();
        repaint();
		bajarBarra();
        // Parte inferior: Campo para escribir mensajes y botones
        JPanel panelInferior = new JPanel(new BorderLayout());
     // Configuración de la área de texto
        areaTexto = new JTextArea(1, 30); // Inicial con 1 línea
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setFont(new Font("Arial", Font.PLAIN, 14));
     // Hacer que el JTextArea crezca dinámicamente
        areaTexto.setPreferredSize(new Dimension(300, 30));
        areaTexto.getDocument().addDocumentListener(new DocumentListener() {
        public void insertUpdate(DocumentEvent e) { ajustarTamañoAreaTexto(); }
        public void removeUpdate(DocumentEvent e) { ajustarTamañoAreaTexto(); }
        public void changedUpdate(DocumentEvent e) { ajustarTamañoAreaTexto(); }
        });
        areaTexto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume(); // Evita que se inserte un salto de línea
                    String texto = getTextoMensaje();
        			if (!texto.trim().isEmpty()) {
        				if (contacto != null) {
        					Mensaje msj=AppChat.INSTANCE.enviarMensajeContacto(contacto, texto, emoji);
        					panelMensajes.add(Utils.getBubbleFromMensaje(msj, panel));
        					altura = panelMensajes.getComponentCount() * ALTURA_BURBUJA; // Suponiendo que cada burbuja de mensaje tiene una altura de 50 píxeles
        					panelMensajes.setPreferredSize(new Dimension(panelMensajes.getWidth(), altura));
        					revalidate();
        					repaint();
        					bajarBarra();
        					limpiarCampoMensaje();
        				}
        			}
                }
            }
        });
        
        
        
        ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/emoji.png"));
		Image iconoEscalado = icono.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		botonEmoticonos = new JButton(new ImageIcon(iconoEscalado));
        botonEmoticonos.setUI(new RoundButtonUI());
        
     // Configurar el botón de emoji
        botonEmoticonos.addActionListener(e -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            EmojiSelectorDialog emojiDialog = new EmojiSelectorDialog(parentFrame);
            emojiDialog.setVisible(true);
            emoji=emojiDialog.getSelectedEmoji();
			if (emoji != -1) {
				Mensaje msj=AppChat.INSTANCE.enviarMensajeContacto(contacto, "", emoji);
				panelMensajes.add(Utils.getBubbleFromMensaje(msj, panel));
				altura = panelMensajes.getComponentCount() * ALTURA_BURBUJA; // Suponiendo que cada burbuja de mensaje tiene una altura de 50 píxeles
				panelMensajes.setPreferredSize(new Dimension(panelMensajes.getWidth(), altura));
				emoji=-1;
				revalidate();
				repaint();
				bajarBarra();
                limpiarCampoMensaje();
                
			}
        });
        
        
        icono = new ImageIcon(getClass().getResource("/iconos/send.png"));
		iconoEscalado = icono.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		botonEnviar = new JButton(new ImageIcon(iconoEscalado));
        botonEnviar.setUI(new RoundButtonUI());
        

        panelInferior.add(botonEmoticonos, BorderLayout.WEST);
        panelInferior.add(areaTexto, BorderLayout.CENTER);
        panelInferior.add(botonEnviar, BorderLayout.EAST);
		botonEnviar.addActionListener(e -> {
			String texto = getTextoMensaje();
			if (!texto.trim().isEmpty()) {
				if (contacto != null) {
					Mensaje msj=AppChat.INSTANCE.enviarMensajeContacto(contacto, texto, emoji);
					panelMensajes.add(Utils.getBubbleFromMensaje(msj, panel));
					altura = panelMensajes.getComponentCount() * ALTURA_BURBUJA; // Suponiendo que cada burbuja de mensaje tiene una altura de 50 píxeles
					panelMensajes.setPreferredSize(new Dimension(panelMensajes.getWidth(), altura));
					revalidate();
					repaint();
					bajarBarra();
					limpiarCampoMensaje();
					
				}
			}
		});

        add(panelInferior, BorderLayout.SOUTH);
    }
	
    // Métodos para interactuar con el panel
    public String getTextoMensaje() {
        return areaTexto.getText();
    }
    public void bajarBarra() {
    	SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum()));
    }
    public void limpiarCampoMensaje() {
        areaTexto.setText("");
    }

    public void setBotonEnviarListener(ActionListener listener) {
        botonEnviar.addActionListener(listener);
    }

    public void setBotonEmoticonosListener(ActionListener listener) {
        botonEmoticonos.addActionListener(listener);
    }

	public void setContacto(Contacto c) {
		this.nombreReceptor = c.getNombre();
		this.mensajes = c.getMensajes();
		this.removeAll();
		initialize(c);
	}

	// Método para ajustar el tamaño del JTextArea dinámicamente
	private void ajustarTamañoAreaTexto() {
	 int lineas = areaTexto.getLineCount();
	 int altura = 20 * lineas; // Ajusta el valor según el tamaño de fuente
	 areaTexto.setPreferredSize(new Dimension(300, altura));
	 areaTexto.revalidate();
	}
	
	
	private class EmojiSelectorDialog extends JDialog {
	    private static final long serialVersionUID = 1L;
	    private int selectedEmoji = -1;

	    public EmojiSelectorDialog(JFrame parent) {
	        super(parent, "Seleccionar Emoji", true);
	        setLayout(new BorderLayout());

	        // Crear el array de emojis
	        Object[][] emojiData = new Object[BubbleText.MAXICONO + 1][1];
	        for (int i = 0; i <= BubbleText.MAXICONO; i++) {
	            emojiData[i][0] = BubbleText.getEmoji(i);
	        }

	        // Crear la tabla de emojis
	        String[] columnNames = {""};
	        JTable emojiTable = new JTable(emojiData, columnNames) {
	            private static final long serialVersionUID = 1L;

				@Override
	            public Class<?> getColumnClass(int column) {
	                return ImageIcon.class;
	            }
	        };
	        emojiTable.setRowHeight(50);  // Ajusta la altura de las filas para mostrar los emojis correctamente
	        emojiTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	        // Añadir un mouse listener para obtener el valor seleccionado
	        emojiTable.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                int row = emojiTable.getSelectedRow();
	                if (row != -1) {
	                    selectedEmoji = row;
	                    dispose();  // Cerrar el diálogo después de la selección
	                }
	            }
	        });

	        // Añadir la tabla a un JScrollPane
	        JScrollPane scrollPane = new JScrollPane(emojiTable);
	        add(scrollPane, BorderLayout.CENTER);

	        setSize(300, 400);
	        setLocationRelativeTo(parent);
	    }

	    public int getSelectedEmoji() {
	        return selectedEmoji;
	    }
	}
    
}
