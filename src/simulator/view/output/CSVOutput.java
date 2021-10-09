package simulator.view.output;

import java.io.File;
import java.io.IOException;

import java.util.Observable;

import simulator.csv.CSVLog;
import simulator.csv.CSVRow;
import simulator.csv.CSVWriter;
import simulator.model.Model;

public class CSVOutput extends AbstractOutput {

	public static final int COLUMN_DATE        = 0;
	public static final int COLUMN_ABS_GLUCOSE = 1;
	public static final int COLUMN_REL_GLUCOSE = 2; // Not supported by the model?!?!
	public static final int COLUMN_REL_INSULIN = 3;
	public static final int COLUMN_INJECTION   = 4;
	public static final int COLUMN_FOOD        = 5;

	private CSVWriter writer;
	private CSVLog log;
	private boolean update;

	public CSVOutput(Model model,File file,boolean update) {
		super(model);
		this.log = new CSVLog();
		this.writer = new CSVWriter(file);
		this.update = update;
		super.model.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		try {
			CSVRow row = new CSVRow();

			row.add(super.model.getTime().toString());
			row.add(String.valueOf(super.model.getAbsoluteGlucose()));
			row.add(String.valueOf(super.model.getInjectedInsulin()));

			this.log.addRow(row);

			if(this.update) {
				this.writer.toCSV(this.log,true);
				this.log = new CSVLog();
			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}

	@Override
	public void finalize() {
		if(!this.update) {
			try {
				this.writer.toCSV(this.log,true);
			} catch(IOException e) {}
		}
	}

}