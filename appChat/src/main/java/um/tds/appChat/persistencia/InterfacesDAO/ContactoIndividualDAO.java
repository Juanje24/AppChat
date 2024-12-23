package um.tds.appChat.persistencia.InterfacesDAO;

import java.util.List;

import um.tds.appChat.dominio.ContactoIndividual;

public interface ContactoIndividualDAO {
	
	public void registrarContactoIndividual(ContactoIndividual ContactoIndividual);

	public void borrarContactoIndividual(ContactoIndividual ContactoIndividual);

	public void modificarContactoIndividual(ContactoIndividual ContactoIndividual);

	public ContactoIndividual recuperarContactoIndividualPorId(int id);

	public List<ContactoIndividual> recuperarTodosContactoIndividuales();
}
