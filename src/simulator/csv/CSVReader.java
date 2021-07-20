package simulator.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

public class CSVReader {

	private File input;
	private int seperator;
	private int textSeperator;

	public CSVReader(File input,int seperator,int textSeperator) {
		this.input = input;
		this.seperator = seperator;
		this.textSeperator = textSeperator;
	}

	public CSVResult getCSVResult() throws IOException {
		CSVResult result = null;
		BufferedReader in = null;

		try {
			String line = "";
			Vector<CSVRow> rows = new Vector<CSVRow>();

			in = new BufferedReader(
					new FileReader(this.input)
			);

			while((line=in.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line,String.valueOf((char)this.seperator));
				Vector<String> content = new Vector<String>();

				while(st.hasMoreElements()) {
					content.add(st.nextToken());
				}
				rows.add(new CSVRow(content,this.seperator,this.textSeperator));
			}

			result = new CSVResult(rows);
		} finally {
			if(in != null) {
				in.close();
			}
		}

		return result;
	}

}