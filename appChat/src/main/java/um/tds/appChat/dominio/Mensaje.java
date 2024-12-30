package um.tds.appChat.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Mensaje {
    
    private int id;
    private String texto;
    private int emoji;
    private String emisor; //telefono
    private String receptor; //telefono
    private int tipo;
    private LocalDate fecha;

    public Mensaje (String texto, int emoji, String emisor, String receptor, int tipo){
    	
        this.texto = texto;
        this.emoji = emoji;
        this.emisor = emisor;
        this.receptor = receptor;
        this.tipo = tipo;
        this.fecha = LocalDate.now(); 

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

	public int getEmoji() {
		return emoji;
	}

	public int setEmoji(int emoji) {
		return this.emoji = emoji;
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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
    public int hashCode() {
        return texto != null ? texto.hashCode() : 0;
    }
    
}
