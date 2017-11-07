package main;

import java.net.URL;
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

	public ParserManager(URL url) throws DocumentException {
		recetario = new Recetario();
		parser = new XMLParser();
		chargeRecetario(url);
	}

	private void chargeRecetario(URL url) throws DocumentException {
		Document doc = parser.getDocument(url);
		List<Node> recetas = doc.selectNodes("//Receta");

		for (Node recetaNodes : recetas) {
			String nombreReceta = recetaNodes.valueOf("@nombre");
			Receta receta = new Receta(nombreReceta);

			List<Node> ingredientes = recetaNodes.selectNodes("//Ingrediente");

			for (Node ingrediente : ingredientes) {
				String nombreIngrdiente = ingrediente.getText();
				String cantidad = ingrediente.valueOf("@cantidad");
				receta.addIngrediente(new Ingrediente(nombreIngrdiente, cantidad));
			}

			recetario.addReceta(receta);
		}
	}
}
