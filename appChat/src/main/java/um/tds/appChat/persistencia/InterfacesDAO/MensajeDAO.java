package um.tds.appChat.persistencia.InterfacesDAO;

import um.tds.appChat.dominio.Mensaje;

import java.util.List;
import java.util.Optional;

public interface MensajeDAO {
	
	public Mensaje registrarMensaje(Mensaje mensaje);

	public void borrarMensaje(Mensaje mensaje);

	public Mensaje modificarMensaje(Mensaje mensaje);

	public Optional<Mensaje> recuperarMensajePorId(int id);

	public List<Mensaje> recuperarTodosMensajes();
}
