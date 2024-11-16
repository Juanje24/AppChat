package um.tds.appChat.dominio;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Contacto {
    
    private int id;
    private String nombre;
    private Usuario usuario;
    private List<Mensaje> mensajes;

    private List<Mensaje> searchMessageByText(String text){
        return mensajes.stream()
                .filter(mensaje -> mensaje.matchText(text))
                .collect(Collectors.toList());
    }

    // private List<Mensaje> searchMessageByContactName(String name){
    //     return mensajes.stream()
    //             .filter(mensaje -> mensaje.matchName(name))
    //             .collect();
    // }

}
