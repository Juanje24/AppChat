package um.tds.appChat.persistencia.ClasesDAO;

import um.tds.appChat.dominio.Contacto;
import um.tds.appChat.dominio.Usuario;
import um.tds.appChat.persistencia.InterfacesDAO.UsuarioDAO;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;
import um.tds.appChat.persistencia.FactoriaDAO;
import um.tds.appChat.persistencia.UtilsUsuarioDAO;


import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;


public class UsuarioDAO_TDS implements UsuarioDAO {
	private ServicioPersistencia servicioPersistencia;
	private static UsuarioDAO_TDS unicaInstancia;
	
	public UsuarioDAO_TDS() {
		servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	private String obtenerIDsContactos(List<Contacto> contactos) {
		String corte = "";
		for (Contacto contacto : contactos) {
			corte += contacto.getId() + " ";
		}
		return corte.trim();
	}
	
	private List<Contacto> obtenerContactosDesdeIDs(String idsContactos){
		List<Contacto> contactos = new LinkedList<Contacto>();
		StringTokenizer strTok = new StringTokenizer(idsContactos, " ");
		while ( strTok.hasMoreElements()) {
			contactos.add(FactoriaDAO.getFactoriaDAO().)
		}
	}
	
	@Override
	public Usuario registrarUsuario(Usuario usuario) {
		Entidad eUsuario = null;
		try {
			eUsuario = servicioPersistencia.recuperarEntidad(usuario.getId());
		} catch (NullPointerException e) {
			eUsuario = null;
		}
		if(eUsuario!=null) {
			return UtilsUsuarioDAO.entidadToUsuario(eUsuario);
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
						new Propiedad("birthday", usuario.getBirthday().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
						new Propiedad("saludo", usuario.getSaludo()),
						new Propiedad("urlImagen", usuario.getUrlImagen()))));
						new Propiedad("contactos", obtenerIDsContactos(usuario.getContactos()));
	   eUsuario = servicioPersistencia.registrarEntidad(eUsuario);
	   usuario.setId(eUsuario.getId());
	   return UtilsUsuarioDAO.entidadToUsuario(eUsuario);
	}

	@Override
	public void borrarUsuario(Usuario usuario) {
		Entidad eUsurio;
		eUsurio = servicioPersistencia.recuperarEntidad(usuario.getId());
		FactoriaDAO.getFactoriaDAO().
		
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		Entidad eUsuario = servicioPersistencia.recuperarEntidad(usuario.getId());

	    eUsuario.getPropiedades().stream().forEach(prop -> {
	        if (prop.getNombre().equals("name")) {
	            prop.setValor(usuario.getNombre());
	        } else if (prop.getNombre().equals("apellido")) {
	            prop.setValor(usuario.getApellido());
	        } else if (prop.getNombre().equals("contraseña")) {
	            prop.setValor(usuario.getContraseña());
	        } else if (prop.getNombre().equals("telefono")) {
	            prop.setValor(usuario.getTelefono());
	        } else if (prop.getNombre().equals("premium")) {
	            prop.setValor(String.valueOf(usuario.isPremium()));
	        } else if (prop.getNombre().equals("birthday")) {
	            prop.setValor(usuario.getBirthday().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	        } else if (prop.getNombre().equals("saludo")) {
	            prop.setValor(usuario.getSaludo());
	        } else if (prop.getNombre().equals("urlImagen")) {
	            prop.setValor(usuario.getUrlImagen());
	        } else if (prop.getNombre().equals("contactos")) {
	        	String contactos = obtenerIDsContactos(usuario.getContactos());
	        	prop.setValor(contactos);
	        }
	        servicioPersistencia.modificarPropiedad(prop);
	    });
	}

	@Override
	public Optional<Usuario> recuperarUsuarioPorId(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
