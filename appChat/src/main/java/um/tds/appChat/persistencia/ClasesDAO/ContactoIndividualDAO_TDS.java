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
import um.tds.appChat.dominio.Mensaje;
import um.tds.appChat.dominio.Usuario;
import um.tds.appChat.persistencia.FactoriaDAO;
import um.tds.appChat.persistencia.InterfacesDAO.ContactoIndividualDAO;

public class ContactoIndividualDAO_TDS implements ContactoIndividualDAO{
	
	private static final String NOMBRE = "nombre";
	private static final String MENSAJES = "mensajes";
	private static final String USUARIO = "usuario";
	
	private ServicioPersistencia servicioPersistencia;
	private static ContactoIndividualDAO_TDS unicaInstancia;
	
	public static ContactoIndividualDAO_TDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			unicaInstancia = new ContactoIndividualDAO_TDS();
		}
		return unicaInstancia;
	}

	public ContactoIndividualDAO_TDS() {
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
	
	private List<Mensaje> obtenerMensajesDesdeIDs(String mensajes){
		List<Mensaje> listaMensajes = new LinkedList<Mensaje>();
		StringTokenizer strTok = new StringTokenizer(mensajes, " ");
		while(strTok.hasMoreTokens()) {
			listaMensajes.add(FactoriaDAO.getFactoriaDAO().getMensajeDAO().recuperarMensajePorId(Integer.valueOf((String)strTok.nextElement())));
		}
		return listaMensajes;
	}
	
	private ContactoIndividual entidadToIndividual(Entidad eIndividual) {
		
		String nombre = servicioPersistencia.recuperarPropiedadEntidad(eIndividual, NOMBRE);
		int usuario = Integer.parseInt(servicioPersistencia.recuperarPropiedadEntidad(eIndividual, USUARIO));
		List<Mensaje> mensajes = obtenerMensajesDesdeIDs(servicioPersistencia.recuperarPropiedadEntidad(eIndividual, MENSAJES));
		
		
		Usuario u = FactoriaDAO.getFactoriaDAO().getUsuarioDAO().recuperarUsuarioPorId(usuario);
		
		
		ContactoIndividual individual = new ContactoIndividual(nombre, u);
		individual.setMensajes(mensajes);
		individual.setId(eIndividual.getId());
		
		return individual;
	}
	
	private Entidad IndividualToEntidad(ContactoIndividual individual) {
		Entidad eIndividual = new Entidad();
		eIndividual.setNombre("ContactoIndividual");
		eIndividual.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad(NOMBRE, individual.getNombre()),
						new Propiedad(USUARIO, String.valueOf(individual.getUsuario().getId())),
						new Propiedad(MENSAJES, obtenerIDsMensajes(individual.getMensajes())))));
		return eIndividual;
	}
	
	/////////////////////////
	
	@Override
	public void registrarContactoIndividual(ContactoIndividual contactoIndividual) {
		Entidad eIndividual = null;
		try {
			eIndividual = servicioPersistencia.recuperarEntidad(contactoIndividual.getId());
			
			if (eIndividual != null) {
		        throw new IllegalStateException("El contacto ya existe en el sistema.");
		    }
		} catch (NullPointerException e) {
			eIndividual = null;
		}
		eIndividual = servicioPersistencia.registrarEntidad(IndividualToEntidad(contactoIndividual)) ;
		contactoIndividual.setId(eIndividual.getId());
	}

	@Override
	public void borrarContactoIndividual(ContactoIndividual contactoIndividual) {
		Entidad eIndividual = servicioPersistencia.recuperarEntidad(contactoIndividual.getId());
		servicioPersistencia.borrarEntidad(eIndividual);
		
	}

	@Override
	public void modificarContactoIndividual(ContactoIndividual contactoIndividual) {
		
		Entidad eIndividual = servicioPersistencia.recuperarEntidad(contactoIndividual.getId());
		for (Propiedad prop : eIndividual.getPropiedades()) {
			if (prop.getNombre().equals(NOMBRE)) {
				prop.setValor(contactoIndividual.getNombre());
			}
			else if (prop.getNombre().equals(USUARIO)) {
				prop.setValor(String.valueOf(contactoIndividual.getUsuario().getId()));
			} else if (prop.getNombre().equals(MENSAJES)) {
				prop.setValor(obtenerIDsMensajes(contactoIndividual.getMensajes()));
			}
			servicioPersistencia.modificarPropiedad(prop);
		}
		if (PoolDAO.getUnicaInstancia(1).contiene(contactoIndividual.getId())) {
			PoolDAO.getUnicaInstancia(1).modificarObjeto(contactoIndividual.getId(), contactoIndividual);
		}
	}

	@Override
	public ContactoIndividual recuperarContactoIndividualPorId(int id) {
		
				//Si el objeto está en el pool, se devuelve
				if (PoolDAO.getUnicaInstancia(1).contiene(id)) {
					return (ContactoIndividual) PoolDAO.getUnicaInstancia(1).getObjeto(id);
				}
				//Si no, se recupera de la base de datos
				Entidad eIndividual = servicioPersistencia.recuperarEntidad(id);
                if(eIndividual==null) return null;
                ContactoIndividual c = entidadToIndividual(eIndividual);
                PoolDAO.getUnicaInstancia(1).addObjeto(id, c);
                return c;
	}

	@Override
	public List<ContactoIndividual> recuperarTodosContactoIndividuales() {
		return servicioPersistencia.recuperarEntidades("ContactoIndividual").stream()
				.map(e->entidadToIndividual(e))
				.collect(Collectors.toList());
	}

}
