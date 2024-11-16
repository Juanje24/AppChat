package um.tds.appChat.singletons;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import um.tds.appChat.dominio.Usuario;

public enum RepositorioUsuario {
    INSTANCE;

    private Set<Usuario> usuarios = new HashSet<Usuario>();

    public void setUsuarios(Collection<Usuario> setUsuarios) {
		this.usuarios.addAll(setUsuarios);
	}
	
	public void deleteRepositorio() {
		usuarios.clear();
	}
    
    public Usuario creUsuario(String name, String lastName, String telefono, String password, LocalDate birthday, String saludo, String urlImagen){
        return new Usuario(name, lastName, telefono, password, birthday, saludo, urlImagen) ;
    }

    public Usuario creUsuario(String name, String lastName, String telefono, String password, LocalDate birthday, String saludo){
        return new Usuario(name, lastName, telefono, password, birthday, saludo);
    }

    public Usuario Usuario(String name, String lastName, String telefono, String password, String saludo){ //sin imagen y sin fecha
        return new Usuario(name, lastName, telefono, password, null, saludo, null);
    }

    public Usuario Usuario(String name, String lastName, String telefono, String password, String saludo, String urlImagen){ //con imagen, sin fecha
        return new Usuario(name, lastName, telefono, password, null, saludo, urlImagen);
    }

    public List<Usuario> getAllUsuarios(){
		return usuarios.stream().collect(Collectors.toList());
	}

    public void removeUsuario(String email) {
		usuarios.removeIf(usuario -> usuario.getEmail().equals(email));
	}

    private boolean usuarioExistente(Usuario usuario) {
		return searchUsuarioByEmail(usuario.getEmail()).isPresent();
	}

    Optional<um.tds.appChat.dominio.Usuario> searchUsuarioByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addUsuario(Usuario usuario) {
		if(!usuarioExistente(usuario)) {
			usuarios.add(usuario);
			return true;
		} else return false;
	}

    public boolean logUsuario(String usuario, String contraseña) {
		Optional<Usuario> u = searchUsuarioByUsuario(usuario);
		if (u.isPresent()) {
			return u.get().getContraseña().equals(contraseña);
		} else return false;
	}

	Optional<um.tds.appChat.dominio.Usuario> searchUsuarioByUsuario(String usuario) {
		// TODO Auto-generated method stub
		return null;
	}

    //Es posible que se tenga que implementar la actualización de usuarios por git


}
