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
}
