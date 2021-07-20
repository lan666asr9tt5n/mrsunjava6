package simulator.csv;

import java.util.Iterator;

public interface CSVFile extends Iterator<CSVRow> {

	public static final int SEPERATOR_SEMICOLON = 59;
	public static final int SEPERATOR_COMMA     = 44;
	public static final int SEPERATOR_COLON     = 58;
	public static final int SEPERATOR_TAB       = 9;
	public static final int SEPERATOR_SPACE     = 32;
	public static final int TEXT_QUOTATION      = 34;
	public static final int TEXT_APOSTROPHE     = 39;

	public void add(String value);

	public void addRow(CSVRow row);

	public int getSeperator();

	public int getTextSeperator();

	public void nextRow();

	public void reset();

	public void setSeperator(int seperator);

	public void setTextSeperator(int text);

}