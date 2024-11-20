package um.tds.appChat.persistencia.InterfacesDAO;

import java.util.List;

import um.tds.appChat.dominio.ContactoIndividual;

public interface ContactoIndividualDAO {
	
	ContactoIndividual craete(ContactoIndividual ci);
	
	boolean delete(ContactoIndividual ci);
	
	void update(ContactoIndividual ci);
	
	ContactoIndividual get(int id);
	
	List<ContactoIndividual> getAll();
}
