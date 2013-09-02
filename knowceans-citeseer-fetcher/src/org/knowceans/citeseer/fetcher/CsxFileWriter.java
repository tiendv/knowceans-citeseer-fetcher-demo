package org.knowceans.citeseer.fetcher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.knowceans.map.BijectiveHashMap;
import org.xml.sax.SAXException;

/*
 * Created on Dec 20, 2009
 */

/**
 * converts OAI xml files to files easier indexable with the knowceans-lda
 * package, collecting authors, titles etc. in separate fields. The main issue
 * with this is the extensive amount of duplicates in the result (is CSX really
 * that noisy?). <br>
 * Use this with -Xmx1024m, as the code is based on large indices.
 * 
 * @author gregor heinrich (gregor :: arbylon . net)
 * @date 20091220 / 20100406
 * @version draft (quickly written but functional)
 */
public class CsxFileWriter {

	int nextmid;
	int nextaid;

	public static void main(String[] args) {
		String base = "C:/datasets/citeseerx/citeseerx-oai/oaidump-";
		String outbase = "C:/datasets/citeseerx-out/csx";
		// String base = "c:/shared/citeseerx/oaidump-";
		// String outbase = "c:/shared/citeseerx-out/csx";
		CsxFileWriter cf = new CsxFileWriter(base, outbase);
		try {
			cf.run();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String base;
	private CsxParser parser;
	private String outbase;
	private BufferedWriter bwdocs;
	private BufferedWriter bwdescriptions;
	private BufferedWriter bwauthors;
	private BufferedWriter bwactors;
	private BufferedWriter bwreferences;
	private BijectiveHashMap<String, Integer> actors;

	public CsxFileWriter(String base, String outbase) {
		this.base = base;
		this.outbase = outbase;
		this.parser = new CsxParser();
	}

	/**
	 * fetch documents
	 * 
	 * @param downloads
	 * @throws IOException
	 * @throws SAXException
	 */
	private void run() throws SAXException, IOException {

		nextmid = 0;
		nextaid = 0;
		actors = new BijectiveHashMap<String, Integer>();
		try {
			bwdocs = new BufferedWriter(new FileWriter(outbase + ".docs"));
			bwdescriptions = new BufferedWriter(new FileWriter(outbase
					+ ".abstracts"));
			bwauthors = new BufferedWriter(new FileWriter(outbase + ".authors"));
			bwactors = new BufferedWriter(new FileWriter(outbase + ".actors"));
			bwreferences = new BufferedWriter(new FileWriter(outbase + ".refs"));
			for (int i = 0; i < 1500; i++) {

				String filename = base + i + ".xml";
				System.out.println(filename);
				File f = new File(filename);
				if (f.exists() && getResumptionToken(filename) != null) {
					List<CsxDocument> a = parser.parse(filename);
					for (int j = 0; j < a.size(); j++) {
						write(a.get(j));
					}
					bwdocs.flush();
					bwdescriptions.flush();
					bwauthors.flush();
					bwactors.flush();
					bwreferences.flush();
				} else {
					break;
				}
			}
			bwdocs.close();
			bwdescriptions.close();
			bwauthors.close();
			bwactors.close();
			bwreferences.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * write a CsxDocument to the database
	 * 
	 * @param csxDocument
	 */
	private void write(CsxDocument doc) {

		doc.id = doc.id.replaceFirst("10.1.1.", "");
		// split actors
		try {
			if (doc.authors != null) {
				String[] authors = doc.authors.split("\\|");
				int[] aa = new int[authors.length];
				for (int i = 0; i < authors.length; i++) {
					String actor = authors[i].trim();
					Integer a = actors.get(actor);
					if (a == null) {
						a = actors.size();
						bwactors.write(a + " " + actor + "\n");
						actors.put(actor, a);
					}
					aa[i] = a;
					// authors are zero-based, i.e., actors start with id=0 in
					// first line
					bwauthors.write(a + " ");
				}
			}
			bwauthors.write("\n");
			bwdocs.write(doc.id + ": ");
			bwdocs.write(doc.title != null ? doc.title.trim().replaceAll("\n",
					"\\\\n") : "");
			bwdocs.write("\n");
			bwdescriptions.write("<" + doc.id + ">\n");
			bwdescriptions.write(doc.description != null ? doc.description
					.trim() : "");
			bwdescriptions.write("\n");
			bwreferences.write(doc.relations != null ? doc.relations
					.replaceAll("\\|", " ").replaceAll("10.1.1.", "")
					+ "\n" : "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * read out the resumption token from the file. This is inefficient, but
	 * used here for simplicity and to check file consistency.
	 * 
	 * @param filename
	 * @return
	 */
	private String getResumptionToken(String filename) {
		String resumptionToken = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = null;
			Pattern p = Pattern
					.compile("<resumptionToken>([^<]+)</resumptionToken>");
			while ((line = br.readLine()) != null) {
				// first use "cheap" candidate calculation
				if (line.trim().startsWith("<resu")) {
					Matcher m = p.matcher(line);
					if (m.find()) {
						resumptionToken = m.group(1);
						break;
					}
				}
			}
			br.close();
			return resumptionToken;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
