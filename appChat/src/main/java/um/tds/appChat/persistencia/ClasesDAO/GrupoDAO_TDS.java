package um.tds.appChat.persistencia.ClasesDAO;

import java.util.List;
import um.tds.appChat.dominio.Grupo;
import um.tds.appChat.persistencia.InterfacesDAO.GrupoDAO;

public class GrupoDAO_TDS implements GrupoDAO {
	private static GrupoDAO_TDS unicaInstancia = new GrupoDAO_TDS();
	
	public static GrupoDAO_TDS getUnicaInstancia() {
		return unicaInstancia;
	}
	
	@Override
	public void registrarGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
	}

	@Override
	public void borrarGrupo(Grupo grupo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
	}

	@Override
	public Grupo recuperarGrupoPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Grupo> recuperarTodosGrupos() {
		// TODO Auto-generated method stub
		return null;
	}

}
