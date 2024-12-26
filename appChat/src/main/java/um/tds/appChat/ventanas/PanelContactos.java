package um.tds.appChat.ventanas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import um.tds.appChat.dominio.Contacto;

public class PanelContactos extends JPanel {
	private static final long serialVersionUID = 1L;
	private JList<Contacto> listaContactos;
	
	public PanelContactos() {
		ContactListModel modelo = new ContactListModel(new ArrayList<Contacto>());
		listaContactos = new JList<>(modelo);
		listaContactos.setCellRenderer(new ContactoListCellRenderer());
		JScrollPane scroll = new JScrollPane(listaContactos);
		add(scroll);
	}

	public void addContact(Contacto contacto) {
		((ContactListModel) listaContactos.getModel()).addContact(contacto);
	}

	public void removeContact(Contacto contacto) {
		((ContactListModel) listaContactos.getModel()).removeContact(contacto);
	}

	public void addContacts(List<Contacto> contactos) {
		((ContactListModel) listaContactos.getModel()).addContacts(contactos);
	}
}
