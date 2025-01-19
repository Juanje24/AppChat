package um.tds.appChat.dominio;

import java.time.LocalDateTime;

public class DescuentoPorMensajes implements Descuento {
    
	public static final String NOMBRE = "Descuento por número de mensajes";
	
	private static final int MINIMO_MENSAJES=20;
    private static int valor = 10;
    

    public DescuentoPorMensajes() {
  
    }

    @Override
    public boolean aplicaDescuento(Usuario usuario) {
        LocalDateTime haceUnMes = LocalDateTime.now().minusMonths(1);
        return usuario.getNumMensajesEntreFecha(haceUnMes, LocalDateTime.now()) >= MINIMO_MENSAJES; 
    }
    
    @Override
	public double getPrecio(double precioOriginal) {
    	return precioOriginal - (precioOriginal * valor / 100);
    }
    public String getNombre() {
    	return NOMBRE;
    }
}
