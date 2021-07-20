package simulator.csv;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class CSVRow extends Vector<String> {

	private static final long serialVersionUID = 1L;

	private int seperator;
	private int textSeperator;

	public CSVRow(Collection<String> rows,int seperator,int textSeperator) {
		super();

		if(rows != null) {
			for(String s : rows) {
				add(s);
			}
		}
		this.seperator = seperator;
		this.textSeperator = textSeperator;
	}

	public CSVRow(Collection<String> rows) {
		this(rows,CSVFile.SEPERATOR_SEMICOLON,CSVFile.TEXT_QUOTATION);
	}

	public CSVRow() {
		this(null);
	}

	public void setSeperator(int seperator) {
		this.seperator = seperator;
	}

	public int getSeperator() {
		return seperator;
	}

	public void setTextSeperator(int textSeperator) {
		this.textSeperator = textSeperator;
	}

	public int getTextSeperator() {
		return textSeperator;
	}

	public boolean isNumeric(String s) {
		boolean res = false;

		try {
			Integer.parseInt(s);
			res = true;
		} catch(NumberFormatException e) {
			res = false;
		}

		return res;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = this.iterator();

		while(it.hasNext()) {
			String value = it.next();

			if(isNumeric(value)) {
				buffer.append(value);
			} else {
				buffer.append(
						(char)this.textSeperator + 
						value +
						(char)this.textSeperator
				);
			}

			if(it.hasNext()) {
				buffer.append((char)this.seperator);
			}
		}

		buffer.append("\n");

		return buffer.toString();
	}

}