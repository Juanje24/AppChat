package um.tds.appChat.persistencia.ClasesDAO;

import java.util.List;
import java.util.Optional;

import um.tds.appChat.dominio.Mensaje;
import um.tds.appChat.persistencia.InterfacesDAO.MensajeDAO;


public class MensajeDAO_TDS implements MensajeDAO {
	@Override
	public Mensaje registrarMensaje(Mensaje mensaje) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrarMensaje(Mensaje mensaje) {
		// TODO Auto-generated method stub

	}

	@Override
	public Mensaje modificarMensaje(Mensaje mensaje) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Mensaje> recuperarMensajePorId(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Mensaje> recuperarTodosMensajes() {
		// TODO Auto-generated method stub
		return null;
	}

}
