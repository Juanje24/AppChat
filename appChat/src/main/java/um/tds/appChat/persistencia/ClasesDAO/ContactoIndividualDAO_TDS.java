package um.tds.appChat.persistencia.ClasesDAO;

import java.util.List;
import java.util.Optional;

import um.tds.appChat.dominio.ContactoIndividual;
import um.tds.appChat.persistencia.InterfacesDAO.ContactoIndividualDAO;

public class ContactoIndividualDAO_TDS implements ContactoIndividualDAO{

	private static ContactoIndividualDAO_TDS unicaInstancia;
	
	public static ContactoIndividualDAO_TDS getUnicaInstancia() {
		if (unicaInstancia == null) {
			return new ContactoIndividualDAO_TDS();
		} else {return unicaInstancia;}
	}
	
	@Override
	public ContactoIndividual registrarContactoIndividual(ContactoIndividual ContactoIndividual) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrarContactoIndividual(ContactoIndividual ContactoIndividual) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ContactoIndividual modificarContactoIndividual(ContactoIndividual ContactoIndividual) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ContactoIndividual> recuperarContactoIndividualPorId(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<ContactoIndividual> recuperarTodosContactoIndividuales() {
		// TODO Auto-generated method stub
		return null;
	}

}
