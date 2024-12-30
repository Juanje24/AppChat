package um.tds.appChat.dominio;


public class ContactoIndividual extends Contacto{
	private String apellidos;
	private Usuario usuario;
	
    public ContactoIndividual(String nombre, Usuario u) {
		super(nombre, u.getUrlImagen());
		this.usuario = u;
		this.apellidos="";
	}
    
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
    public String getTelefonoPropio() {
    	return usuario.getTelefono();
    }
    public String getSaludo() {
    	return usuario.getSaludo();
    }
    
    
}
