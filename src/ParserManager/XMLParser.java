package ParserManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import logic.Ingrediente;
import logic.Receta;
import main.Compra;

public class XMLParser {

	public Document getDocument(File file) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		return document;
	}

	public ArrayList<Receta> chargeRecetario(File file) throws DocumentException {
		ArrayList<Receta> recetario = new ArrayList<Receta>();
		Document doc = getDocument(file);
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
			recetario.add(receta);
		}
		return recetario;
	}

	public void writeListaCompra(ArrayList<Receta> recetas, URI uri) {
		XMLWriter writer = null;
		String ruta = uri.toString() + "/Compra.xml";
		try (FileWriter fileWriter = new FileWriter(ruta)) {
			OutputFormat format = OutputFormat.createPrettyPrint();
			writer = new XMLWriter(System.out, format);
			writer.write(createDocument(recetas));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private Document createDocument(ArrayList<Receta> recetas) {
		Document document = DocumentHelper.createDocument();
		Element compra = document.addElement("compra");
		for (Receta receta : recetas) {
			Element comida = compra.addElement("receta").addText(receta.getNombre());
			for (Ingrediente ingrediente : receta.getIngredientes()) {
				comida.addElement("ingrediente").addAttribute("cantidad", ingrediente.getCantidad())
						.addText(ingrediente.getNombre());
			}
		}
		return document;
	}

}
