package um.tds.appChat.persistencia.ClasesDAO;

import um.tds.appChat.dominio.Usuario;
import um.tds.appChat.persistencia.InterfacesDAO.UsuarioDAO;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;


import java.text.SimpleDateFormat;
import java.util.List;


public class UsuarioDAO_TDS implements UsuarioDAO {
	private ServicioPersistencia servicioPersistencia;
	private SimpleDateFormat sdf ;
	private static UsuarioDAO_TDS unicaInstancia;
	
	public UsuarioDAO_TDS() {
		servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}
	public static UsuarioDAO getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new UsuarioDAO_TDS();
		return unicaInstancia;
	}
	
	@Override
	public Usuario registrarUsuario(Usuario usuario) {
		Entidad eUsuario = new Entidad();
		return null;
	}

	@Override
	public void borrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario modificarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario recuperarUsuarioPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
