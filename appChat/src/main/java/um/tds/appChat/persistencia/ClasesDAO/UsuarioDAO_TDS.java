package um.tds.appChat.persistencia.ClasesDAO;

import um.tds.appChat.dominio.Usuario;
import um.tds.appChat.persistencia.InterfacesDAO.UsuarioDAO;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UsuarioDAO_TDS implements UsuarioDAO {
	private ServicioPersistencia servicioPersistencia;
	private SimpleDateFormat sdf ;
	private static UsuarioDAO_TDS unicaInstancia;
	
	public UsuarioDAO_TDS() {
		servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}
	public static UsuarioDAO getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new UsuarioDAO_TDS();
		return unicaInstancia;
	}
	
	@Override
	public void registrarUsuario(Usuario usuario) {
		Entidad eUsuario = null;
		try {
			eUsuario = servicioPersistencia.recuperarEntidad(usuario.getId());
		} catch (NullPointerException e) {
			eUsuario = null;
		}
		if(eUsuario!=null) {
			return;
			}
		//registrar Contactos,grupos y mensajes
		eUsuario = new Entidad();
		eUsuario.setNombre("Usuario");
		eUsuario.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("name", usuario.getNombre()),
						new Propiedad("apellido", usuario.getApellido()),
						new Propiedad("contraseña", usuario.getContraseña()),
						new Propiedad("telefono", usuario.getTelefono()),
						new Propiedad("premium", String.valueOf(usuario.isPremium())),
						new Propiedad("birthday", sdf.format(usuario.getBirthday())),
						new Propiedad("saludo", usuario.getSaludo()),
						new Propiedad("urlImagen", usuario.getUrlImagen()))));
	   eUsuario = servicioPersistencia.registrarEntidad(eUsuario);
	   usuario.setId(eUsuario.getId());
	}

	@Override
	public void borrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario modificarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario recuperarUsuarioPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
