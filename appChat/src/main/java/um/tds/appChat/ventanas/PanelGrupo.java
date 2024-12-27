package um.tds.appChat.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.*;
import um.tds.appChat.dominio.ContactoIndividual;

public class PanelGrupo extends JFrame {
    private static final long serialVersionUID = 1L;
    private List<ContactoIndividual> contactos;

    public PanelGrupo() {
        super();
    }

    public PanelGrupo(JFrame frame, List<ContactoIndividual> contactosIndividuales) {
        this();
        this.contactos = contactosIndividuales;
        this.setLocationRelativeTo(frame);
        initialize();
    }

    private void initialize() {
        setTitle("Crear Grupo");
        setBounds(100, 100, 900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Modelo para las listas
        DefaultListModel<ContactoIndividual> modeloContactos = new DefaultListModel<>();
        DefaultListModel<ContactoIndividual> modeloGrupo = new DefaultListModel<>();
        contactos.forEach(modeloContactos::addElement);

        // Listas con scroll
        JList<ContactoIndividual> listaContactosJList = new JList<>(modeloContactos);
        JList<ContactoIndividual> grupoContactosJList = new JList<>(modeloGrupo);

        // Custom ListCellRenderer to show "nombre apellidos"
        listaContactosJList.setCellRenderer(new ContactoIndividualRenderer());
        grupoContactosJList.setCellRenderer(new ContactoIndividualRenderer());

        JScrollPane scrollContactos = new JScrollPane(listaContactosJList);
        JScrollPane scrollGrupo = new JScrollPane(grupoContactosJList);

        // Botones
        JButton btnAdd = new JButton("Añadir >>");
        JButton btnRemove = new JButton("<< Quitar");

        // Panel central para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(btnAdd);
        panelBotones.add(btnRemove);

        // Añadir los componentes al panel principal
        add(scrollContactos, BorderLayout.WEST);
        add(panelBotones, BorderLayout.CENTER);
        add(scrollGrupo, BorderLayout.EAST);

        // Acciones de los botones
        btnAdd.addActionListener(e -> {
            ContactoIndividual seleccionado = listaContactosJList.getSelectedValue();
            if (seleccionado != null) {
                modeloGrupo.addElement(seleccionado);
            }
        });

        btnRemove.addActionListener(e -> {
            ContactoIndividual seleccionado = grupoContactosJList.getSelectedValue();
            if (seleccionado != null) {
                modeloGrupo.removeElement(seleccionado);
            }
        });
    }

    // Custom ListCellRenderer to show "nombre apellidos"
    private static class ContactoIndividualRenderer extends DefaultListCellRenderer {
        @Override
        public java.awt.Component getListCellRendererComponent(
                JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof ContactoIndividual) {
                ContactoIndividual contacto = (ContactoIndividual) value;
                setText(contacto.getNombre() + " " + contacto.getApellidos());
            }
            return this;
        }
    }
}