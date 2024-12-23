package um.tds.appChat.persistencia;

import tds.driver.FactoriaServicioPersistencia;
import um.tds.appChat.persistencia.ClasesDAO.*;
import um.tds.appChat.persistencia.InterfacesDAO.*;

public class FactoriaDAO_TDS extends FactoriaDAO {

	@Override
	public UsuarioDAO getUsuarioDAO() {

		
	}

	@Override
	public ContactoIndividualDAO getContactoIndividualDAO() {
	
		return new ContactoIndividualDAO_TDS();
	}

	@Override
	public GrupoDAO getGrupoDAO() {

		return new GrupoDAO_TDS();
	}

	@Override
	public MensajeDAO getMensajeDAO() {

		return new MensajeDAO_TDS();
	}
	

}
