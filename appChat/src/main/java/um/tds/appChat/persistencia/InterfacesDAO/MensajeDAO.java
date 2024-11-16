package um.tds.appChat.persistencia.InterfacesDAO;

import um.tds.appChat.dominio.Mensaje;
import java.util.List;

public interface MensajeDAO {
	public Mensaje registrarMensaje(Mensaje mensaje);
	public void borrarMensaje(Mensaje mensaje);
	public Mensaje modificarMensaje(Mensaje mensaje);
	public Mensaje recuperarMensajePorId(int id);
	public List<Mensaje> recuperarTodosMensajes();
}
