package um.tds.appChat.persistencia;

import um.tds.appChat.persistencia.ClasesDAO.*;
import um.tds.appChat.persistencia.InterfacesDAO.*;

public class FactoriaDAO_TDS extends FactoriaDAO {

	@Override
	public UsuarioDAO crearUsuarioDAO() {

		return new UsuarioDAO_TDS();
	}

	@Override
	public ContactoIndividualDAO crearContactoIndividualDAO() {
	
		return new ContactoIndividualDAO_TDS();
	}

	@Override
	public GrupoDAO crearGrupoDAO() {

		return new GrupoDAO_TDS();
	}

	@Override
	public MensajeDAO crearMensajeDAO() {

		return new MensajeDAO_TDS();
	}
	

}
