package um.tds.appChat.persistencia.InterfacesDAO;

import java.util.List;

import um.tds.appChat.dominio.Usuario;

public interface UsuarioDAO {

	Usuario craete(Usuario u);
	
	boolean delete(Usuario u);
	
	void update(Usuario u);
	
	Usuario get(int id);
	
	List<Usuario> getAll();
	
//	public void registrarUsuario(Usuario usuario);
//	public void borrarUsuario(Usuario usuario);
//	public Usuario modificarUsuario(Usuario usuario);
//	public Usuario recuperarUsuarioPorId(int id);
//	public List<Usuario> recuperarTodosUsuarios();
}
