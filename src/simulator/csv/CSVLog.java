package simulator.csv;

import java.util.Vector;

public class CSVLog implements CSVFile {

	private Vector<CSVRow> table;
	private int seperator;
	private int textSeperator;
	private int current;

	public CSVLog() {
		this.table = new Vector<CSVRow>();
		this.current = 0;
	}

	@Override
	public void reset() {
		this.current = 0;
	}

	@Override
	public void add(String value) {
		this.table.get(this.table.size() - 1).add(value);
	}

	@Override
	public void addRow(CSVRow row) {
		this.table.add(row);
		nextRow();
	}

	@Override
	public int getSeperator() {
		return this.seperator;
	}

	@Override
	public int getTextSeperator() {
		return this.textSeperator;
	}

	@Override
	public void nextRow() {
		this.table.add(new CSVRow());
	}

	@Override
	public void setSeperator(int seperator) {
		this.seperator = seperator;
	}

	@Override
	public void setTextSeperator(int text) {
		this.textSeperator = text;
	}

	@Override
	public boolean hasNext() {
		return this.current < this.table.size();
	}

	@Override
	public CSVRow next() {
		return (hasNext()) ? this.table.get(this.current++) : null;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Removing not allowed!");
	}

}