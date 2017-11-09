package logic;

import java.util.ArrayList;

public class Receta {

	private String nombre;
	private ArrayList<Ingrediente> ingredientes;

	public Receta(String nombre) {
		setNombre(nombre);
		this.ingredientes = new ArrayList<Ingrediente>();
	}

	@Override
	public String toString() {
		return getNombre();
	}

	public void addIngrediente(Ingrediente ingrediente) {
		ingredientes.add(ingrediente);
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}