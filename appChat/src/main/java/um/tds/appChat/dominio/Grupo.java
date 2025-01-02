package um.tds.appChat.dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Grupo extends Contacto{
    
	private List<ContactoIndividual> contactos;
	
    public Grupo(String nombre, List<ContactoIndividual> contactos, String urlImagen) {
    	super(nombre, urlImagen);
    	this.setContactos(new ArrayList<ContactoIndividual>(contactos));
		
	}
    
    @Override
    public List<Mensaje> searchMessages(MessageSearchBuilder builder) {
        return this.getMensajes().stream()
                .filter(mensaje -> builder.getText().isEmpty() || mensaje.getTexto().contains(builder.getText().get()))
                .filter(mensaje -> builder.getNumero().isEmpty() || 
                        Arrays.stream(this.getTelefonoPropio().split(" ")) // Separar teléfonos por espacio
                                .anyMatch(tlf -> tlf.equals(builder.getNumero().get())) || // Comparar con emisor
                        Arrays.stream(this.getTelefonoPropio().split(" ")) 
                                .anyMatch(tlf -> tlf.equals(mensaje.getReceptor())))  // Comparar con receptor
                .filter(mensaje -> builder.getNombreContacto().isEmpty() || 
                        mensaje.getNombreEmisor().equalsIgnoreCase(builder.getNombreContacto().get()) ||
                        this.getNombre().equalsIgnoreCase(builder.getNombreContacto().get()))
                .collect(Collectors.toList());
    }


	public List<ContactoIndividual> getContactos() {
		return contactos;
	}

	public void setContactos(List<ContactoIndividual> contactos) {
		this.contactos = contactos;
	}

	public void addContacto(ContactoIndividual contacto) {
        this.contactos.add(contacto);
    }
	public String getTelefonoPropio() {
		String aux = "";
		for (ContactoIndividual c : contactos) {
			aux += c.getTelefonoPropio() + " ";
		}
		return aux.trim();
	}
	public String getSaludo() {
		return "";
	}


    
    
    
}
