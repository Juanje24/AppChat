package um.tds.appChat.dominio;

import java.util.Random;

public class ContactoIndividual extends Contacto{

    public ContactoIndividual(String nombre) {
		super(nombre,new Random().nextInt(2001,9999));
		// TODO Auto-generated constructor stub
	}
	private Usuario usuario;
    
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

    
    
    
}
