package um.tds.appChat.dominio;

import java.time.LocalDate;

public class DescuentoPorFecha implements Descuento {
	
	public static final String NOMBRE = "Descuento por creación de cuenta entre fechas con promoción";
	
    private static LocalDate inicio = LocalDate.of(2025, 1, 1); //PODRÍAN SER ESTÁTICOS
    private static LocalDate fin = LocalDate.of(2025, 1, 31);
    private int valor = 25;

    public DescuentoPorFecha() {
    }

    @Override
    public boolean aplicaDescuento(Usuario usuario) {
      
        return usuario.getFechaRegistro().isAfter(inicio) && usuario.getFechaRegistro().isBefore(fin); // Verifica si está en el rango de fechas
    }
    
    @Override
    public double getPrecio(double precioOriginal) {
    	return precioOriginal - (precioOriginal * valor / 100);
    }

	public String getNombre() {
		return NOMBRE;
	}
}
