package david.apps.model;

public class Producto {
	
	private int codigo;
	private String nombre;
	private String linea;
	private int contenido;
	private String unidadesContenido;
	private double precio;
	
	
	public Producto(int codigo, String nombre, String linea, int contenido, String unidadesContenido, double precio) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.linea = linea;
		this.contenido = contenido;
		this.unidadesContenido = unidadesContenido;
		this.precio = precio;
	}


	public int getCodigo() {
		return codigo;
	}


	public String getNombre() {
		return nombre;
	}


	public String getLinea() {
		return linea;
	}


	public int getContenido() {
		return contenido;
	}


	public String getUnidadesContenido() {
		return unidadesContenido;
	}


	public double getPrecio() {
		return precio;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setLinea(String linea) {
		this.linea = linea;
	}


	public void setContenido(int contenido) {
		this.contenido = contenido;
	}


	public void setUnidadesContenido(String unidadesContenido) {
		this.unidadesContenido = unidadesContenido;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}

	

}
