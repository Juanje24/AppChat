package um.tds.appChat.dominio;

import java.time.LocalDate;
import java.util.LinkedList;

public class Usuario {
    
    private int id;
    private String name;
    private String lastName;
    private String password;
    private String telefono;
    private boolean premium;
    private LocalDate birthday;
    private String saludo;
    private String urlImagen;
    private LinkedList<Contacto> contactos; //podría ser un Set


    public Usuario(String name, String lastName, String telefono, String password, LocalDate birthday, String saludo, String urlImagen){
        this.name = name;
        this.lastName = lastName;
        this.telefono = telefono;
        this.password = password;
        this.birthday = birthday;
        this.saludo = saludo;
        this.urlImagen = urlImagen;
        this.premium = false;
        this.contactos = new LinkedList<>();
    }

    public Usuario(String name, String lastName, String telefono, String password, LocalDate birthday, String saludo){ //sin imagen, con fecha
        this(name, lastName, telefono, password, birthday, saludo, null);
    }

    public Usuario(String name, String lastName, String telefono, String password, String saludo){ //sin imagen y sin fecha
        this(name, lastName, telefono, password, null, saludo, null);
    }

    public Usuario(String name, String lastName, String telefono, String password, String saludo, String urlImagen){ //con imagen, sin fecha
        this(name, lastName, telefono, password, null, saludo, urlImagen);
    }

	public String getEmail() {
		
		return null;
	}

	public String getContraseña() {
		return null;
	}

//    public void sendMessage(Mensaje mensaje, Contacto contacto){
//        contacto.
//    }
//
//    public List<Mensaje> searchMessageByText (){
//
//    }
//
//    public List<Mensaje> searchMessageByNumber (){
//        this.me
//    }
//
//    public List<Mensaje> searchMessageByContactName (){
//
//    }
//
//    public List<Mensaje> searchMessage (String text, String number, String contactName){
//        
//    }


    



}
