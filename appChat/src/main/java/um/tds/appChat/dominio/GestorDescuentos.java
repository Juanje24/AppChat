package um.tds.appChat.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestorDescuentos {
    private List<Descuento> descuentos;

    public GestorDescuentos() {
        descuentos = new ArrayList<>();
    }

    public void agregarDescuento(Descuento descuento) {
        descuentos.add(descuento);
    }

    public boolean tieneDescuento(Usuario usuario) {
        return descuentos.stream().anyMatch(descuento -> descuento.aplicaDescuento(usuario));
    }
    
    public Optional<Descuento> getMayorDescuento(Usuario usuario) {
        return descuentos.stream()
                .filter(descuento -> descuento.aplicaDescuento(usuario)) // Filtra descuentos aplicables
                .max((d1, d2) -> Integer.compare(d1.getValor(), d2.getValor())); // Encuentra el mayor valor
    }
}
