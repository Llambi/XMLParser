package logic;

import java.util.ArrayList;

public class Recetario {
	ArrayList<Receta> recetas;
	
	public Recetario()
	{
		recetas = new ArrayList<Receta>();
	}
	
	public void addReceta(Receta receta)
	{
		recetas.add(receta);
	}
}
