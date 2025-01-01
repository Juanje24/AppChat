package um.tds.appChat.dominio;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
                .filter(mensaje -> builder.getText().isEmpty() || mensaje.getTexto().contains(builder.getText().get()))
                .filter(mensaje -> builder.getNumero().isEmpty() || mensaje.getReceptor().equals(builder.getNumero().get()))
                .filter(mensaje -> builder.getNombreContacto().isEmpty() || nombre.equalsIgnoreCase(builder.getNombreContacto().get()))
                .collect(Collectors.toList());
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

	public Mensaje addMensaje(String texto, int emoji, String tlfEmisor,String nombreEmisor, int tipo) {
		Mensaje mensaje = new Mensaje(texto, emoji, tlfEmisor, getTelefonoPropio(),nombreEmisor, tipo);
		this.mensajes.add(mensaje);
		return mensaje;
	}
	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
	public abstract String getTelefonoPropio();

	public abstract String getSaludo();





}
