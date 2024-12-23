package um.tds.appChat.persistencia;

import um.tds.appChat.persistencia.InterfacesDAO.*;

public abstract class FactoriaDAO {
	private static FactoriaDAO INSTANCE;
	public static final String DAO_TDS = "um.tds.appChat.persistencia.FactoriaDAO_TDS";
	
	public static FactoriaDAO getInstancia(String tipo) throws DAOException {
		if (INSTANCE == null)
			try {
				INSTANCE = (FactoriaDAO) Class.forName(tipo).getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				throw new DAOException(e.getMessage());
			}
		return INSTANCE;
	}

	public static FactoriaDAO getFactoriaDAO(){
		return INSTANCE;
	}

	protected FactoriaDAO() {
	}
	public abstract UsuarioDAO crearUsuarioDAO();
	public abstract ContactoIndividualDAO crearContactoIndividualDAO();
	public abstract GrupoDAO crearGrupoDAO();
	public abstract MensajeDAO crearMensajeDAO();
}
