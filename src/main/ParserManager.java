package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;

import ParserManager.XMLParser;
import logic.Ingrediente;
import logic.Receta;
import logic.Recetario;

public class ParserManager {

	private Recetario recetario;
	private XMLParser parser;

	public ParserManager(File file) throws DocumentException {
		recetario = new Recetario();
		parser = new XMLParser();
		chargeRecetario(file);
	}

	private void chargeRecetario(File file) throws DocumentException {
		Document doc = parser.getDocument(file);
		List<Node> recetas = doc.selectNodes("//receta");

		for (Node recetaNodes : recetas) {
			Node nombreReceta = recetaNodes.selectSingleNode("titulo");
			Receta receta = new Receta(nombreReceta.getText());

			List<Node> ingredientes = recetaNodes.selectNodes("//ingrediente");

			for (Node ingrediente : ingredientes) {
				String nombreIngrdiente = ingrediente.getText();
				String cantidad = ingrediente.valueOf("@cantidad");
				receta.addIngrediente(new Ingrediente(nombreIngrdiente, cantidad));
			}

			recetario.addReceta(receta);
		}
	}

	public ArrayList<Receta> getRecetario() {
		return recetario.getRecetas();
	}
}
