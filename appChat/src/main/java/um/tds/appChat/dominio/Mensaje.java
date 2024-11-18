package um.tds.appChat.dominio;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Mensaje {
    
    private int id;
    private String texto;
    private String emisor; //telefono
    private String receptor; //telefono
    private TipoMensaje tipo;
    private LocalDateTime fecha;

    public Mensaje (String texto, String emisor, String receptor, String tipo){
    	
        this.texto = texto;
        this.emisor = emisor;
        this.receptor = receptor;
        if (tipo.equals("ENVIADO")) {
			this.tipo = TipoMensaje.ENVIADO;
		} else if (tipo.equals("RECIBIDO")) {
			this.tipo = TipoMensaje.RECIBIDO;
    	}
        this.fecha = LocalDateTime.now(); //se tiene que modificar para que se atrape el momento exacto

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

	public TipoMensaje getTipo() {
		return tipo;
	}

	public void setTipo(TipoMensaje tipo) {
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
