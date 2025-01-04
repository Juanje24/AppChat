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
