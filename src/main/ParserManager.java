package main;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

import org.dom4j.DocumentException;

import ParserManager.XMLParser;
import logic.Receta;
import logic.Recetario;

public class ParserManager {

	private Recetario recetario;
	private Compra compra;
	private XMLParser parser;

	public ParserManager(File file) throws DocumentException {
		parser = new XMLParser();
		recetario = new Recetario(parser.chargeRecetario(file));
		compra = new Compra();
	}

	public ArrayList<Receta> getRecetasRecetario() {
		return recetario.getRecetas();
	}

	public Recetario getRecetario() {
		return recetario;
	}

	public ArrayList<Receta> getRecetasCompra() {
		return compra.getRecetas();
	}

	public Compra getCompra() {
		return compra;
	}

	public void printCompra(URI uri) {
		parser.writeListaCompra(getRecetasCompra(), uri);
	}
}
