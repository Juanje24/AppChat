package um.tds.appChat.persistencia.InterfacesDAO;

import um.tds.appChat.dominio.Mensaje;

import java.util.List;

public interface MensajeDAO {
	
	Mensaje craete(Mensaje mensaje);
	
	boolean delete(Mensaje mensaje);
	
	void update(Mensaje mensaje);
	
	Mensaje get(int id);
	
	List<Mensaje> getAll();
}
