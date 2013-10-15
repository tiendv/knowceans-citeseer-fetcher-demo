/*
 * Created on Feb 3, 2010
 */
package org.knowceans.citeseer.fetcher;

/**
 * represents the data of a CSX document
 * 
 * @author gregor heinrich (gregor :: arbylon . net)
 * @date 20100203 / 20100406
 * @version draft (quickly written but functional)
 */
public class CsxDocument {

	String id;
	String date;
	String title;
	String authors;
	String tags;
	String language;
	String description;
	String relations;
        String publisher;

	@Override
	public String toString() {
		return String.format("\nID=%s\nT=%s\nA=%s\nD=%s\nC=%s,L=%s\nR=%s\n",
				id, title, authors, description, tags, language, relations,publisher);
	}

}
