package um.tds.appChat.dominio;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Mensaje {
    
    private int id;
    private String texto;
    private int emoji;
    private String tlfEmisor; //telefono
    private String tlfReceptor; //telefono
    private String nombreEmisor;
    private String nombreReceptor;
    private int tipo;
    private LocalDateTime fecha;
    private boolean leido;

    public Mensaje (String texto, int emoji, String emisor, String receptor, String nombreEmisor, String nombreReceptor,  int tipo){
    	
        this.texto = texto;
        this.emoji = emoji;
        this.tlfEmisor = emisor;
        this.tlfReceptor = receptor;
        this.nombreEmisor = nombreEmisor;
        this.nombreReceptor = nombreReceptor;
        System.out.println("tlfEmisor: "+tlfEmisor+" tlfReceptor: "+tlfReceptor+" nombreEmisor: "+nombreEmisor+" nombreReceptor: "+nombreReceptor);
        this.tipo = tipo;
        this.fecha = LocalDateTime.now(); 
        this.leido = false;

    }

    public boolean matchText(String text){
        return Arrays.stream(texto.split("\\s+"))
                .anyMatch(e -> text.equalsIgnoreCase(texto));
    }   
    
    
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
		return tlfEmisor;
	}

	public void setEmisor(String emisor) {
		this.tlfEmisor = emisor;
	}

	public String getReceptor() {
		return tlfReceptor;
	}

	public void setReceptor(String receptor) {
		this.tlfReceptor = receptor;
	}

	public String getNombreEmisor() {
		return nombreEmisor;
	}

	public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
	}
	public String getNombreReceptor() {
		return nombreReceptor;
	}

	public void setNombreReceptor(String nombreReceptor) {
        this.nombreReceptor = nombreReceptor;
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

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}

	@Override
    public int hashCode() {
        return texto != null ? texto.hashCode() : 0;
    }
    
}
