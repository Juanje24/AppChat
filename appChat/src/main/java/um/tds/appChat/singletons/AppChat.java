package um.tds.appChat.singletons;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

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
	public Mensaje enviarMensajeContacto(Contacto c3, String string, int emoji) {
		Mensaje msj = usuarioActual.sendMensaje( string, emoji, c3);
		mensajeDAO.registrarMensaje(msj);
		if (c3 instanceof ContactoIndividual) {
			contactoIndividualDAO.modificarContactoIndividual((ContactoIndividual) c3);
		} else {
			grupoDAO.modificarGrupo((Grupo) c3);
		}
		//Falta la parte inversa, buscar en el usuario asociado a c3 el contacto con tlf=usuarioActual.getTlf() y añadir el mensaje
		StringTokenizer strTok = new StringTokenizer(c3.getTelefonoPropio(), " ");
		while ( strTok.hasMoreElements()) { 
			String tlf = (String) strTok.nextElement();
			Usuario uReceptor = repositorioUsuarios.buscarUsuarioPorMovil(tlf).get();
			Optional<ContactoIndividual> cEmisor = uReceptor.getContactoIndividual(usuarioActual.getTelefono());
			if (cEmisor.isPresent()) {
				Mensaje msjRecv= uReceptor.recibeMensaje(string, emoji,usuarioActual.getTelefono(),cEmisor.get().getNombre(), cEmisor.get());
				mensajeDAO.registrarMensaje(msjRecv);
				contactoIndividualDAO.modificarContactoIndividual(cEmisor.get());
			}
			else {
				uReceptor.addContactoIndividual(usuarioActual.getTelefono(), usuarioActual);
				ContactoIndividual c = uReceptor.getContactoIndividual(usuarioActual.getTelefono()).get();
				contactoIndividualDAO.registrarContactoIndividual(c);
				Mensaje msjRecv= uReceptor.recibeMensaje(string, emoji,usuarioActual.getTelefono(),c.getNombre(), c);
				mensajeDAO.registrarMensaje(msjRecv);
				contactoIndividualDAO.modificarContactoIndividual(c);
				usuarioDAO.modificarUsuario(uReceptor);
			}
			
			
		}
		return msj;		
}

	public ContactoIndividual agregarContacto(String nombre, String tlf) {
		Optional<Usuario> contacto = repositorioUsuarios.buscarUsuarioPorMovil(tlf);
		if (contacto.isPresent() && !usuarioActual.isTlfEnContactos(tlf) ) {
			usuarioActual.addContactoIndividual(nombre,contacto.get());
			contactoIndividualDAO.registrarContactoIndividual(usuarioActual.getContactoIndividual(tlf).get());
			usuarioDAO.modificarUsuario(usuarioActual);
			return usuarioActual.getContactoIndividual(tlf).get();
		}
		else{
			return null;
		}
	}
	public Grupo crearGrupo(String nombre, List<ContactoIndividual> contactosGrupo, String foto) {
		Grupo g=usuarioActual.addGrupo(nombre, contactosGrupo,foto);
		grupoDAO.registrarGrupo(g);
		usuarioDAO.modificarUsuario(usuarioActual);
		return g;
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
	

		public void actualizarNombreContacto(Contacto contacto, String nuevoNombre) {
		contacto.modificarMensajes(nuevoNombre);
		usuarioActual.modificarNombreContacto(contacto, nuevoNombre);
		contacto.getMensajes().forEach(mensaje -> mensajeDAO.modificarMensaje(mensaje));
		contactoIndividualDAO.modificarContactoIndividual((ContactoIndividual) contacto);
		
	}

	public void eliminarContacto(Contacto contacto) {
		List<Mensaje> mensajes = contacto.getMensajes();
		usuarioActual.eliminarContacto(contacto);
		for (Mensaje mensaje : mensajes) {
			mensajeDAO.borrarMensaje(mensaje);
		}
		contactoIndividualDAO.borrarContactoIndividual((ContactoIndividual) contacto);
		usuarioDAO.modificarUsuario(usuarioActual);
	}
	
	public List<Mensaje> buscarMensaje(String texto, String numero, String nombreContacto, LocalDate fecha) {
		MessageSearchBuilder builder = new MessageSearchBuilder();
		
	    builder.setText(texto);
	    builder.setNumero(numero);
	    builder.setNombreContacto(nombreContacto);
	    builder.setFecha(fecha);
	    
	    return usuarioActual.searchMensajes(builder);
	}
		
	
	
}
