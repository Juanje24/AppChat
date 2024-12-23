package um.tds.appChat.persistencia.InterfacesDAO;

import java.util.List;
import java.util.Optional;

import um.tds.appChat.dominio.ContactoIndividual;

public interface ContactoIndividualDAO {
	
	public ContactoIndividual registrarContactoIndividual(ContactoIndividual ContactoIndividual);

	public void borrarContactoIndividual(ContactoIndividual ContactoIndividual);

	public ContactoIndividual modificarContactoIndividual(ContactoIndividual ContactoIndividual);

	public Optional<ContactoIndividual> recuperarContactoIndividualPorId(int id);

	public List<ContactoIndividual> recuperarTodosContactoIndividuales();
}
