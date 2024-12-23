package um.tds.appChat.persistencia;

import um.tds.appChat.persistencia.ClasesDAO.*;
import um.tds.appChat.persistencia.InterfacesDAO.*;

public class FactoriaDAO_TDS extends FactoriaDAO {

	@Override
	public UsuarioDAO getUsuarioDAO() {

		return UsuarioDAO_TDS.getUnicaInstancia();
	}

	@Override
	public ContactoIndividualDAO getContactoIndividualDAO() {
	
		return ContactoIndividualDAO_TDS.getUnicaInstancia();
	}

	@Override
	public GrupoDAO getGrupoDAO() {

		return  GrupoDAO_TDS.getUnicaInstancia();
	}

	@Override
	public MensajeDAO getMensajeDAO() {

		return  MensajeDAO_TDS.getUnicaInstancia();
	}
	

}
