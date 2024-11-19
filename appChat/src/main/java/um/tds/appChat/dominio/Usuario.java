package um.tds.appChat.dominio;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    
    //CONSTRUCTORES

    public Usuario(String name, String lastName, String telefono, String password, LocalDate birthday, String saludo){ //sin imagen, con fecha
        this(name, lastName, telefono, password, birthday, saludo, null);
    }

    public Usuario(String name, String lastName, String telefono, String password, String saludo){ //sin imagen y sin fecha
        this(name, lastName, telefono, password, null, saludo, null);
    }

    public Usuario(String name, String lastName, String telefono, String password, String saludo, String urlImagen){ //con imagen, sin fecha
        this(name, lastName, telefono, password, null, saludo, urlImagen);
    }
    
    //FUNCIONALIDAD
    
    
    public void sendMessage(Mensaje mensaje, Contacto contacto){ //
        contacto.addMessage(mensaje);
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
    
    public List<Mensaje> searchMessageByText (String text){
    	return contactos.stream()
                .flatMap(contacto -> contacto.searchMessageByText(text).stream())
                .distinct()
                .collect(Collectors.toList());
    }
    
    
    //GETTERS Y SETTERS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getTelefono() {
		return telefono;
	}

	public boolean isPremium() {
		return premium;
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

	public LinkedList<Contacto> getContactos() {
		return contactos;
	}

	public void setId(int id2) {
		id = id2;
		
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

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getSaludo() {
		return saludo;
	}

	public void setSaludo(String saludo) {
		this.saludo = saludo;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public List<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(LinkedList<Contacto> contactos) {
		this.contactos = new LinkedList<Contacto>(contactos);
	}
	
	




    



}
