package org.knowceans.citeseer.fetcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Created on Dec 20, 2009
 */

/**
 * retrieves XML metadata from new CiteSeerX OAI site using a "lean" coding
 * approach. Caveat: The OAI site is sloooooooow (5-10 min / xml file).
 * 
 * @author gregor heinrich (gregor :: arbylon . net)
 * @date 20091220 / 20100406
 * @version draft (quickly written but functional)
 */
public class CsxOaiFetcher {

	/**
	 * maximum number of documents to fetch.
	 */
	private static final int MAXDOCS = 2500;

	/**
	 * whether to use temporary files (OSX ok, Windows ???)
	 */
	private static final boolean USETEMPFILES = false;

	private String base;

	/**
	 * citeseerx oai2 url
	 */
	private String baseurl = "https://citeseerx.ist.psu.edu/oai2?verb=ListRecords&%s";

	/**
	 * OAI prefix to use (language = OAI Dublin Core)
	 */
	private String initquery = "metadataPrefix=oai_dc";

	/**
	 * resumption token query key
	 */
	private String resumequery = "resumptionToken=";

	/**
	 * out-of-box driver for OAI fetcher.
	 * 
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		String base = "C:/Users/Administrator/Dropbox/Data/CiteSeerx/oaidump-";
		// base = args[0];
		CsxOaiFetcher cf = new CsxOaiFetcher(base);
		cf.fetch();
	}

	/**
	 * init fetcher with destination file base (including "root" name of the
	 * files to be appended a running index and xml extension)
	 * 
	 * @param base
	 */
	public CsxOaiFetcher(String base) {
		super();
		this.base = base;
	}

	/**
	 * fetch documents from OAI2 site.
	 * 
	 * @param downloads
	 */
	private void fetch() {

		String resumptionToken = null;
		int k = getLastValidFile();
		try {
			for (int i = k; i < MAXDOCS; i++) {
				String filename = base + i + ".xml";
				File f = new File(filename);
				if (!f.exists() || getResumptionToken(filename) == null) {
					URL u = null;
					if (i == 0) {
						u = new URL(String.format(baseurl, initquery));
					} else {
						u = new URL(String.format(baseurl, resumequery
								+ resumptionToken));
					}
					System.out.println(u);
					if (!urlToFile(u, filename)) {
						System.out.println("retrying...");
						// try a second time
						if (!urlToFile(u, filename)) {
							System.out.println("retrying #2...");
							if (!urlToFile(u, filename)) {
								System.out.println("deleting " + filename);
								new File(filename).delete();
								return;
							}
						}
					}
				} else {
					System.out.println(filename + " exists + valid");
				}
				resumptionToken = getResumptionToken(filename);
				if (resumptionToken == null) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * gets the last file that has a resumption token
	 * 
	 * @return
	 */
	private int getLastValidFile() {
		int x = base.lastIndexOf('/');
		final String basefile = base.substring(x + 1);
		String dir = base.substring(0, x);
		String[] ls = new File(dir).list(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				return name.startsWith(basefile);
			}
		});
		final Pattern p = Pattern.compile("(\\d+)");
		// sort files by numeric values
		Arrays.sort(ls, new Comparator<String>() {

			public int compare(String o1, String o2) {
				Matcher m1 = p.matcher(o1);
				Matcher m2 = p.matcher(o2);
				if (m1.find() && m2.find()) {
					int a = Integer.parseInt(m1.group(1));
					int b = Integer.parseInt(m2.group(1));
					return a < b ? -1 : a == b ? 0 : 1;
				}
				return 0;
			}
		});
		// iterate backwards and check resumption tokens
		for (int i = ls.length - 1; i >= 0; i--) {
			if (getResumptionToken(dir + "/" + ls[i]) != null) {
				Matcher m = p.matcher(ls[i]);
				if (m.find())
					return Integer.parseInt(m.group(1));
			}
		}
		return 0;
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

	/**
	 * reads from url to output stream
	 */
	private void urlToStream(URL url, OutputStream os) throws IOException {
		InputStream is = url.openStream();
		byte[] buf = new byte[1048576];
		int n = is.read(buf);
		while (n != -1) {
			os.write(buf, 0, n);
			n = is.read(buf);
		}
	}

	/**
	 * write content of url to file via saveURL
	 */
	private boolean urlToFile(URL url, String filename) throws IOException {
		File f;
		if (USETEMPFILES) {
			f = File.createTempFile("citeseerx.downloader-", "");
			f.deleteOnExit();
		} else {
			f = new File(filename);
		}
		FileOutputStream os = new FileOutputStream(f);
		try {
			urlToStream(url, os);
			os.close();
			if (USETEMPFILES) {
				f.renameTo(new File(filename));
			}
			return true;
		} catch (IOException e) {
			System.out.println("error while downloading: " + e);
			f.delete();
			return false;
		}

	}
}
