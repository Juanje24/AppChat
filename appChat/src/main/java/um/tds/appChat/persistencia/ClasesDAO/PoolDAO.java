package um.tds.appChat.persistencia.ClasesDAO;

import java.util.Hashtable;


public class PoolDAO {
	private static PoolDAO[] unicaInstancia = new PoolDAO[4];
	private Hashtable<Integer, Object> pool;

	private PoolDAO() {
		pool = new Hashtable<Integer, Object>();
	}

	public static PoolDAO getUnicaInstancia(int tipo) {
		if (unicaInstancia[tipo] == null) unicaInstancia[tipo] = new PoolDAO();
		return unicaInstancia[tipo];
		
	}
	
	public Object getObjeto(int id) {
		return pool.get(id);
	} // devuelve null si no encuentra el objeto

	public void addObjeto(int id, Object objeto) {
		pool.put(id, objeto);
	}

	public void modificarObjeto(int id, Object objeto) {
		pool.replace(id, objeto);
	}
	public boolean contiene(int id) {
		return pool.containsKey(id);
	}
	public void borrarTodos() {
		pool.clear();
	}
}
