package um.tds.appChat.persistencia.InterfacesDAO;

import java.util.List;

import um.tds.appChat.dominio.Grupo;

public interface GrupoDAO {
	
	Grupo craete(Grupo grupo);
	
	boolean delete(Grupo grupo);
	
	void update(Grupo grupo);
	
	Grupo get(int id);
	
	List<Grupo> getAll();
}
