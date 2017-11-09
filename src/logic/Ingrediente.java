package logic;

public class Ingrediente {

	private String nombre;
	private String cantidad;

	public Ingrediente(String nombre, String cantidad) {
		super();
		setNombre(nombre);
		setCantidad(cantidad);
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getCantidad() {
		return cantidad;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

}
