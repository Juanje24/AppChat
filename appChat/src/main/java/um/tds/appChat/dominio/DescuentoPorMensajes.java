package um.tds.appChat.dominio;

import java.time.LocalDate;

public class DescuentoPorMensajes implements Descuento {
    
	public static final String NOMBRE = "Descuento por número de mensajes";
	
	private int minimoMensajes; //PODRÍAN SER ESTÁTICOS
    private int valor;
    

    public DescuentoPorMensajes(int minimoMensajes, int valor) {
        this.minimoMensajes = minimoMensajes;
        this.valor = valor;
    }

    @Override
    public boolean aplicaDescuento(Usuario usuario) {
        LocalDate haceUnMes = LocalDate.now().minusMonths(1);
        return usuario.getContactos().stream()
                .flatMap(contacto -> contacto.getMensajes().stream())
                .filter(mensaje -> mensaje.getEmisor().equals(usuario.getTelefono()))
                .filter(mensaje -> !mensaje.getFecha().isBefore(haceUnMes))
                .count() >= minimoMensajes; 
    }
    
    @Override
    public int getValor() {
    	return valor;
    }
}
