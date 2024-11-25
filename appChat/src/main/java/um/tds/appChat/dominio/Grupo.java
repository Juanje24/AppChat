package um.tds.appChat.dominio;

import java.util.Set;

public class Grupo extends Contacto{
    
    public Grupo(String nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}

	private Set<Usuario> miembros;

	public Set<Usuario> getMiembros() {
		return miembros;
	}

	public void setMiembros(Set<Usuario> miembros) {
		this.miembros = miembros;
	}
    
    
    
    
}
