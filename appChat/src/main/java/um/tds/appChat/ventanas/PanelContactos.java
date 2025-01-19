package um.tds.appChat.ventanas;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;

import um.tds.appChat.dominio.Contacto;

public class PanelContactos extends JPanel {
	private static final long serialVersionUID = 1L;
	private JList<Contacto> listaContactos;
	
	public PanelContactos(List<Contacto> contactos) {
		ContactListModel modelo = new ContactListModel(new ArrayList<Contacto>());
		listaContactos = new JList<>(modelo);
		listaContactos.setCellRenderer(new ContactoListCellRenderer());
		JScrollPane scroll = new JScrollPane(listaContactos);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setMinimumSize(new Dimension(200, 1000));
		add(scroll);
		((ContactListModel) listaContactos.getModel()).addContacts(contactos);
	}

	public void addContact(Contacto contacto) {
		((ContactListModel) listaContactos.getModel()).addContact(contacto);
	}

	public void removeContact(Contacto contacto) {
		((ContactListModel) listaContactos.getModel()).removeContact(contacto);
	}
	public void reemplazarContactos(List<Contacto> nuevosContactos) {
	    ((ContactListModel) listaContactos.getModel()).setContacts(nuevosContactos);
	}

	public void addContacts(List<Contacto> contactos) {
		((ContactListModel) listaContactos.getModel()).addContacts(contactos);
	}
	public void addListSelectionListener(ListSelectionListener listener) {
        listaContactos.addListSelectionListener(listener);
    }
	public Contacto getContactoSeleccionado() {
		return listaContactos.getSelectedValue();
	}

	public void borrarSeleccion() {
		listaContactos.clearSelection();
	}
}
