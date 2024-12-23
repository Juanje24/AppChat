package um.tds.appChat.dominio;

import java.util.Set;
import java.util.Random;

public class Grupo extends Contacto{
    
    public Grupo(String nombre) {
		super(nombre,new Random().nextInt(0,2000));
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
