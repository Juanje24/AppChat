package um.tds.appChat;

import java.awt.EventQueue;

import um.tds.appChat.ventanas.Inicio;

public class Lanzador {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//CargarAppChat.cargarDatos();
					Inicio ventana = new Inicio();
					ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
