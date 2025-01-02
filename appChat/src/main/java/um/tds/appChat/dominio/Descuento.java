package um.tds.appChat.dominio;

public interface Descuento {
	boolean aplicaDescuento(Usuario usuario);
	int getValor();
}
