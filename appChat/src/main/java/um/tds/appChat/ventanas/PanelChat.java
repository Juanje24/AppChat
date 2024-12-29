package um.tds.appChat.ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import tds.BubbleText;
import um.tds.appChat.dominio.Contacto;
import um.tds.appChat.dominio.Mensaje;
import um.tds.appChat.utils.RoundButtonUI;

public class PanelChat extends JPanel {
    private static final long serialVersionUID = 1L;
    private List<Mensaje> mensajes;
    private String nombreEmisor;
    private String nombreReceptor;
    private JTextField campoMensaje;
    private JButton botonEmoticonos;
    private JButton botonEnviar;

    /**
     * Create the application.
     */
    public PanelChat(Contacto c, String nombreEmisor) {
        this.mensajes = c.getMensajes();
        this.nombreEmisor = nombreEmisor;
        this.nombreReceptor = c.getNombre();
        initialize(c);
    }

    public PanelChat(String nombreEmisor) {
    	this.nombreEmisor = nombreEmisor;
    	initialize();
	}

    private void initialize() {
        setLayout(new BorderLayout());

        // Parte superior: Foto, nombre y botón de tres puntos
        JPanel panelSuperior = new JPanel(new GridLayout(1,3));
     

        add(panelSuperior, BorderLayout.NORTH);

        // Parte central: Panel para mostrar los mensajes
        JPanel panelMensajes = new JPanel();
        panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelMensajes);
        add(scrollPane, BorderLayout.CENTER);

        // Parte inferior: Campo para escribir mensajes y botones
        JPanel panelInferior = new JPanel(new BorderLayout());
        campoMensaje = new JTextField();
        botonEmoticonos = new JButton("Emoji");
        botonEnviar = new JButton("Enviar");

        panelInferior.add(botonEmoticonos, BorderLayout.WEST);
        panelInferior.add(campoMensaje, BorderLayout.CENTER);
        panelInferior.add(botonEnviar, BorderLayout.EAST);

        add(panelInferior, BorderLayout.SOUTH);
    }
    
    
    
	private void initialize(Contacto contacto) {
        setLayout(new BorderLayout());

        // Parte superior: Foto, nombre y botón de tres puntos
        JPanel panelSuperior = new JPanel(new GridLayout(1,3,0,10));
        JLabel fotoUsuario = new JLabel();
	    ImageIcon iconoUsuario = new ImageIcon(getClass().getResource(contacto.getUrlImagen()));
	    Image imagenEscalada = iconoUsuario.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	    fotoUsuario.setIcon(new ImageIcon(imagenEscalada));
        JLabel etiquetaNombre = new JLabel(nombreReceptor);
        etiquetaNombre.setFont(new Font("Arial", Font.BOLD, 16));
        JButton botonOpciones = new JButton("⋮");
        botonOpciones.setUI(new RoundButtonUI());
        
        botonOpciones.addActionListener(e->{
			JPopupMenu menu = new JPopupMenu();
			JMenuItem item1 = new JMenuItem("Cambiar nombre");
			JMenuItem item3 = new JMenuItem("Eliminar");
			menu.add(item1);
			menu.add(item3);
			menu.show(botonOpciones, 0, botonOpciones.getHeight());
        });
        
        panelSuperior.add(fotoUsuario);
        panelSuperior.add(etiquetaNombre);
        panelSuperior.add(botonOpciones);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(panelSuperior, BorderLayout.NORTH);

        // Parte central: Panel para mostrar los mensajes
        JPanel panelMensajes = new JPanel();
        panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelMensajes);
        for (Mensaje m : mensajes) {
            if (m.getTipo() == BubbleText.SENT) {
                panelMensajes.add(new BubbleText(this, m.getTexto(), Color.GREEN, nombreEmisor, m.getTipo()));
            } else {
                panelMensajes.add(new BubbleText(this, m.getTexto(), Color.LIGHT_GRAY, nombreReceptor, m.getTipo()));
            }
        }

        add(scrollPane, BorderLayout.CENTER);

        // Parte inferior: Campo para escribir mensajes y botones
        JPanel panelInferior = new JPanel(new BorderLayout());
        campoMensaje = new JTextField();
        botonEmoticonos = new JButton("Emoji");
        botonEnviar = new JButton("Enviar");

        panelInferior.add(botonEmoticonos, BorderLayout.WEST);
        panelInferior.add(campoMensaje, BorderLayout.CENTER);
        panelInferior.add(botonEnviar, BorderLayout.EAST);

        add(panelInferior, BorderLayout.SOUTH);
    }

    // Métodos para interactuar con el panel
    public String getTextoMensaje() {
        return campoMensaje.getText();
    }

    public void limpiarCampoMensaje() {
        campoMensaje.setText("");
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
    
}
