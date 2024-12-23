package um.tds.appChat.persistencia.InterfacesDAO;

import java.util.List;
import java.util.Optional;

import um.tds.appChat.dominio.Grupo;

public interface GrupoDAO {

	public Grupo registrarGrupo(Grupo Grupo);

	public void borrarGrupo(Grupo Grupo);

	public Grupo modificarGrupo(Grupo Grupo);

	public Optional<Grupo> recuperarGrupoPorId(int id);

	public List<Grupo> recuperarTodosGrupos();
}
