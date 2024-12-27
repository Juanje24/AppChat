package um.tds.appChat.ventanas;


import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

import um.tds.appChat.dominio.Contacto;
import um.tds.appChat.dominio.Mensaje;

import java.awt.*;
import java.io.IOException;

public class ContactoListCellRenderer extends JPanel implements ListCellRenderer<Contacto> {
	private static final long serialVersionUID = 1L;
	private static final Border SELECCIONADO = BorderFactory.createLineBorder(Color.BLUE, 2);
    private static final Border NO_SELECCIONADO = BorderFactory.createEmptyBorder(2, 2, 2, 2);

	private JLabel lblImagen;
	private JLabel lblNombre;
	private JLabel lblUltimoMsg;

	public ContactoListCellRenderer() {
		setLayout(new BorderLayout(10, 10)); // Espaciado entre imagen y texto

		lblImagen = new JLabel();
		lblNombre = new JLabel();
		lblUltimoMsg = new JLabel();

		lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
		lblUltimoMsg.setFont(new Font("Arial", Font.PLAIN, 12));

		JPanel panelTexto = new JPanel(new GridLayout(3, 1)); // Para organizar los textos verticalmente
		panelTexto.add(lblNombre);
		panelTexto.add(lblUltimoMsg);

		add(lblImagen, BorderLayout.WEST);  // Imagen a la izquierda
		add(panelTexto, BorderLayout.CENTER);  // Texto a la derecha
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Contacto> listacontactos, Contacto contacto, int index,
			boolean isSelected, boolean cellHasFocus) {
		// Configuración de la imagen
		String fotoUsuario = contacto.getUrlImagen();
		URL url = getClass().getResource(fotoUsuario);
		if (url != null) {
			Image imagenOriginal;
			try {
				imagenOriginal = ImageIO.read(url);
				int anchoDeseado = 50;
		        int altoDeseado = 50;
		        Image imagenEscalada = imagenOriginal.getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);
		        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);		
		        lblImagen.setIcon(iconoEscalado);
			} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
	            
		} else System.err.println("No se pudo cargar la imagen: " + fotoUsuario);

		// Configuración del texto
		lblNombre.setText(contacto.getNombre());
//		Mensaje ultimoMsj = contacto.getMensajes().getLast();
//		if (ultimoMsj != null) {
//			lblUltimoMsg.setText(ultimoMsj.getTexto());
//		}
		lblUltimoMsg.setText("Último mensaje");
		


		// Configuración de colores para selección
		if (isSelected) {
            setBackground(new Color(184, 207, 229)); // Color de fondo para selección
            setBorder(SELECCIONADO); // Borde azul para mostrar selección
        } else {
            setBackground(Color.WHITE); // Fondo blanco cuando no está seleccionado
            setBorder(NO_SELECCIONADO); // Sin borde cuando no está seleccionado
        }

		setOpaque(true); // Para que el fondo se muestre correctamente
		return this;
	}
}
