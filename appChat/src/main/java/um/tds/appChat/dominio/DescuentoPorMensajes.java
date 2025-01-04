package um.tds.appChat.dominio;

import java.time.LocalDate;

public class DescuentoPorMensajes implements Descuento {
    
	public static final String NOMBRE = "Descuento por número de mensajes";
	
	private static final int MINIMO_MENSAJES=100; //PODRÍAN SER ESTÁTICOS
    private static int valor = 10;
    

    public DescuentoPorMensajes() {
  
    }

    @Override
    public boolean aplicaDescuento(Usuario usuario) {
        LocalDate haceUnMes = LocalDate.now().minusMonths(1);
        return usuario.getNumMensajesEntreFecha(haceUnMes, LocalDate.now()) >= MINIMO_MENSAJES; 
    }
    
    @Override
    public int getValor() {
    	return valor;
    }
}
