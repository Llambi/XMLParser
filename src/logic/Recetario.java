package logic;

import java.util.ArrayList;

public class Recetario {
	ArrayList<Receta> recetas;
	
	public Recetario(ArrayList<Receta> recetas)
	{
		setRecetas(recetas);
	}

	public ArrayList<Receta> getRecetas() {
		return recetas;
	}

	public void setRecetas(ArrayList<Receta> recetas) {
		this.recetas = recetas;
	}

	public void clean() {
		recetas.clear();
	}
}
