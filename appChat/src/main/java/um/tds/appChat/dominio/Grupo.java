package um.tds.appChat.dominio;

import java.util.Set;
import java.util.Random;

public class Grupo extends Contacto{
    
    public Grupo(String nombre, String urlImagen) {
    	super(nombre,new Random().nextInt(20000), urlImagen);
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
