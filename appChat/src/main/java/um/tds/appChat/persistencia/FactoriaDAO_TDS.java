package um.tds.appChat.persistencia;

import um.tds.appChat.persistencia.ClasesDAO.*;
import um.tds.appChat.persistencia.InterfacesDAO.*;

public class FactoriaDAO_TDS extends FactoriaDAO {

	@Override
	public UsuarioDAO getUsuarioDAO() {
		// TODO Auto-generated method stub
		//return UsuarioDAO_TDS.getUnicaInstancia();
	}

	@Override
	public ContactoIndividualDAO getContactoIndividualDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GrupoDAO getGrupoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MensajeDAO getMensajeDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
