package um.tds.appChat.dominio;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import tds.BubbleText;

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

    
    public Usuario( String nombre,String apellido, String telefono, String contraseña, LocalDate birthday,
    		 String saludo, String urlImagen, int id, boolean isPremium, List<Contacto> contactos){
        this.nombre = nombre;
        this.id = id;
        this.apellido = apellido;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.birthday = birthday;
        this.saludo = saludo;
        this.urlImagen = urlImagen;
        this.premium = isPremium;
        this.contactos = new LinkedList<Contacto>(contactos);
    }
    public Usuario( String nombre,String apellido, String telefono, String contraseña, LocalDate birthday,
   		 String saludo, String urlImagen, boolean isPremium){
       this.nombre = nombre;
       //this.id = id;
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
        //this.id = new Random().nextInt(40000);
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
    
    
    public Usuario(String nombre, String apellido, String telefono, String contraseña, LocalDate birthday){ //campos obligatorios
        this(nombre, apellido, telefono, contraseña, birthday, "", "");
    }

    
    //FUNCIONALIDAD
    
    
    
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
	public void setApellido(String apellido) {
		this.apellido = apellido;
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
		return new LinkedList<Contacto>(contactos);
	}
	
	public void setContactos(List<Contacto> contactos) {
		this.contactos = new LinkedList<Contacto>(contactos);
	}


	public void addContactoIndividual(String nombre,Usuario usuario) {
        ContactoIndividual contacto = new ContactoIndividual(nombre, usuario);
		this.contactos.add(contacto);
    }

	public Grupo addGrupo(String nombre, List<ContactoIndividual> contactosGrupo, String foto) {
		Grupo g = new Grupo(nombre, contactosGrupo, foto);
		this.contactos.add(g);
		return g;
	}


	public ContactoIndividual getContactoIndividual(String telefono) {
		for (Contacto c: contactos) {
			if (c instanceof ContactoIndividual) {
				if (((ContactoIndividual) c).getUsuario().getTelefono().equals(telefono)) {
					return (ContactoIndividual) c;
				}
			}
		}
        return null;
	}


	public boolean isTlfEnContactos(String tlf) {
		for (Contacto c : contactos) {
			if (c instanceof ContactoIndividual) {
				if (((ContactoIndividual) c).getUsuario().getTelefono().equals(tlf)) {
					return true;
				}
			}
		}
		return false;
	}
	public List<ContactoIndividual> getContactosIndividuales() {

		List<ContactoIndividual> individuales = new LinkedList<>();
		for (Contacto c : contactos) {
			if (c instanceof ContactoIndividual) {
				individuales.add((ContactoIndividual) c);
			}
		}
		return individuales;
	}
	public void addContacto(Contacto c) {
		this.contactos.add(c);
		
	}
	public void sendMensaje(String texto, Contacto c) {
		for (Contacto contacto : contactos) {
			if (contacto.equals(c)) {
				contacto.addMensaje(texto, telefono, BubbleText.SENT);
			}
		}
	}
	public void modificarNombreContacto(Contacto contacto, String nuevoNombre) {
		for (Contacto c : contactos) {
			if (c.equals(contacto)) {
				c.setNombre(nuevoNombre);
			}
		}
		
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
