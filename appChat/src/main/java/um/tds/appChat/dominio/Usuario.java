package um.tds.appChat.dominio;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Usuario {
    
    private int id;
    private String nombre;
    private String apellido;
    private String contraseña;
    private String telefono;
    private boolean premium;
    private LocalDate birthday;
    private String saludo;
    private String urlImagen;
    private List<Contacto> contactos; //podría ser un Set

    
    public Usuario( int id, String nombre,String apellido, String telefono, String contraseña, LocalDate birthday,
    		boolean isPremium, String saludo, String urlImagen){
        this.nombre = nombre;
        this.id = id;
        this.apellido = apellido;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.birthday = birthday;
        this.saludo = saludo;
        this.urlImagen = urlImagen;
        this.premium = isPremium;
        this.contactos = new LinkedList<Contacto>();
    }
    
    
    public Usuario(String nombre, String apellido, String telefono, String contraseña, LocalDate birthday, String saludo, String urlImagen){
        this.nombre = nombre;
        this.id = new Random().nextInt();
        this.apellido = apellido;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.birthday = birthday;
        this.saludo = saludo;
        this.urlImagen = urlImagen;
        this.premium = false;
        this.contactos = new LinkedList<>();
    }
    
    
    //CONSTRUCTORES
    
    
    public Usuario(String nombre, String apellido, String telefono, String contraseña, LocalDate birthday, String saludo){ //sin imagen, con fecha
        this(nombre, apellido, telefono, contraseña, birthday, saludo, null);
    }

    public Usuario(String nombre, String apellido, String telefono, String contraseña, String saludo){ //sin imagen y sin fecha
        this(nombre, apellido, telefono, contraseña, null, saludo, null);
    }

    public Usuario(String nombre, String apellido, String telefono, String contraseña, String saludo, String urlImagen){ //con imagen, sin fecha
        this(nombre, apellido, telefono, contraseña, null, saludo, urlImagen);
    }
    
    //FUNCIONALIDAD
    
    
    public void sendMessage(Mensaje mensaje, Contacto contacto){ //
        contacto.addMessage(mensaje);
    }
    
    public void addContacto(Contacto contacto) {
    	contactos.add(contacto);
    }
    
//    public List<Mensaje> searchMessages(MessageSearchBuilder builder) {
//        return contactos.stream()
//                .filter(contacto -> builder.getNombreContacto().isEmpty() || contacto.getNombre().equalsIgnoreCase(builder.getNombreContacto().get()))
//                .flatMap(contacto -> contacto.getMensajes().stream()
//                        .filter(mensaje -> builder.getText().isEmpty() || mensaje.getContenido().contains(builder.getText().get()))
//                        .filter(mensaje -> builder.getNumero().isEmpty() || mensaje.getNumero() == builder.getNumero().get())
//                )
//                .distinct()
//                .collect(Collectors.toList());
//    }
    
//    public List<Mensaje> searchMessageByText (String text){
//    	return contactos.stream()
//                .flatMap(contacto -> contacto.searchMessageByText(text).stream())
//                .distinct()
//                .collect(Collectors.toList());
//    }
    
    
    //GETTERS Y SETTERS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getContraseña() {
		return contraseña;
	}

	public String getTelefono() {
		return telefono;
	}

	public boolean isPremium() {
		return premium;
	}
	
	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public String getSaludo() {
		return saludo;
	}

	public String getUrlImagen() {
		return urlImagen;
	}


	public List<Contacto> getContactos() {
		return contactos;
	}
	
	public void setContactos(LinkedList<Contacto> contactos) {
		this.contactos = new LinkedList<Contacto>(contactos);
	}

	public void addContacto(Contacto contacto) {
        this.contactos.add(contacto);
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
//    public List<Mensaje> searchMessageByContactnombre (){
//
//    }
//
//    public List<Mensaje> searchMessage (String text, String number, String contactnombre){
//        
//    }

	




    



}
