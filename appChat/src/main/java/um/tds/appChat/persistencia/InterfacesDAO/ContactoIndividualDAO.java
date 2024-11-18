package um.tds.appChat.persistencia.InterfacesDAO;

import java.util.List;

import um.tds.appChat.dominio.ContactoIndividual;

public interface ContactoIndividualDAO {
	public void registrarContactoIndividual(ContactoIndividual contactoIndividual);
	public void borrarContactoIndividual(ContactoIndividual contactoIndividual);
	public ContactoIndividual modificarContactoIndividual(ContactoIndividual contactoIndividual);
	public ContactoIndividual recuperarContactoIndividualPorId(int id);
	public List<ContactoIndividual> recuperarTodosContactosIndividuales();
}
