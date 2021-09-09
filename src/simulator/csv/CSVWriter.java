package simulator.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

	private File output;

	public CSVWriter(File output) {
		this.output = output;
	}

	public void toCSV(CSVFile file,boolean append) throws IOException {
		BufferedWriter out = null;

		try {
			out = new BufferedWriter(
					new FileWriter(output,append)
			);
			file.reset();

			while(file.hasNext()) {
				out.write(file.next().toString());
			}
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}

}
