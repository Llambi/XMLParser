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

	public ArrayList<Receta> getRecetario() {
		return recetario.getRecetas();
	}

	public ArrayList<Receta> getCompra() {
		return compra.getRecetas();
	}
	
	public void printCompra(URI uri)
	{
		parser.writeListaCompra(getCompra(), uri);
	}
}
