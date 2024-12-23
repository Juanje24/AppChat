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
    
    public void addUsuario(String name, String lastName, String telefono, String password, LocalDate birthday, String saludo, String urlImagen){
        usuarios.add( new Usuario(name, lastName, telefono, password, birthday, saludo, urlImagen)) ;
        
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
    
    public Optional<Usuario> getUsuarioById(int id) {
    	Optional<Usuario> usuario = usuarios.stream()
    			.filter(u -> u.getId() == id)
    			.findFirst();
    	return usuario;
    }

//    public void removeUsuario(String email) {
//		usuarios.removeIf(usuario -> usuario.getEmail().equals(email));
//	}

//    private boolean usuarioExistente(Usuario usuario) {
//		return searchUsuarioByTelefono(usuario.getTelefono()).isPresent();
//	}

    //public Optional<Usuario> searchUsuarioByEmail(String email) {

    Optional<um.tds.appChat.dominio.Usuario> searchUsuarioByTelefono(String telefono) {
		Optional<Usuario> usuario = usuarios.stream()
				.filter(u -> u.getTelefono() == telefono)
				.findFirst();
		return usuario;
    }


	public boolean addUsuario(Usuario usuario) {
		if(!buscarUsuarioPorMovil(usuario.getTelefono())) {
			usuarios.add(usuario);
			return true;
		} else return false;
	}

	
	public boolean containsUsuario(int id) {
		return usuarios.stream().anyMatch(usuario -> usuario.getId() == id);
	}
	

	public boolean buscarUsuarioPorMovil(String numTlf) {
		return usuarios.stream().anyMatch(usuario -> usuario.getTelefono().equals(numTlf));
	}

	public Usuario getUsuarioPorMovil(String numTlf) {
		return usuarios.stream().
				filter(usuario -> usuario.getTelefono().equals(numTlf)).
				findFirst().get();
	}



}
