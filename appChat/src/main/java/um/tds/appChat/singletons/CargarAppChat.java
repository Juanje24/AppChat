package um.tds.appChat.singletons;


import java.time.LocalDate;
import java.util.LinkedList;

import um.tds.appChat.dominio.ContactoIndividual;


public class CargarAppChat {
	

	public static void cargarDatos() {
		AppChat appChat = AppChat.INSTANCE;
		appChat.recuperarUsuarios();
		appChat.registrarUsuario("Juanje", "Ortiz", "jjog", LocalDate.of(2004, 02, 24),"717719424" ,"/users/foto perfil.jpg", "Hola, soy Juanje");
		appChat.registrarUsuario("Marina", "Montoro", "mnn", LocalDate.of(2004, 10, 05),"11" , "/users/noa.jpeg", "Hola,soy Marina");
		appChat.registrarUsuario("Stephen", "Curry", "sc", LocalDate.of(1988, 03, 14),"22" , "/users/curry.jpg", "Hello, I'm Stephen");
		appChat.registrarUsuario("Lebron", "James", "lb", LocalDate.of(1984, 12, 30),"33" , "/users/lebron.jpg", "I'm the king");
		appChat.registrarUsuario("Dwayne", "Johnson", "dj", LocalDate.of(1972, 05, 02),"44" , "/users/rock.jpg", "I'm the rock");
		appChat.registrarUsuario("Jack", "Sparrow", "js", LocalDate.of(1980, 12, 12), "55", "/users/jack.jpg", "I'm Jack Sparrow");
		appChat.registrarUsuario("Gandalf", "El Gris", "gandalf", LocalDate.of(1000, 4, 3), "66","/users/gandalf.jpg", "You shall not pass");
		
		appChat.login("717719424", "jjog");
		
		ContactoIndividual c1 = appChat.agregarContacto("Marina", "11");
		ContactoIndividual c2 = appChat.agregarContacto("Curry", "22");
		ContactoIndividual c3 = appChat.agregarContacto("Lebron", "33");
		ContactoIndividual c4 = appChat.agregarContacto("Dwayne", "44");
		ContactoIndividual c5 = appChat.agregarContacto("Jack", "55");
		ContactoIndividual c6 = appChat.agregarContacto("Gandalf", "66");
		
//		appChat.enviarMensajeContacto(c2, "Hola, ¿cómo estás?", -1, TipoMensaje.ENVIADO);
//		appChat.enviarMensajeContacto(c2, "", 2, TipoMensaje.ENVIADO);
//		
//		appChat.enviarMensajeContacto(c3, "Cuando cantas?", -1, TipoMensaje.ENVIADO);
//		appChat.enviarMensajeContacto(c2, "", 6, TipoMensaje.ENVIADO);
//		
//		appChat.login("22", "bb");
//		
//		//ContactoIndividual c1 =appChat.agregarContacto("jesus", "11");
//		ContactoIndividual c1 =  RepositorioUsuario.INSTANCE.buscarUsuarioPorMovil("22").get().getContactoIndividual("11");
//		ContactoIndividual c4 = appChat.agregarContacto("diego", "44");
//		ContactoIndividual c5 = appChat.agregarContacto("anne", "55");
//		
//		appChat.enviarMensajeContacto(c1, "Vienes este finde?", -1, TipoMensaje.ENVIADO);
//		appChat.enviarMensajeContacto(c1, "", 3, TipoMensaje.ENVIADO);
//	    appChat.enviarMensajeContacto(c4, "Juegas esta semana?", -1, TipoMensaje.ENVIADO);	
	    
	    System.out.println("Fin de la carga de datos");
	}

}