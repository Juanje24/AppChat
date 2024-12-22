package um.tds.appChat.dominio;

public class ContactoIndividual extends Contacto{

    public ContactoIndividual(String nombre) {
		super(nombre);
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
