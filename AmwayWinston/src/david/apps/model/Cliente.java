package david.apps.model;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {
	
	private final StringProperty nombre;
	private final StringProperty apellido;
	private final IntegerProperty telefono;
	private final StringProperty email;
	private final ObjectProperty<LocalDate> fechaCompra;
	private final ObjectProperty<ArrayList<Producto>> listaProducto;
	private final StringProperty producto;
	
	/**
     * Default constructor.
     */
    public Cliente() {
        this(null, null);
    }
	
    /**
     * Constructor with some initial data.
     * 
     * @param firstName
     * @param lastName
     */
    public Cliente(String nombre, String apellido) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido= new SimpleStringProperty(apellido);

        // Some initial dummy data, just for convenient testing.
        this.email = new SimpleStringProperty("some email");
        this.telefono = new SimpleIntegerProperty(1234);
        this.producto = new SimpleStringProperty("some producto");
        this.fechaCompra = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
        this.listaProducto= new SimpleObjectProperty<ArrayList<Producto>>(null);
    }
    
    public String getNombre() {
    	return nombre.get();
    }
    
    public void setNombre(String nombre) {
    	this.nombre.set(nombre);
    }
    
    public StringProperty nombreProperty() {
    	return nombre;
    }
	
    public String getApellido() {
    	return apellido.get();
    }
    
    public void setApellido(String apellido) {
    	this.apellido.set(apellido);
    }
    
    public StringProperty apellidoProperty() {
    	return apellido;
    }
	
    public String getEmail() {
    	return email.get();
    }
    
    public void setEmail(String email) {
    	this.email.set(email);
    }
    
    public StringProperty emailProperty() {
    	return email;
    }

    public int getTelefono() {
    	return telefono.get();
    }
    
    public void setTelefono(int telefono) {
    	this.telefono.set(telefono);
    }
    
    public IntegerProperty telefonoProperty() {
    	return telefono;
    }

    public LocalDate getFechaCompra() {
    	return fechaCompra.get();
    }
    
    public void setFechaCompra(LocalDate fechaCompra) {
    	this.fechaCompra.set(fechaCompra);
    }
    
    public ObjectProperty<LocalDate> fechaCompraProperty() {
    	return fechaCompra;
    }

    public String getProducto() {
    	return producto.get();
    }
    
    public void setProducto(String producto) {
    	this.producto.set(producto);
    }
    
    public StringProperty productoProperty() {
    	return producto;
    }


}
