package um.tds.appChat.dominio;

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
        this.tipo = tipo;
        this.fecha = new LocalDateTime(); //se tiene que modificar para que se atrape el momento exacto

    }

    public boolean matchText(String text){
        return Arrays.stream(texto.split("\\s+"))
                .anyMatch(text -> text.equalsIgnoreCase(texto));
    }

    //public boolean mathcNumber
}
