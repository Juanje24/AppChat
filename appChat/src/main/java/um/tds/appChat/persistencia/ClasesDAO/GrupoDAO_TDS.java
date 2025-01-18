package um.tds.appChat.persistencia.ClasesDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import um.tds.appChat.dominio.ContactoIndividual;
import um.tds.appChat.dominio.Grupo;
import um.tds.appChat.dominio.Mensaje;
import um.tds.appChat.persistencia.FactoriaDAO;
import um.tds.appChat.persistencia.InterfacesDAO.GrupoDAO;

public class GrupoDAO_TDS implements GrupoDAO {
	
	private static final String NOMBRE = "nombre";
	private static final String MENSAJES = "mensajes";
	private static final String CONTACTOS = "contactos";
	private static final String URLIMAGEN = "urlImagen";
	
	private ServicioPersistencia servicioPersistencia;
	private static GrupoDAO_TDS unicaInstancia;
	
	
	
	public static GrupoDAO_TDS getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new GrupoDAO_TDS();
		}
		return unicaInstancia;
	}
	
	public GrupoDAO_TDS() {
		servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	//METODOS PRIVADOS
	
	private String obtenerIDsMensajes(List<Mensaje> mensajes) {
		String aux = "";
		for (Mensaje mensaje : mensajes) {
			aux += mensaje.getId() + " ";
		}
		return aux.trim();
	}
	
	private String obtenerIDsContactos(List<ContactoIndividual> contactos) {
		String aux = "";
		for (ContactoIndividual contacto : contactos) {
			aux += contacto.getId() + " ";
		}
		return aux.trim();
	}
	
	private List<Mensaje> obtenerMensajesDesdeIDs(String mensajes){
		List<Mensaje> listaMensajes = new LinkedList<Mensaje>();
		StringTokenizer strTok = new StringTokenizer(mensajes, " ");
		while(strTok.hasMoreTokens()) {
			listaMensajes.add(FactoriaDAO.getFactoriaDAO().getMensajeDAO().recuperarMensajePorId(Integer.valueOf((String)strTok.nextElement())));
		}
		return listaMensajes;
	}
	
	private List<ContactoIndividual> obtenerContactosDesdeIDs(String contactos){
		List<ContactoIndividual> listaContactos = new LinkedList<ContactoIndividual>();
		StringTokenizer strTok = new StringTokenizer(contactos, " ");
		while(strTok.hasMoreTokens()) {
			listaContactos.add(FactoriaDAO.getFactoriaDAO().getContactoIndividualDAO().recuperarContactoIndividualPorId(Integer.valueOf((String)strTok.nextElement())));
		}
		return listaContactos;
	}
	
	private Grupo entidadToGrupo(Entidad eGrupo) {
		String nombre = servicioPersistencia.recuperarPropiedadEntidad(eGrupo, NOMBRE);
		String urlImagen = servicioPersistencia.recuperarPropiedadEntidad(eGrupo, URLIMAGEN);
		List<Mensaje> mensajes = obtenerMensajesDesdeIDs(servicioPersistencia.recuperarPropiedadEntidad(eGrupo, MENSAJES));
		List<ContactoIndividual> contactos = obtenerContactosDesdeIDs(servicioPersistencia.recuperarPropiedadEntidad(eGrupo, CONTACTOS));
		Grupo grupo = new Grupo(nombre, contactos, urlImagen);
		grupo.setMensajes(mensajes);
		grupo.setId(eGrupo.getId());
		
		return grupo;
	}
	
	private Entidad GrupoToEntidad(Grupo grupo) {
		Entidad eGrupo = new Entidad();
		eGrupo.setNombre("Grupo");
		eGrupo.setPropiedades(new ArrayList<Propiedad>(
			Arrays.asList(new Propiedad(NOMBRE, grupo.getNombre()),
					new Propiedad(URLIMAGEN, grupo.getUrlImagen()),
					new Propiedad(MENSAJES, obtenerIDsMensajes(grupo.getMensajes())),
					new Propiedad(CONTACTOS, obtenerIDsContactos(grupo.getContactos()))))
		);
		return eGrupo;
	}
	
	@Override
	public void registrarGrupo(Grupo grupo) {
		Entidad eGrupo = null;
		try {
			eGrupo = servicioPersistencia.recuperarEntidad(grupo.getId());
			
			if (eGrupo != null) {
		        throw new IllegalStateException("El grupo ya existe en el sistema.");
		    }
		} catch (NullPointerException e) {
			eGrupo = null;
		}
		
		eGrupo = servicioPersistencia.registrarEntidad(GrupoToEntidad(grupo));
		grupo.setId(eGrupo.getId());
		
	}

	@Override
	public void borrarGrupo(Grupo grupo) {
		Entidad eGrupo = servicioPersistencia.recuperarEntidad(grupo.getId());
		servicioPersistencia.borrarEntidad(eGrupo);
	}

	@Override
	public void modificarGrupo(Grupo grupo) {
		Entidad eGrupo = servicioPersistencia.recuperarEntidad(grupo.getId());
		for (Propiedad prop : eGrupo.getPropiedades()) {
			if(prop.getNombre().equals(NOMBRE)) {
				prop.setValor(grupo.getNombre());
			} else if (prop.getNombre().equals(URLIMAGEN)) {
				prop.setValor(grupo.getUrlImagen());
			} else if(prop.getNombre().equals(MENSAJES)) {
				prop.setValor(obtenerIDsMensajes(grupo.getMensajes()));
			} else if(prop.getNombre().equals(CONTACTOS)) {
				prop.setValor(obtenerIDsContactos(grupo.getContactos()));
			}
			servicioPersistencia.modificarPropiedad(prop);
			if (PoolDAO.getUnicaInstancia(2).contiene(grupo.getId())) {
				PoolDAO.getUnicaInstancia(2).modificarObjeto(grupo.getId(), grupo);
			}
		}
	}

	@Override
	public Grupo recuperarGrupoPorId(int id) {
		//Si el objeto está en el pool, se devuelve
		if (PoolDAO.getUnicaInstancia(2).contiene(id)) {
			return (Grupo) PoolDAO.getUnicaInstancia(2).getObjeto(id);
		}
		Entidad eGrupo = servicioPersistencia.recuperarEntidad(id);
		if(eGrupo == null) return null;
		Grupo grupo = entidadToGrupo(eGrupo);
		PoolDAO.getUnicaInstancia(2).addObjeto(id, grupo);
		
		return grupo;
	}

	@Override
	public List<Grupo> recuperarTodosGrupos() {
		return servicioPersistencia.recuperarEntidades("Grupo").stream()
				.map(e -> entidadToGrupo(e))
				.collect(Collectors.toList());
	}

}
