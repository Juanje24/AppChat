package um.tds.appChat.dominio;

import java.util.Optional;

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
}
