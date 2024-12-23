package um.tds.appChat.persistencia;

import um.tds.appChat.persistencia.ClasesDAO.*;
import um.tds.appChat.persistencia.InterfacesDAO.*;

public class FactoriaDAO_TDS extends FactoriaDAO {

	@Override
	public UsuarioDAO crearUsuarioDAO() {

		return UsuarioDAO_TDS.getUnicaInstancia();
	}

	@Override
	public ContactoIndividualDAO crearContactoIndividualDAO() {
	
		return ContactoIndividualDAO_TDS.getUnicaInstancia();
	}

	@Override
	public GrupoDAO crearGrupoDAO() {

		return GrupoDAO_TDS.getUnicaInstancia();
	}

	@Override
	public MensajeDAO crearMensajeDAO() {

		return MensajeDAO_TDS.getUnicaInstancia();
	}
	

}
