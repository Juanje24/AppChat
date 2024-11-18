package um.tds.appChat.persistencia;

import java.util.List;

import um.tds.appChat.dominio.Usuario;

public interface UsuarioDAO {

	Usuario craete(Usuario u);
	
	boolean delete(Usuario u);
	
	void update(Usuario u);
	
	Usuario get(int id);
	
	List<Usuario> getAll();
}
