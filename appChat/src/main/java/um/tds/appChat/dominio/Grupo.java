package um.tds.appChat.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grupo extends Contacto{
    
	private List<ContactoIndividual> contactos;
	
    public Grupo(String nombre, List<ContactoIndividual> contactos, String urlImagen) {
    	super(nombre,new Random().nextInt(20000), urlImagen);
    	this.setContactos(new ArrayList<ContactoIndividual>(contactos));
		// TODO Auto-generated constructor stub
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
		return "";
	}


    
    
    
}
