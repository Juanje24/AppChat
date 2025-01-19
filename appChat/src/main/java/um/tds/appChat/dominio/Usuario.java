package um.tds.appChat.dominio;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private List<Contacto> contactos; 
    private LocalDate fechaRegistro;
    private Descuento descuento; 

    
    //CONSTRUCTORES
    
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
        this.fechaRegistro = LocalDate.now();
        this.descuento=null;
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
       this.fechaRegistro = LocalDate.now();
       this.descuento=null;
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
        this.fechaRegistro = LocalDate.now();
        this.descuento=null;
    }
    
    
    public Usuario(String nombre, String apellido, String telefono, String contraseña, LocalDate birthday){ //campos obligatorios
        this(nombre, apellido, telefono, contraseña, birthday, "", "");
    }



	
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

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Descuento getDescuento() {
		return descuento;
	}

	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
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


	public Optional<ContactoIndividual> getContactoIndividual(String telefono) {
		Optional<ContactoIndividual> contacto = Optional.empty();
		for (Contacto c: contactos) {
			if (c instanceof ContactoIndividual) {
				if (((ContactoIndividual) c).getUsuario().getTelefono().equals(telefono)) {
					contacto = Optional.of((ContactoIndividual) c);
				}
			}
		}
		return contacto;
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
	public Contacto getContacto(Contacto c) {
		return contactos.stream()
				.filter(contacto -> contacto.equals(c))
				.findFirst().get();
	}
	public Mensaje sendMensaje(String texto,int emoji, Contacto c) {
		for (Contacto contacto : contactos) {
			if (contacto.equals(c)) {
				return contacto.addMensaje(texto,emoji, telefono,nombre, BubbleText.SENT);
				
			}
		}
		return null;
	}
	public Mensaje recibeMensaje(String texto, int emoji, Contacto c) {
		for (Contacto contacto : contactos) {
			if (contacto.equals(c)) {
				return contacto.addMensaje(texto, emoji, telefono, nombre, BubbleText.RECEIVED);
			}
		}
		return null;
	}
	public void modificarNombreContacto(Contacto contacto, String nuevoNombre) {
		for (Contacto c : contactos) {
			if (c.equals(contacto)) {
				c.setNombre(nuevoNombre);
			}
		}
		
	}
	public void eliminarContacto(Contacto contacto) {
		contactos.remove(contacto);
	}
	public void modificarContacto(Contacto contacto) {
		for (Contacto c : contactos) {
			if (c.equals(contacto)){
				contactos.set(contactos.indexOf(c), contacto);
			}
		}
	}
	public int getNumMensajesEntreFecha(LocalDateTime inicio, LocalDateTime fin) {
		return contactos.stream()
		.mapToInt(c -> c.getNumeroMensajesEntreFechas(inicio, fin))
		.sum();
		
	}
	public List<Mensaje> searchMensajes(MessageSearchBuilder builder){
		return contactos.stream()
				.flatMap(c -> c.searchMessages(builder).stream())
				.collect(Collectors.toList());
	}

	public boolean areMensajesNuevos() {
		return contactos.stream().anyMatch(c->c.getNumeroMensajesNoLeidos()>0);
	}
	public Grupo actualizarGrupo(int id, String nombre, List<ContactoIndividual> contactosGrupo, String foto) {
		for (Contacto c : contactos) {
			if (c.getId() == id) {
                ((Grupo) c).setNombre(nombre);
                ((Grupo) c).setContactos(contactosGrupo);
                ((Grupo) c).setUrlImagen(foto);
				return (Grupo) c;
			}
		}
		return null;
	}
	
	

}
