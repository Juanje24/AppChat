package um.tds.appChat.dominio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import tds.BubbleText;

public abstract class Contacto {
    
    private int id;
    private String nombre;
    private List<Mensaje> mensajes;
    private String urlImagen;
    
    // CONSTRUCTORES
    
    public Contacto(String nombre, String urlImagen) {
    	this.nombre = nombre;
    	this.mensajes = new LinkedList<Mensaje>();
    	this.urlImagen = urlImagen;
    }
    
    // FUNCIONALIDAD
   
    public List<Mensaje> searchMessages(MessageSearchBuilder builder) { //revisar para cuando se usa en los grupos
    	return mensajes.stream()
        		.filter(mensaje -> builder.filtrar(mensaje))
                .collect(Collectors.toList());
    }
    public void modificarMensajes(String nuevoNombre) {
    	for (Mensaje mensaje : mensajes) {
    		//Se cambian los nombres de los mensajes que tengan como emisor o receptor el nombre antiguo
			if(mensaje.getEmisor().equals(this.getTelefonoPropio())) {
				mensaje.setNombreEmisor(nuevoNombre);
			}
			else {
				mensaje.setNombreReceptor(nuevoNombre);
			}
    	}
    }

	public int getNumeroMensajesEntreFechas(LocalDateTime inicio, LocalDateTime fin) {
		return (int) mensajes.stream()
				.filter(mensaje -> mensaje.getFecha().isAfter(inicio) && mensaje.getFecha().isBefore(fin.plusDays(1)))
				.count();
	}
    
    
    // GETTERS Y SETTERS

    public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public List<Mensaje> getMensajes() {
		return mensajes;
	}



	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = new LinkedList<Mensaje>(mensajes);
	}

	public Mensaje addMensaje(String texto, int emoji, String tlf,String nombre, int tipo) {
		Mensaje mensaje;
		if (tipo == BubbleText.RECEIVED) {
			mensaje=new Mensaje(texto, emoji, getTelefonoPropio(), tlf,this.nombre, nombre, tipo);
		}
		else {
			mensaje=new Mensaje(texto, emoji, tlf, getTelefonoPropio(),nombre, this.nombre, tipo);
		}
		this.mensajes.add(mensaje);
		return mensaje;
	}
	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public void setLeidos() {
		for (Mensaje mensaje : mensajes) {
			if (mensaje.getTipo() == BubbleText.RECEIVED) {
				mensaje.setLeido(true);
			}
		}
	}

	public int getNumeroMensajesNoLeidos() {
		int noLeidos = 0;
		for (Mensaje mensaje : mensajes) {
			if (mensaje.getTipo() == BubbleText.RECEIVED && !mensaje.isLeido()) {
				noLeidos++;
			}
		}
		return noLeidos;
	}
	
	public abstract String getTelefonoPropio();

	public abstract String getSaludo();
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Contacto) {
			Contacto c = (Contacto) obj;
			return c.getNombre().equals(nombre) && c.getTelefonoPropio().equals(getTelefonoPropio());
		}
		return super.equals(obj);
	}





}
