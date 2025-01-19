package um.tds.appChat.singletons;


import java.time.LocalDate;
import java.util.LinkedList;

import um.tds.appChat.dominio.ContactoIndividual;
import um.tds.appChat.dominio.Grupo;


public class CargarAppChat {
	

	@SuppressWarnings("unused")
	public static void cargarDatos() {
		AppChat appChat = AppChat.INSTANCE;
		appChat.recuperarUsuarios();
		appChat.registrarUsuario("Juanje", "Ortiz", "jj", LocalDate.of(2004, 02, 24),"71" ,"/users/foto perfil.jpg", "Hola, soy Juanje");
		appChat.registrarUsuario("Marina", "Montoro", "mnn", LocalDate.of(2004, 10, 05),"11" , "/users/noa.jpeg", "Hola,soy Marina");
		appChat.registrarUsuario("Stephen", "Curry", "sc", LocalDate.of(1988, 03, 14),"22" , "/users/curry.jpg", "Hello, I'm Stephen");
		appChat.registrarUsuario("Lebron", "James", "lb", LocalDate.of(1984, 12, 30),"33" , "/users/lebron.jpg", "I'm the king");
		appChat.registrarUsuario("Dwayne", "Johnson", "dj", LocalDate.of(1972, 05, 02),"44" , "/users/rock.jpg", "I'm the rock");
		appChat.registrarUsuario("Jack", "Sparrow", "js", LocalDate.of(1980, 12, 12), "55", "/users/jack.jpg", "I'm Jack Sparrow");
		appChat.registrarUsuario("Gandalf", "El Gris", "gandalf", LocalDate.of(1000, 4, 3), "66","/users/gandalf.jpg", "You shall not pass");
		appChat.registrarUsuario("Fernando", "Alonso", "fa", LocalDate.of(1970, 2, 23), "14", "/users/nano.jpg", "Soy Fernando Alonso");
		appChat.registrarUsuario("Frodo", "Bolson", "fb", LocalDate.of(1980, 6, 23), "24", "/users/frodo.jpg", "Soy Frodo Bolsón");
		appChat.registrarUsuario("Sam", "Altman", "sa", LocalDate.of(1986, 3, 23), "34", "/users/sam.jpg", "I'm Sam Altman");
		appChat.registrarUsuario("Donald", "Trump", "dt", LocalDate.of(1953, 9, 23), "54", "/users/trump.png", "I'm Donald Trump");
		appChat.login("71", "jjog");
		
		ContactoIndividual c1 = appChat.agregarContacto("Marina", "11");
		ContactoIndividual c2 = appChat.agregarContacto("Curry", "22");
		ContactoIndividual c3 = appChat.agregarContacto("Lebron", "33");
		ContactoIndividual c4 = appChat.agregarContacto("Dwayne", "44");
		ContactoIndividual c5 = appChat.agregarContacto("Jack", "55");
		ContactoIndividual c6 = appChat.agregarContacto("Gandalf", "66");
		ContactoIndividual c7 = appChat.agregarContacto("Fernando", "14");
		ContactoIndividual c8 = appChat.agregarContacto("Frodo", "24");
		ContactoIndividual c9 = appChat.agregarContacto("Sam", "34");
		ContactoIndividual c10 = appChat.agregarContacto("Donald", "54");
		
		LinkedList<ContactoIndividual> contactos = new LinkedList<ContactoIndividual>();
		contactos.add(c2);
		contactos.add(c3);
		
		LinkedList<ContactoIndividual> contactos2 = new LinkedList<ContactoIndividual>();
		contactos2.add(c6);
		contactos2.add(c8);
		
		
		Grupo g1 =appChat.crearGrupo("NBA", contactos,"/iconos/grupoDefault.png");
		Grupo g2 =appChat.crearGrupo("El señor de los anillos", contactos2,"/iconos/grupoDefault.png");
		
		appChat.enviarMensajeContacto(c1, "Hola", -1);
		appChat.enviarMensajeContacto(c1, "Que tal?", -1);
		
		appChat.enviarMensajeContacto(g2, "Hola", -1);
		appChat.enviarMensajeContacto(g2, "Sois la compañia del anillo", -1);
		
		appChat.enviarMensajeContacto(c2, "Hello", -1);
		appChat.enviarMensajeContacto(c2, "How are you?", -1);
		
		appChat.logout();
		
		appChat.login("11", "mnn");
		ContactoIndividual c11 = appChat.agregarContacto("Gandalf", "66");
        appChat.enviarMensajeContacto(c11, "Hola", -1);
		
	    
	    System.out.println("Fin de la carga de datos");
	}

}