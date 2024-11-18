package um.tds.appChat.persistencia.InterfacesDAO;

import java.util.List;

import um.tds.appChat.dominio.Grupo;

public interface GrupoDAO {
	public void registrarGrupo(Grupo grupo);
	public void borrarGrupo(Grupo grupo);
	public Grupo modificarGrupo(Grupo grupo);
	public Grupo recuperarGrupoPorId(int id);
	public List<Grupo> recuperarTodosGrupos();
}
