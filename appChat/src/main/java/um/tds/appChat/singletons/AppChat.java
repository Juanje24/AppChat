package um.tds.appChat.singletons;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import um.tds.appChat.dominio.*;
import um.tds.appChat.persistencia.*;
import um.tds.appChat.persistencia.InterfacesDAO.*;


public enum AppChat {
	INSTANCE;
	public static final String DAO_TDS = "um.tds.appChat.persistencia.FactoriaDAO_TDS";
	private FactoriaDAO factoriaDAO;
	private UsuarioDAO usuarioDAO;
	private ContactoIndividualDAO contactoIndividualDAO;
	private GrupoDAO grupoDAO;
	private MensajeDAO mensajeDAO;
	private Usuario usuarioActual;
	private RepositorioUsuario repositorioUsuarios;

	private AppChat() {
		try {
			factoriaDAO = FactoriaDAO.getInstancia(DAO_TDS);
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
		usuarioDAO = factoriaDAO.getUsuarioDAO();
		contactoIndividualDAO = factoriaDAO.getContactoIndividualDAO();
		grupoDAO = factoriaDAO.getGrupoDAO();
		mensajeDAO = factoriaDAO.getMensajeDAO();
		repositorioUsuarios = RepositorioUsuario.INSTANCE;
	}
	public void enviarMensajeContacto(Contacto c3, String string, int emoji, int enviado) {
		// TODO Auto-generated method stub
		
	}

	public ContactoIndividual agregarContacto(String nombre, String tlf) {
		Optional<Usuario> contacto = repositorioUsuarios.buscarUsuarioPorMovil(tlf);
		if (contacto.isPresent() && !usuarioActual.isTlfEnContactos(tlf) ) {
			usuarioActual.addContactoIndividual(nombre,contacto.get());
			contactoIndividualDAO.registrarContactoIndividual(usuarioActual.getContactoIndividual(tlf));
			usuarioDAO.modificarUsuario(usuarioActual);
			return usuarioActual.getContactoIndividual(tlf);
		}
		else{
			return null;
		}
	}

	public boolean registrarUsuario(String nombre, String apellidos, String contrasena, LocalDate fechaNacimiento, String numTlf, String foto, String saludo) {

		if (repositorioUsuarios.buscarUsuarioPorMovil(numTlf).isPresent()) {
			return false;
		}
		repositorioUsuarios.addUsuario(nombre, apellidos, numTlf, contrasena, fechaNacimiento, saludo, foto);
		usuarioDAO.registrarUsuario(repositorioUsuarios.buscarUsuarioPorMovil(numTlf).get());
		return true;
	}

	public boolean login(String telefono, String contrasena) {
		if (repositorioUsuarios.buscarUsuarioPorMovil(telefono).isPresent()) {
			usuarioActual = repositorioUsuarios.buscarUsuarioPorMovil(telefono).get();
			return usuarioActual.getContraseña().equals(contrasena);
		}
		return false;
	}
	public void recuperarUsuarios() {
		List<Usuario> usuarios = usuarioDAO.recuperarTodosUsuarios();
		repositorioUsuarios.cargarUsuarios(usuarios);
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	public void actualizarUsuario(String nombre, String apellidos, String contrasena, LocalDate fechaNacimiento, String numTlf, String foto, String saludo) {
			Usuario usuario = repositorioUsuarios.modificarUsuario(nombre, apellidos, numTlf, contrasena, fechaNacimiento, saludo, foto);
			usuarioDAO.modificarUsuario(usuario);
			usuarioActual = usuario;
	}
	

	public Grupo crearGrupo(String nombre, List<ContactoIndividual> contactosGrupo, String foto) {
		Grupo g=usuarioActual.addGrupo(nombre, contactosGrupo,foto);
		//grupoDAO.registrarGrupo(g);
		
		return g;
	}
	public void actualizarNombreContacto(Contacto contacto, String nuevoNombre) {
		usuarioActual.modificarNombreContacto(contacto, nuevoNombre);
		contactoIndividualDAO.modificarContactoIndividual((ContactoIndividual) contacto);
		
	}
	
}
