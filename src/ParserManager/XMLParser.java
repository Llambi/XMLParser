package ParserManager;

import java.net.URL;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class XMLParser {

	public Document getDocument(URL url) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);
		return document;
	}
	
	public void bar(Document document) {
	    List<Node> list = document.selectNodes("//foo/bar");

	    Node node = document.selectSingleNode("//foo/bar/author");

	    String name = node.valueOf("@name");
	}
}
