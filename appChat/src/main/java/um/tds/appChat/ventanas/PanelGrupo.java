package um.tds.appChat.ventanas;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import um.tds.appChat.dominio.ContactoIndividual;
import um.tds.appChat.singletons.AppChat;
import um.tds.appChat.utils.RoundButtonUI;
import um.tds.appChat.utils.Utils;

public class PanelGrupo extends JFrame {
    private static final long serialVersionUID = 1L;
    private List<ContactoIndividual> contactos;
    private JFrame parent;
    private String path;

    public PanelGrupo() {
        super();
    }

    public PanelGrupo(JFrame frame, List<ContactoIndividual> contactosIndividuales) {
        this();
        this.contactos = contactosIndividuales;
        this.setLocationRelativeTo(frame);
        this.parent = frame;
        initialize();
    }

    private void initialize() {
    		
    	
        setTitle("Crear Grupo");
        setBounds(100, 100, 900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Espaciado entre bordes

        // Panel norte
        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new GridLayout(1, 4, 10, 0)); // 3 filas, 2 columnas;
        panelNorte.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding

        JLabel lblNombre = new JLabel("Nombre: ", SwingConstants.RIGHT);
        panelNorte.add(lblNombre);
        
        JTextField nombreGrupo = new JTextField();
        nombreGrupo.setPreferredSize(new Dimension(200, 1));
        panelNorte.add(nombreGrupo);

        JLabel lblFotos = new JLabel("Imagen del grupo", SwingConstants.CENTER);
        lblFotos.setPreferredSize(new Dimension(50, 50)); // Tamaño para la imagen
        panelNorte.add(lblFotos);

        JButton btnSubirFoto = new JButton("Subir foto");
		btnSubirFoto.setUI(new RoundButtonUI(new Color(10,10,10), new Color(255,255,255)));
		btnSubirFoto.addActionListener(e-> {
				PanelArrastraImagen panelArrastraImagen = new PanelArrastraImagen(this);
				List<File> imagenes = panelArrastraImagen.showDialog();
				if (imagenes != null && !imagenes.isEmpty() && imagenes.get(0) != null) {
					path = Utils.getRutaResourceFromFile(imagenes.get(0));
					ImageIcon iconoImagen = new ImageIcon(getClass().getResource(path));
					Image imagenEscalada = iconoImagen.getImage().
					getScaledInstance(50, 50, Image.SCALE_SMOOTH);
					lblFotos.setIcon(new ImageIcon(imagenEscalada));
					lblFotos.setText("");
					panelArrastraImagen.setVisible(false);
					panelArrastraImagen.dispose();
				}
		});
        panelNorte.add(btnSubirFoto);

        add(panelNorte, BorderLayout.NORTH);

        // Modelo para las listas
        DefaultListModel<ContactoIndividual> modeloContactos = new DefaultListModel<>();
        DefaultListModel<ContactoIndividual> modeloGrupo = new DefaultListModel<>();
        contactos.forEach(modeloContactos::addElement);

        // Listas con scroll y padding
        JList<ContactoIndividual> listaContactosJList = new JList<>(modeloContactos);
        JList<ContactoIndividual> grupoContactosJList = new JList<>(modeloGrupo);

        listaContactosJList.setCellRenderer(new ContactoIndividualRenderer());
        grupoContactosJList.setCellRenderer(new ContactoIndividualRenderer());

        JScrollPane scrollContactos = new JScrollPane(listaContactosJList);
        JScrollPane scrollGrupo = new JScrollPane(grupoContactosJList);

        scrollContactos.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Contactos"),
                new EmptyBorder(10, 10, 10, 10)
        ));

        scrollGrupo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Grupo"),
                new EmptyBorder(10, 10, 10, 10)
        ));

        // Botones centrales
        JButton btnAdd = new JButton("Añadir >>");
        btnAdd.setUI(new RoundButtonUI());
        JButton btnRemove = new JButton("<< Quitar");
        btnRemove.setUI(new RoundButtonUI());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); 
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelBotones.add(btnAdd, gbc);

        gbc.gridy = 1;
        panelBotones.add(btnRemove, gbc);

       
        JPanel panelOeste = new JPanel(new BorderLayout());
        panelOeste.setPreferredSize(new Dimension(300, 0)); // Ancho fijo, altura flexible
        panelOeste.add(scrollContactos, BorderLayout.CENTER);
        add(panelOeste, BorderLayout.WEST);

        // Panel este con ancho fijo
        JPanel panelEste = new JPanel(new BorderLayout());
        panelEste.setPreferredSize(new Dimension(300, 0)); // Ancho fijo, altura flexible
        panelEste.add(scrollGrupo, BorderLayout.CENTER);
        add(panelEste, BorderLayout.EAST);
        
        
        add(panelBotones, BorderLayout.CENTER);
        
        
        // Panel sur
        JPanel panelSur = new JPanel();
        panelSur.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelSur.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding

        JButton btnCrear = new JButton("Crear Grupo");
        btnCrear.setUI(new RoundButtonUI());
        JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setUI(new RoundButtonUI(SystemColor.textHighlight,new Color(255,100,100)));
        panelSur.add(btnCrear);
        panelSur.add(btnCancelar);

        add(panelSur, BorderLayout.SOUTH);

        // Acciones de los botones
        btnAdd.addActionListener(e -> {
            ContactoIndividual seleccionado = listaContactosJList.getSelectedValue();
            if (seleccionado != null && !modeloGrupo.contains(seleccionado)) {
                modeloGrupo.addElement(seleccionado);
            }
        });

        btnRemove.addActionListener(e -> {
            ContactoIndividual seleccionado = grupoContactosJList.getSelectedValue();
            if (seleccionado != null) {
                modeloGrupo.removeElement(seleccionado);
            }
        });
        
		btnCancelar.addActionListener(e -> {
			parent.setVisible(true);
			this.dispose();
		});
		
		btnCrear.addActionListener(e -> {
			List<ContactoIndividual> contactosGrupo = getContactosGrupo(modeloGrupo);
			String nombre = nombreGrupo.getText();
			String foto = path;
			((Principal) parent).añadirContacto(AppChat.INSTANCE.crearGrupo(nombre, contactosGrupo, foto));
			this.dispose();
		});
		
		
    }

    
    private List<ContactoIndividual> getContactosGrupo(DefaultListModel<ContactoIndividual> modeloGrupo) {
        List<ContactoIndividual> contactosGrupo = new ArrayList<>();
        for (int i = 0; i < modeloGrupo.getSize(); i++) {
            contactosGrupo.add(modeloGrupo.getElementAt(i));
        }
        return contactosGrupo;
    }
    
    
    private class ContactoIndividualRenderer extends DefaultListCellRenderer {
        private static final long serialVersionUID = 1L;

		@Override
        public java.awt.Component getListCellRendererComponent(
                JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof ContactoIndividual) {
                ContactoIndividual contacto = (ContactoIndividual) value;
                setText(contacto.getNombre() + " " + contacto.getApellidos());
                setHorizontalAlignment(SwingConstants.CENTER);
            }
            return this;
        }
    }

}