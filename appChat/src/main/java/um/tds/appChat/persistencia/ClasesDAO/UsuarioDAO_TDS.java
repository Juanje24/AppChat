package um.tds.appChat.persistencia.ClasesDAO;

import um.tds.appChat.dominio.*;
import um.tds.appChat.persistencia.FactoriaDAO;
import um.tds.appChat.persistencia.InterfacesDAO.*;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


public class UsuarioDAO_TDS implements UsuarioDAO {
	private ServicioPersistencia servicioPersistencia;
	private static UsuarioDAO_TDS unicaInstancia = new UsuarioDAO_TDS();
	
	
	public static UsuarioDAO_TDS getUnicaInstancia() { // patron singleton
		return unicaInstancia;
	}
	
	public UsuarioDAO_TDS() {
		servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
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
				Arrays.asList(new Propiedad("nombre", usuario.getNombre()),
						new Propiedad("apellido", usuario.getApellido()),
						new Propiedad("contraseña", usuario.getContraseña()),
						new Propiedad("telefono", usuario.getTelefono()),
						new Propiedad("premium", String.valueOf(usuario.isPremium())),
						new Propiedad("birthday", usuario.getBirthday().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
						new Propiedad("saludo", usuario.getSaludo()),
						new Propiedad("urlImagen", usuario.getUrlImagen()),
		                new Propiedad("contactos", obtenerCodigosContactos(usuario.getContactos())))));
						System.out.println("lista ids contactos: "+obtenerCodigosContactos(usuario.getContactos()));
	   eUsuario = servicioPersistencia.registrarEntidad(eUsuario);
	   usuario.setId(eUsuario.getId());
	   System.out.println("Registrado usuario con id: "+usuario.getId()); 
	}

	@Override
	public void borrarUsuario(Usuario usuario) {
		Entidad eUsuario = servicioPersistencia.recuperarEntidad(usuario.getId());
		servicioPersistencia.borrarEntidad(eUsuario);		
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		Entidad eUsuario = servicioPersistencia.recuperarEntidad(usuario.getId());
		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals("nombre")) {
				prop.setValor(usuario.getNombre());
			}
			else if (prop.getNombre().equals("apellido")) {
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
				prop.setValor(obtenerCodigosContactos(usuario.getContactos()));
				System.out.println("lista ids contactos modificada: "+obtenerCodigosContactos(usuario.getContactos()));
			}
			servicioPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public Usuario recuperarUsuarioPorId(int id) {
		//Si el objeto está en el pool, se devuelve
		if (PoolDAO.getUnicaInstancia(1).contiene(id)) {
			return (Usuario) PoolDAO.getUnicaInstancia(1).getObjeto(id);
		}
		//Si no, se recupera de la base de datos
		Entidad eUsuario = servicioPersistencia.recuperarEntidad(id);
		if (eUsuario == null) return null;
		String nombre = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		String apellido = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, "apellido");
		String contraseña = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, "contraseña");
		String telefono = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, "telefono");
		boolean premium = Boolean.parseBoolean(servicioPersistencia.recuperarPropiedadEntidad(eUsuario, "premium"));
		LocalDate birthday = LocalDate.parse(servicioPersistencia.recuperarPropiedadEntidad(eUsuario, "birthday"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String saludo = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, "saludo");
		String urlImagen = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, "urlImagen");
		
		Usuario usuario = new Usuario( nombre,apellido, telefono, contraseña,  birthday, saludo, urlImagen,premium);
		PoolDAO.getUnicaInstancia(1).addObjeto(id, usuario);
		System.out.println("lista ids contactos recuperada: "+servicioPersistencia.recuperarPropiedadEntidad(eUsuario, "contactos"));
		List<Contacto> contactos = obtenerContactosDesdeIDs(servicioPersistencia.recuperarPropiedadEntidad(eUsuario, "contactos"));
		usuario.setId(id);
		usuario.setContactos(contactos);
		return usuario;
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		ArrayList<Entidad> eUsuarios = servicioPersistencia.recuperarEntidades("Usuario"); 
		System.out.println("Número de usuarios recuperados: "+eUsuarios.size());
		List<Usuario> usuarios = new LinkedList<Usuario>();
		for (Entidad eUsuario : eUsuarios) {
			usuarios.add(recuperarUsuarioPorId(eUsuario.getId()));
		}
		return usuarios;
	}

	
	//Funciones auxiliares para generar la propiedad con los códigos de los Contactos
	private String obtenerCodigosContactos(List<Contacto> listaContactos) {
		String aux = "";
		for (Contacto c : listaContactos) {
			aux += c.getId() + " ";
		}
		return aux.trim();
	}

//	private List<Contacto> obtenerContactosDesdeCodigos(String contactos) {
//
//		List<Contacto> listaContactos = new LinkedList<Contacto>();
//		StringTokenizer strTok = new StringTokenizer(contactos, " ");
//		ContactoIndividualDAO_TDS adaptadorC = new ContactoIndividualDAO_TDS();
//		GrupoDAO_TDS adaptadorG = new GrupoDAO_TDS();
//		while (strTok.hasMoreTokens()) {
//			int id = Integer.valueOf((String) strTok.nextElement());
//			if (id<=20000) { // Esto está mal, no se puede hacer así
//				listaContactos.add(adaptadorG.recuperarGrupoPorId(id));
//				
//			}
//			else {
//				listaContactos.add(adaptadorC.recuperarContactoIndividualPorId(id));
//			}
//		}
//		return listaContactos;
//	}
	
	private List<Contacto> obtenerContactosDesdeIDs(String idsContactos){
		List<Contacto> contactos = new LinkedList<Contacto>();
		StringTokenizer strTok = new StringTokenizer(idsContactos, " ");
		while ( strTok.hasMoreElements()) { //problema con esto dado que no se que tipo de contacto es
			int id = Integer.parseInt((String) strTok.nextElement());
			Entidad eContacto = servicioPersistencia.recuperarEntidad(id);
	        String tipo = eContacto.getNombre();
	        if ("Grupo".equals(tipo)) {
	            contactos.add(FactoriaDAO.getFactoriaDAO().getGrupoDAO().recuperarGrupoPorId(id));
	        	FactoriaDAO.getFactoriaDAO().getGrupoDAO().recuperarGrupoPorId(id).addContacto(null);
	        } else if ("ContactoIndividual".equals(tipo)) {
	        	contactos.add(FactoriaDAO.getFactoriaDAO().getContactoIndividualDAO().recuperarContactoIndividualPorId(id));
	            
	        }
		}
		return contactos;
	}

}
