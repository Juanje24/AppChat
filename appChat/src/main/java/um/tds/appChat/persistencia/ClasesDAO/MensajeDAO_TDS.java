package um.tds.appChat.persistencia.ClasesDAO;

import java.util.List;
import um.tds.appChat.dominio.Mensaje;
import um.tds.appChat.persistencia.InterfacesDAO.MensajeDAO;


public class MensajeDAO_TDS implements MensajeDAO {
	
	private static MensajeDAO_TDS unicaInstancia = new MensajeDAO_TDS();
	
	public static MensajeDAO_TDS getUnicaInstancia() {
		return unicaInstancia;
	}
	
	@Override
	public void registrarMensaje(Mensaje mensaje) {
		// TODO Auto-generated method stub
	}

	@Override
	public void borrarMensaje(Mensaje mensaje) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarMensaje(Mensaje mensaje) {
		// TODO Auto-generated method stub

	}

	@Override
	public Mensaje recuperarMensajePorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mensaje> recuperarTodosMensajes() {
		// TODO Auto-generated method stub
		return null;
	}

}
