package um.tds.appChat.singletons;


import java.time.LocalDate;

import um.tds.appChat.dominio.ContactoIndividual;
import um.tds.appChat.dominio.Mensaje;
import um.tds.appChat.singletons.RepositorioUsuario;
import um.tds.appChat.dominio.TipoMensaje;
import um.tds.appChat.dominio.Usuario;

public class CargarAppChat {
	

	public static void main(String[] args) {
		AppChat appChat = AppChat.INSTANCE;
		appChat.registrarUsuario("aa", "11", "aa", LocalDate.of(1960, 10, 03),"11" ,"/usuarios/fotoJGM.png", "Hola, soy jesus");
		appChat.registrarUsuario("bb", "22", "bb", LocalDate.of(1995, 12, 28),"22" , "/usuarios/foto-elena.png", "hola, soy elena");
		appChat.registrarUsuario("cc", "33", "cc", LocalDate.of(2000, 5, 15),"33" , "/usuarios/rosalia.jpg", "hola, soy rosalia");
		appChat.registrarUsuario("dd", "44", "dd", LocalDate.of(1970, 5, 11),"44" , "/usuarios/foto-diego.png", "hola, soy diego");
		appChat.registrarUsuario("ee", "55", "ee", LocalDate.of(1990, 3, 28),"55" , "/usuarios/annetaylor.jpg", "hola, soy anne");
		
		appChat.login("11", "aa");
		
		ContactoIndividual c2 = appChat.agregarContacto("elena", "22");
		ContactoIndividual c3 = appChat.agregarContacto("rosalia", "33");
		
		appChat.enviarMensajeContacto(c2, "Hola, ¿cómo estás?", -1, TipoMensaje.ENVIADO);
		appChat.enviarMensajeContacto(c2, "", 2, TipoMensaje.ENVIADO);
		
		appChat.enviarMensajeContacto(c3, "Cuando cantas?", -1, TipoMensaje.ENVIADO);
		appChat.enviarMensajeContacto(c2, "", 6, TipoMensaje.ENVIADO);
		
		appChat.login("22", "bb");
		
		//ContactoIndividual c1 =appChat.agregarContacto("jesus", "11");
		ContactoIndividual c1 = ((Usuario) RepositorioUsuario.INSTANCE.buscarUsuarioPorMovil("22")).getContactoIndividual("11");
		ContactoIndividual c4 = appChat.agregarContacto("diego", "44");
		ContactoIndividual c5 = appChat.agregarContacto("anne", "55");
		
		appChat.enviarMensajeContacto(c1, "Vienes este finde?", -1, TipoMensaje.ENVIADO);
		appChat.enviarMensajeContacto(c1, "", 3, TipoMensaje.ENVIADO);
	    appChat.enviarMensajeContacto(c4, "Juegas esta semana?", -1, TipoMensaje.ENVIADO);	
	    
	    System.out.println("Fin de la carga de datos");
	}

}