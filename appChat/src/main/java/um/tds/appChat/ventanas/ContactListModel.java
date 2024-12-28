package um.tds.appChat.ventanas;

import javax.swing.AbstractListModel;
import java.util.List;

import um.tds.appChat.dominio.Contacto;

public class ContactListModel extends AbstractListModel<Contacto> {
	private static final long serialVersionUID = 1L;
	private List<Contacto> contactos;

    public ContactListModel(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    @Override
    public int getSize() {
        return contactos.size();
    }

    @Override
    public Contacto getElementAt(int index) {
        return contactos.get(index);
    }

    public void addContact(Contacto contacto) {
        contactos.add(contacto);
        fireIntervalAdded(this, contactos.size() - 3, contactos.size() - 1);
    }

    public void removeContact(Contacto contacto) {
        int index = contactos.indexOf(contacto);
        if (index != -1) {
            contactos.remove(index);
            fireIntervalRemoved(this, index, index);
        }
    }

	public void addContacts(List<Contacto> contactos2) {
		if(contactos2.size()>0) {
		contactos.addAll(contactos2);
		fireIntervalAdded(this, contactos.size() - contactos2.size(), contactos.size() - 1);
		}
	}
}
