package um.tds.appChat.dominio;

import java.time.LocalDate;

public class DescuentoPorFecha implements Descuento {
	
	public static final String NOMBRE = "Descuento por creación de cuenta entre fechas con promoción";
	
    private LocalDate inicio; //PODRÍAN SER ESTÁTICOS
    private LocalDate fin;
    private int valor;

    public DescuentoPorFecha(LocalDate inicio, LocalDate fin, int valor) {
        this.inicio = inicio;
        this.fin = fin;
        this.valor = valor;
    }

    @Override
    public boolean aplicaDescuento(Usuario usuario) {
        LocalDate hoy = LocalDate.now();
        return !hoy.isBefore(inicio) && !hoy.isAfter(fin); // Verifica si está en el rango de fechas
    }
    
    @Override
    public int getValor() {
    	return valor;
    }
}
