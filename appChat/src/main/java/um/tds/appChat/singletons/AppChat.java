package um.tds.appChat.singletons;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;



import um.tds.appChat.dominio.*;
import um.tds.appChat.persistencia.*;
import um.tds.appChat.persistencia.ClasesDAO.PoolDAO;
import um.tds.appChat.persistencia.InterfacesDAO.*;
import um.tds.appChat.ventanas.ActualizacionVistaListener;


public enum AppChat {
	INSTANCE;
	public static final String DAO_TDS = "um.tds.appChat.persistencia.FactoriaDAO_TDS";
	private static final double PREMIUM = 100.0;
	private FactoriaDAO factoriaDAO;
	private UsuarioDAO usuarioDAO;
	private ContactoIndividualDAO contactoIndividualDAO;
	private GrupoDAO grupoDAO;
	private MensajeDAO mensajeDAO;
	private Usuario usuarioActual;
	private RepositorioUsuario repositorioUsuarios;
	private Descuento descuento;
	private Peer peer;
	private Thread peerThread;
	private ActualizacionVistaListener listener;
	private boolean simultaneo=false;
	
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
		peer = new Peer();
		
		
		
		
		
	}

	public void startSimultaneo() {
		simultaneo = true;
		peerThread = new Thread(peer);
		peerThread.start();
	}
	public Mensaje enviarMensajeContacto(Contacto c3, String string, int emoji) {
		Mensaje msj = usuarioActual.sendMensaje( string, emoji, c3);
		mensajeDAO.registrarMensaje(msj);
		if (c3 instanceof ContactoIndividual) {
			contactoIndividualDAO.modificarContactoIndividual((ContactoIndividual) c3);
		} else {
			grupoDAO.modificarGrupo((Grupo) c3);
		}
		
		StringTokenizer strTok = new StringTokenizer(c3.getTelefonoPropio(), " ");
		while ( strTok.hasMoreElements()) { 
			String tlf = (String) strTok.nextElement();
			Usuario uReceptor = repositorioUsuarios.buscarUsuarioPorMovil(tlf).get();
			Optional<ContactoIndividual> cEmisor = uReceptor.getContactoIndividual(usuarioActual.getTelefono());
			if (cEmisor.isPresent()) {
				
				Mensaje msjRecv= uReceptor.recibeMensaje(string, emoji, cEmisor.get());
				mensajeDAO.registrarMensaje(msjRecv);
				contactoIndividualDAO.modificarContactoIndividual(cEmisor.get());
				System.out.println("Se ha encontrado");
			}
			else {
				System.out.println("Se ha tenido que crear el contacto");
				uReceptor.addContactoIndividual(usuarioActual.getTelefono(), usuarioActual);
				ContactoIndividual c = uReceptor.getContactoIndividual(usuarioActual.getTelefono()).get();
				contactoIndividualDAO.registrarContactoIndividual(c);
				cEmisor= Optional.of(c);
				Mensaje msjRecv= uReceptor.recibeMensaje(string, emoji, c);
				mensajeDAO.registrarMensaje(msjRecv);
				contactoIndividualDAO.modificarContactoIndividual(c);
				usuarioDAO.modificarUsuario(uReceptor);
			}
			if (simultaneo) {
				peer.sendMessage(String.valueOf(cEmisor.get().getId()));
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
	
	public List<Mensaje> buscarMensaje(String texto, String numero, String nombreContacto, LocalDateTime fecha) {
		MessageSearchBuilder builder = new MessageSearchBuilder();
		if (!numero.equals("")) {
			builder.setNumero(numero);
		}
		if (!nombreContacto.equals("")) {
			builder.setNombreContacto(nombreContacto);
		}
	    builder.setText(texto);
	    
	   
	    builder.setFecha(fecha);
	    
	    return usuarioActual.searchMensajes(builder);
	}
	public void logout() {
		usuarioActual=null;
		peer.stop();		
	}
	public double getPrecioPremium() {
		return PREMIUM;
	}
	public List<String> getDescuentosAplicables(){
		List<Descuento> todosDescuentos = FactoriaDescuentos.INSTANCE.getAllDescuentos();
		todosDescuentos.removeIf(descuento -> !descuento.aplicaDescuento(usuarioActual));
		return todosDescuentos.stream()
				.map(Descuento::getNombre)
				.collect(Collectors.toList());
		
	}
	public double getPrecioDescontado(String nombreDescuento) {
		descuento = FactoriaDescuentos.INSTANCE.crearDescuento(nombreDescuento);
		return descuento.getPrecio(PREMIUM);
	}
	public double hacerPremium(String nombreDescuento) {
		descuento = FactoriaDescuentos.INSTANCE.crearDescuento(nombreDescuento);
		double precioDescuento = descuento.getPrecio(PREMIUM);
		usuarioActual.setPremium(true);
		usuarioDAO.modificarUsuario(usuarioActual);
		return precioDescuento;
	}

	public boolean isPremium() {
		return usuarioActual.isPremium();
	}
	public void exportarPDF(Contacto c, String pdfPath)  {
		ExportadorPDF exportador = new ExportadorPDF();
		exportador.exportarPDF(c.getMensajes(), c.getNombre(), usuarioActual.getNombre(), pdfPath );
		

	}
	public void actualizarUsuario(Usuario usuario) {
		Usuario usuarioActualizado= usuarioDAO.recuperarUsuarioPorId(usuario.getId());
		repositorioUsuarios.modificarUsuario(usuarioActualizado);
		if (usuarioActual.getTelefono().equals(usuarioActualizado.getTelefono())) {
			usuarioActual = usuarioActualizado;
		}
		
	}
	public void actualizarUsuario(String tlf) {
		Usuario u=repositorioUsuarios.buscarUsuarioPorMovil(tlf).get();
		actualizarUsuario(u);
	}
	public void recibidoMensajeSimultaneo(String message) {
		PoolDAO.resetearPools();
		usuarioActual = usuarioDAO.recuperarUsuarioPorId(usuarioActual.getId());
		ContactoIndividual c = contactoIndividualDAO.recuperarContactoIndividualPorId(Integer.parseInt(message));	
		System.out.println("Numero de contactos: "+usuarioActual.getContactos().size());
		for (Contacto cAux : usuarioActual.getContactos()) {
			System.out.println("Contacto: "+cAux.getNombre()+" mensajes: "+cAux.getMensajes().size());
		}
		listener.actualizarVista(c.getNombre(), c.getNumeroMensajesNoLeidos());
	}

	public void leidoEnPersistencia(Contacto c) {
		for (Mensaje m : c.getMensajes()) {
            mensajeDAO.modificarMensaje(m);
        }
		
	}
	public void marcarMensajesLeidos(Contacto c) {
		c.setLeidos();
		for (Mensaje m : c.getMensajes()) {
			mensajeDAO.modificarMensaje(m);
		}
		if (c instanceof ContactoIndividual) {
			contactoIndividualDAO.modificarContactoIndividual((ContactoIndividual) c);
		} else {
			grupoDAO.modificarGrupo((Grupo) c);
		}
	}

	public void setListener(ActualizacionVistaListener listener) {
		this.listener = listener;
	}

	public void actualizarGrupo(int id, String nombre, List<ContactoIndividual> contactosGrupo, String foto) {
		Grupo g = usuarioActual.actualizarGrupo(id, nombre, contactosGrupo, foto);
		grupoDAO.modificarGrupo(g);
		usuarioDAO.modificarUsuario(usuarioActual);
		
	}
	
}
