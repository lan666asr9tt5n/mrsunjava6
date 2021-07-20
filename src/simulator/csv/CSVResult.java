package simulator.csv;

import java.util.Iterator;
import java.util.Vector;

public class CSVResult implements Iterator<CSVRow> {

	private Vector<CSVRow> table;
	private int current;

	public CSVResult(Vector<CSVRow> rows) {
		this.table = rows;
		this.current = 0;
	}

	public String get(int column) {
		return this.table.get(this.current).get(column);
	}

	public void reset() {
		this.current = 0;
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