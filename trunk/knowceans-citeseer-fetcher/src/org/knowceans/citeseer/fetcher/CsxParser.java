/*
 * Created on Feb 3, 2010
 */
package org.knowceans.citeseer.fetcher;

import java.io.CharArrayReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.knowceans.util.Conf;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * CsxParser parses the xml files to extract document information.
 * 
 * @author gregor heinrich (gregor :: arbylon . net)
 * @date 20100203 / 20100406
 * @version draft (quickly written but functional)
 */
public class CsxParser extends DefaultHandler {

	ArrayList<CsxDocument> docs;
	private SAXParser saxParser;
	List<String> taglist = Arrays.asList(new String[] { "identifier",
			"dc:title", "dc:description", "dc:date", "dc:language",
			"dc:creator", "dc:subject", "dc:relation" });
	String[] docdata = null;
	int state = -1;

	public static void main(String[] args) {
		Conf.overridePropFile("csx.conf");
		String base = "C:/Users/Administrator/Dropbox/Data/CiteSeerx/oaidump-";
		CsxParser c = new CsxParser();
		try {
			c.parse(base + "0.xml");
			System.out.println(c.docs);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public CsxParser() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			saxParser = factory.newSAXParser();
			docs = new ArrayList<CsxDocument>();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	/**
	 * parses an xml document
	 * 
	 * @param file
	 * @throws IOException
	 * @throws SAXException
	 */
	public List<CsxDocument> parse(String file) throws SAXException,
			IOException {
		docs.clear();
		saxParser.parse(file, this);
		return docs;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("record")) {
			if (docdata != null && docdata[0] != null) {
				CsxDocument doc = new CsxDocument();
				// save previous document
				doc.id = docdata[0];
				doc.title = docdata[1];
				doc.description = docdata[2];
				doc.date = docdata[3];
				doc.language = docdata[4];
				doc.authors = docdata[5];
				doc.tags = docdata[6];
				doc.relations = docdata[7];
				docs.add(doc);
			}
			docdata = new String[taglist.size()];
		} else {
			state = taglist.indexOf(qName);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		CharArrayReader r = new CharArrayReader(ch, start, length);
		char[] cbuf = new char[length];
		try {
			r.read(cbuf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String s = new String(cbuf).trim();
		// System.out.println(state + " " + s);
		if (state >= 0 && s.length() > 0) {
			if (state == 0) {
				s = s.substring(s.lastIndexOf(':') + 1);
			}
			if (docdata[state] == null) {
				docdata[state] = s;
			} else {
				docdata[state] += " | " + s;
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}
}
