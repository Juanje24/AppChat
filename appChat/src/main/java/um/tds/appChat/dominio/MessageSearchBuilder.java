package um.tds.appChat.dominio;

import java.util.Optional;
import um.tds.appChat.singletons.RepositorioUsuario;

public class MessageSearchBuilder {
	
	private Optional<String> text = Optional.empty();
    private Optional<String> numero = Optional.empty();
    private Optional<String> nombreContacto = Optional.empty();

    public MessageSearchBuilder setText(String text) {
        this.text = Optional.ofNullable(text);
        return this;
    }

    public MessageSearchBuilder setNumero(String numero) {
        this.numero = Optional.ofNullable(numero);
        return this;
    }

    public MessageSearchBuilder setNombreContacto(String nombreContacto) {
        this.nombreContacto = Optional.ofNullable(nombreContacto);
        return this;
    }

    public Optional<String> getText() {
        return text;
    }

    public Optional<String> getNumero() {
        return numero;
    }

    public Optional<String> getNombreContacto() {
        return nombreContacto;
    }
    public boolean filtrar(Mensaje msj) {
    	String nombreReceptor = RepositorioUsuario.INSTANCE.buscarUsuarioPorMovil(msj.getReceptor()).get().getNombre();
		return (text.isEmpty() || msj.getTexto().contains(text.get()))
				&& (numero.isEmpty() || msj.getReceptor().contains(numero.get()) || msj.getEmisor().equals(numero.get())) //Si es un grupo, los tlfs van separados por espacios, 
																														  //contains nos sirve muy bien
				&& (nombreContacto.isEmpty() || msj.getNombreEmisor().equalsIgnoreCase(nombreContacto.get()) || nombreReceptor.equalsIgnoreCase(nombreContacto.get()));
    }
}
