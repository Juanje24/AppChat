package um.tds.appChat.persistencia.InterfacesDAO;

import java.util.List;
import java.util.Optional;

import um.tds.appChat.dominio.Usuario;

public interface UsuarioDAO {
	
	public Usuario registrarUsuario(Usuario usuario);

	public void borrarUsuario(Usuario usuario);

	public void modificarUsuario(Usuario usuario);

	public Optional<Usuario> recuperarUsuarioPorId(int id);

	public List<Usuario> recuperarTodosUsuarios();
}
