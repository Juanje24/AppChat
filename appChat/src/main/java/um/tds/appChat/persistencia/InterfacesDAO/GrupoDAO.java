package um.tds.appChat.persistencia.InterfacesDAO;

import java.util.List;

import um.tds.appChat.dominio.Grupo;

public interface GrupoDAO {

	public void registrarGrupo(Grupo Grupo);

	public void borrarGrupo(Grupo Grupo);

	public void modificarGrupo(Grupo Grupo);

	public Grupo recuperarGrupoPorId(int id);

	public List<Grupo> recuperarTodosGrupos();
}
