package um.tds.appChat.singletons;

import java.time.LocalDate;

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
		usuarioDAO = factoriaDAO.crearUsuarioDAO();
		contactoIndividualDAO = factoriaDAO.crearContactoIndividualDAO();
		grupoDAO = factoriaDAO.crearGrupoDAO();
		mensajeDAO = factoriaDAO.crearMensajeDAO();
		repositorioUsuarios = RepositorioUsuario.INSTANCE;
	}
	void enviarMensajeContacto(ContactoIndividual c3, String string, int i, TipoMensaje enviado) {
		// TODO Auto-generated method stub
		
	}

	ContactoIndividual agregarContacto(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}

	public void registrarUsuario(String nombre, String apellidos, String contrasena, LocalDate fechaNacimiento, String numTlf, String foto, String saludo) {
		if (repositorioUsuarios.buscarUsuarioPorMovil(numTlf)) {
			return;
		}
		repositorioUsuarios.addUsuario(nombre, apellidos, numTlf, contrasena, fechaNacimiento, saludo, foto);
		usuarioDAO.registrarUsuario(repositorioUsuarios.getUsuarioPorMovil(numTlf));
		
	}

	public boolean login(String nombre, String contrasena) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
