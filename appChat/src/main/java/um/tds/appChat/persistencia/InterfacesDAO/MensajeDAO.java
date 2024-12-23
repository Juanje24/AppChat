package um.tds.appChat.persistencia.InterfacesDAO;

import um.tds.appChat.dominio.Mensaje;
import java.util.List;

public interface MensajeDAO {
	
	public void registrarMensaje(Mensaje mensaje);

	public void borrarMensaje(Mensaje mensaje);

	public void modificarMensaje(Mensaje mensaje);

	public Mensaje recuperarMensajePorId(int id);

	public List<Mensaje> recuperarTodosMensajes();
}
