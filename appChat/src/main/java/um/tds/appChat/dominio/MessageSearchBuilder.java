package um.tds.appChat.dominio;

import java.time.LocalDate;
import java.util.Optional;

public class MessageSearchBuilder {
	//A pesar de que no se recomienda usar Optional como atributos de clase, en este caso se ha decidido usarlo 
	//ya que el manejo del Optional es privado a esta clase
	private Optional<String> text = Optional.empty();
    private Optional<String> numero = Optional.empty();
    private Optional<String> nombreContacto = Optional.empty();
    private Optional<LocalDate> fecha = Optional.empty();

	public MessageSearchBuilder() {
	}
	public MessageSearchBuilder(String texto, String numero, String nombreContacto, LocalDate fecha) {
		this.text = Optional.ofNullable(texto);
		this.numero = Optional.ofNullable(numero);
		this.nombreContacto = Optional.ofNullable(nombreContacto);
		this.fecha = Optional.ofNullable(fecha);
	}
	
	
    public void setText(String text) {
        this.text = Optional.ofNullable(text);
    }

    public void setNumero(String numero) {
        this.numero = Optional.ofNullable(numero);
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = Optional.ofNullable(nombreContacto);
    }

	public void setFecha(LocalDate fecha) {
		this.fecha = Optional.ofNullable(fecha);
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
    public Optional<LocalDate> getFecha() {
    	   return fecha;
    }
    
    public boolean filtrar(Mensaje msj) {
		return (text.isEmpty() || msj.getTexto().contains(text.get()))
				&& (numero.isEmpty() || msj.getReceptor().contains(numero.get()) || msj.getEmisor().equals(numero.get())) //Si es un grupo, los tlfs van separados por espacios, 
																														  //contains nos sirve muy bien
				&& (fecha.isEmpty() || msj.getFecha().isAfter(fecha.get().minusDays(1)))
				&& (nombreContacto.isEmpty() || msj.getNombreEmisor().equalsIgnoreCase(nombreContacto.get()) || msj.getNombreReceptor().equalsIgnoreCase(nombreContacto.get()));
    }
}
