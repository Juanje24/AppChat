package um.tds.appChat.dominio;

import java.time.LocalDateTime;
import java.util.Arrays;
import tds.BubbleText;

public class Mensaje {
    
    private int id;
    private String texto;
    private String emisor; //telefono
    private String receptor; //telefono
    private int tipo;
    private LocalDateTime fecha;

    public Mensaje (String texto, String emisor, String receptor, int tipo){
    	
        this.texto = texto;
        this.emisor = emisor;
        this.receptor = receptor;
        this.tipo = tipo;
        this.fecha = LocalDateTime.now(); 

    }

    public boolean matchText(String text){
        return Arrays.stream(texto.split("\\s+"))
                .anyMatch(e -> text.equalsIgnoreCase(texto));
    }

    //public boolean mathcNumber
    
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mensaje mensaje = (Mensaje) o;
        return texto != null ? texto.equals(mensaje.texto) : mensaje.texto == null;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	@Override
    public int hashCode() {
        return texto != null ? texto.hashCode() : 0;
    }
    
}
