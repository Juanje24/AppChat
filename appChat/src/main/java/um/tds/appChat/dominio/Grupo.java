package um.tds.appChat.dominio;

import java.util.Set;

public class Grupo extends Contacto{
    
    private Set<Usuario> miembros;

	public Set<Usuario> getMiembros() {
		return miembros;
	}

	public void setMiembros(Set<Usuario> miembros) {
		this.miembros = miembros;
	}
    
    
    
    
}
