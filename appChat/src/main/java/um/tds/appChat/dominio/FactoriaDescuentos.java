package um.tds.appChat.dominio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public enum FactoriaDescuentos {
	INSTANCE;
    private static final Map<String, Supplier<Descuento>> descuentoMap = new HashMap<>();

    	static {
        descuentoMap.put(DescuentoPorFecha.NOMBRE, DescuentoPorFecha::new);
        descuentoMap.put(DescuentoPorMensajes.NOMBRE, DescuentoPorMensajes::new);
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

	public List<Descuento> getAllDescuentos() {
		return descuentoMap.values().stream()
				.map(Supplier::get)
				.collect(Collectors.toList());
	}

}
