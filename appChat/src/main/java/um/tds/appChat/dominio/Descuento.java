package um.tds.appChat.dominio;

public interface Descuento {
	boolean aplicaDescuento(Usuario usuario);
	double getPrecio(double precioOriginal);
	String getNombre();
}
