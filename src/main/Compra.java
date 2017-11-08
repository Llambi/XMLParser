package main;

import java.util.ArrayList;

import logic.Receta;

public class Compra {
	ArrayList<Receta> recetas;

	public Compra() {
		this.recetas = new ArrayList<Receta>();
	}

	public boolean isInCompra(Receta receta) {
		return recetas.contains(receta);
	}

	public void addReceta(Receta receta) {
		recetas.add(receta);
	}

	public ArrayList<Receta> getRecetas() {
		return recetas;
	}
}
