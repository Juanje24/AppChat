package um.tds.appChat.dominio;

import java.util.Random;

public class ContactoIndividual extends Contacto{
	private Usuario usuario;
    public ContactoIndividual(String nombre, Usuario u) {
		super(nombre,(new Random().nextInt()  + 20001), u.getUrlImagen());
		this.usuario = u;
	}
    
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

    
    
    
}
