package um.tds.appChat.dominio;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FactoriaDescuentos {
    private static final Map<String, Supplier<Descuento>> descuentoMap = new HashMap<>();

    	static {
        descuentoMap.put("fecha", DescuentoPorFecha::new);
        descuentoMap.put("mensajes", DescuentoPorMensajes::new);
    }

	public Descuento crearDescuento(String tipo) {
		Supplier<Descuento> descuentoSupplier = descuentoMap.get(tipo);
		if (descuentoSupplier != null) {
			return descuentoSupplier.get();
		}
		else {
			throw new IllegalArgumentException("Tipo de descuento desconocido");
		}
	}

}
