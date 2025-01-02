package um.tds.appChat.persistencia.ClasesDAO;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import um.tds.appChat.dominio.Mensaje;
import um.tds.appChat.persistencia.InterfacesDAO.MensajeDAO;


public class MensajeDAO_TDS implements MensajeDAO {
	
	private static final String MENSAJE = "Mensaje";
	private static final String TEXTO = "texto";
	private static final String FECHA = "fechaNacimiento";
	private static final String EMISOR = "tlfEmisor";
	private static final String RECEPTOR = "tlfReceptor";
	private static final String NOMBRE_EMISOR = "nombreEmisor";
	private static final String TIPO = "tipo";
	private static final String EMOJI = "emoji";
	
	
	private ServicioPersistencia servicioPersistencia;
	private static MensajeDAO_TDS unicaInstancia = new MensajeDAO_TDS();
	
	public static MensajeDAO_TDS getUnicaInstancia() {
		return unicaInstancia;
	}
	
	public MensajeDAO_TDS() {
		servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	//MÉTODOS PRIVADOS
	
	private Mensaje entidadToMensaje(Entidad eMensaje){
		
		String texto = servicioPersistencia.recuperarPropiedadEntidad(eMensaje, TEXTO);
		LocalDate fecha = LocalDate.parse(servicioPersistencia.recuperarPropiedadEntidad(eMensaje, FECHA), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String emisor = servicioPersistencia.recuperarPropiedadEntidad(eMensaje, EMISOR);
		String receptor = servicioPersistencia.recuperarPropiedadEntidad(eMensaje, RECEPTOR);
		String nombreEmisor = servicioPersistencia.recuperarPropiedadEntidad(eMensaje, NOMBRE_EMISOR);
		int tipo = Integer.parseInt(servicioPersistencia.recuperarPropiedadEntidad(eMensaje, TIPO));
		int emoji = Integer.parseInt(servicioPersistencia.recuperarPropiedadEntidad(eMensaje, EMOJI));
		
		Mensaje mensaje = new Mensaje(texto, emoji, emisor, receptor,nombreEmisor, tipo);
		mensaje.setFecha(fecha);
		mensaje.setId(eMensaje.getId());
		
		return mensaje;
	}
	
	private Entidad mensajeToEntidad(Mensaje mensaje) {
		Entidad eMensaje = new Entidad();
		eMensaje.setNombre(MENSAJE);
		eMensaje.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad(TEXTO, mensaje.getTexto()),
						new Propiedad(EMOJI, String.valueOf(mensaje.getEmoji())),
						new Propiedad(FECHA, mensaje.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
						new Propiedad(EMISOR, mensaje.getEmisor()),
						new Propiedad(RECEPTOR, mensaje.getReceptor()),
						new Propiedad(NOMBRE_EMISOR, mensaje.getNombreEmisor()),
						new Propiedad(TIPO, String.valueOf(mensaje.getTipo())))));
		return eMensaje;
	}
	
	/////////////////////////////
	
	@Override
	public void registrarMensaje(Mensaje mensaje) {
		Entidad eMensaje = null;
		try {
			eMensaje = servicioPersistencia.recuperarEntidad(mensaje.getId());
			
			if (eMensaje != null) {
		        throw new IllegalStateException("El mensaje ya existe en el sistema.");
		    }
		} catch (NullPointerException e) {
			eMensaje = null;
		}

		eMensaje=servicioPersistencia.registrarEntidad(mensajeToEntidad(mensaje));
		mensaje.setId(eMensaje.getId());
		
	}

	@Override
	public void borrarMensaje(Mensaje mensaje) {
		Entidad eMensaje = servicioPersistencia.recuperarEntidad(mensaje.getId());
		servicioPersistencia.borrarEntidad(eMensaje);

	}

	@Override
	public void modificarMensaje(Mensaje mensaje) {
		Entidad eMensaje = servicioPersistencia.recuperarEntidad(mensaje.getId());
		for (Propiedad prop : eMensaje.getPropiedades()) {
			if (prop.getNombre().equals(TEXTO)) {
				prop.setValor(mensaje.getTexto());
			}
			else if (prop.getNombre().equals(EMOJI)) {
				prop.setValor(String.valueOf(mensaje.getEmoji()));
			}
			else if (prop.getNombre().equals(FECHA)) {
				prop.setValor(mensaje.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			} else if (prop.getNombre().equals(EMISOR)) {
				prop.setValor(mensaje.getEmisor());
			} else if (prop.getNombre().equals(RECEPTOR)) {
				prop.setValor(mensaje.getReceptor());
			} else if (prop.getNombre().equals(NOMBRE_EMISOR)) {
                prop.setValor(mensaje.getNombreEmisor());
            } else if (prop.getNombre().equals(TIPO)) {
				prop.setValor(String.valueOf(mensaje.getTipo()));
			} 
			servicioPersistencia.modificarPropiedad(prop);
		}

	}

	@Override
	public Mensaje recuperarMensajePorId(int id) {

		//Si el objeto está en el pool, se devuelve
		if (PoolDAO.getUnicaInstancia(3).contiene(id)) {
			return (Mensaje) PoolDAO.getUnicaInstancia(3).getObjeto(id);
		}
		//Si no, se recupera de la base de datos
		Entidad eMensaje = servicioPersistencia.recuperarEntidad(id);
        if(eMensaje==null) return null;
        Mensaje m = entidadToMensaje(eMensaje);
		
        PoolDAO.getUnicaInstancia(3).addObjeto(id, m);
        return m;
	}

	@Override
	public List<Mensaje> recuperarTodosMensajes() {
		return servicioPersistencia.recuperarEntidades(MENSAJE).stream()
				.map(e -> entidadToMensaje(e))
				.collect(Collectors.toList());
	}

}
