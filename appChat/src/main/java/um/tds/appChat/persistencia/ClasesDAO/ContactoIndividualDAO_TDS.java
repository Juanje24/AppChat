package um.tds.appChat.persistencia.ClasesDAO;

import java.util.List;
import um.tds.appChat.dominio.ContactoIndividual;
import um.tds.appChat.persistencia.InterfacesDAO.ContactoIndividualDAO;

public class ContactoIndividualDAO_TDS implements ContactoIndividualDAO{
	private static ContactoIndividualDAO_TDS unicaInstancia = new ContactoIndividualDAO_TDS();
	
	public static ContactoIndividualDAO_TDS getUnicaInstancia() { // patron singleton
		return unicaInstancia;
	}
	
	@Override
	public void registrarContactoIndividual(ContactoIndividual ContactoIndividual) {
		// TODO Auto-generated method stub
	}

	@Override
	public void borrarContactoIndividual(ContactoIndividual ContactoIndividual) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarContactoIndividual(ContactoIndividual ContactoIndividual) {
		// TODO Auto-generated method stub
	}

	@Override
	public ContactoIndividual recuperarContactoIndividualPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContactoIndividual> recuperarTodosContactoIndividuales() {
		// TODO Auto-generated method stub
		return null;
	}

}
