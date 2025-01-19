package um.tds.appChat.singletons;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import um.tds.appChat.dominio.Usuario;

public enum RepositorioUsuario {
    INSTANCE;

    private Set<Usuario> usuarios = new HashSet<Usuario>();


	
	public void deleteRepositorio() {
		usuarios.clear();
	}
    
    public void addUsuario(String name, String lastName, String telefono, String password, LocalDate birthday, String saludo, String urlImagen){
        if(!buscarUsuarioPorMovil(telefono).isPresent()) {
        	usuarios.add( new Usuario(name, lastName, telefono, password, birthday, saludo, urlImagen)) ;
        }        
    }

	public Usuario modificarUsuario(String name, String lastName, String telefono, String password, LocalDate birthday,
			String saludo, String urlImagen) {
			Usuario usuario = buscarUsuarioPorMovil(telefono).get();
			
			Usuario usuarioModificado = new Usuario(name, lastName, telefono, password, birthday, saludo, urlImagen, usuario.getId(), usuario.isPremium(), usuario.getContactos());
			usuarios.remove(usuario);
			usuarios.add(usuarioModificado);
			return usuarioModificado;
	
	}
	public Usuario modificarUsuario(Usuario u) {
			Usuario usuario = buscarUsuarioPorMovil(u.getTelefono()).get();
			usuarios.remove(usuario);
			usuarios.add(u);
			return u;
	
	}
 
	public void cargarUsuarios(Collection<Usuario> usuarios) {
		this.usuarios.clear();
		this.usuarios.addAll(usuarios);
	}
    //El repositorio es el que crea los usuarios nuevos, ya que tiene la colección de usuarios
    
    public List<Usuario> getAllUsuarios(){
		return usuarios.stream().collect(Collectors.toList());
	}
    
    public Optional<Usuario> getUsuarioById(int id) {
    	return usuarios.stream()
    			.filter(u -> u.getId() == id)
    			.findFirst();
    	
    }

	
	public boolean containsUsuario(int id) {
		return usuarios.stream().anyMatch(usuario -> usuario.getId() == id);
	}
	

	public Optional<Usuario> buscarUsuarioPorMovil(String numTlf) {
		return usuarios.stream().
				filter(usuario -> usuario.getTelefono().equals(numTlf)).
				findFirst();
	}
	public Optional<Usuario> buscarUsuarioPorNombre(String nombre) {
		return usuarios.stream().
				filter(usuario -> usuario.getNombre().equals(nombre)).
				findFirst();
	}



}
