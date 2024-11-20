package um.tds.appChat.persistencia;

import beans.Entidad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import um.tds.appChat.dominio.Usuario;
import um.tds.appChat.singletons.RepositorioUsuario;

public class UsuarioDAO_TDS {
	
	private static final String USUARIO = "usuario";
	private static final String NOMBRE = "nombre";
	private static final String APELLIDO = "apellido";
	private static final String CONTRASEÑA = "contraseña";
	private static final String TELEFONO = "telefono";
	private static final String PREMIUM = "premium";
	private static final String BIRTHDAY = "birthday";
	private static final String SALUDO = "saludo";
	private static final String URLIMAGEN = "urlimagen";
	
	private ServicioPersistencia servPersistencia;
	
	public UsuarioDAO_TDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	private Usuario entidadToUsuario(Entidad eUsuario) {
		if (RepositorioUsuario.INSTANCE.containsUsuario(id))
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String apellido = servPersistencia.recuperarPropiedadEntidad(eUsuario, APELLIDO);
		String contraseña = servPersistencia.recuperarPropiedadEntidad(eUsuario, CONTRASEÑA);
		String telefono = servPersistencia.recuperarPropiedadEntidad(eUsuario, TELEFONO);
		String premium = servPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM);
		//String birthday = servPersistencia.recuperarPropiedadEntidad(eUsuario, BIRTHDAY);
		String saludo = servPersistencia.recuperarPropiedadEntidad(eUsuario, SALUDO);
		String urlImagen = servPersistencia.recuperarPropiedadEntidad(eUsuario, URLIMAGEN);
		Usuario usuario = new Usuario(nombre, apellido, telefono, contraseña, saludo, urlImagen);
		usuario.setId(eUsuario.getId());
		usuario.setPremium(Boolean.valueOf(premium));
		return usuario;
	}
	
	private Entidad usuarioToEntidad(Usuario usuario) {
		Entidad eUsuario = new Entidad();
		eUsuario.setNombre(USUARIO);
	}
	

}
