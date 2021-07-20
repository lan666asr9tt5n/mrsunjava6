package simulator.csv;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class CSVTest {

	/**
	 * Input:
	 * Date;Input-type(meal & beverage or insulin);(e.g. Cola or Shortacting)[;Amount of insulin]
	 * 
	 * Output:
	 * Date;Absolute Glucose;Relative Glucose;Relative Insulin;Injection?;Meal or beverage;Glucose Level
	 * 
	 */

	public static void main(String[] args) {
		try {
			File testFile = new File("temp.csv");
			File csvFile = new File("src/simulator/csv/test.csv");
			CSVReader input = new CSVReader(csvFile,CSVFile.SEPERATOR_SEMICOLON,CSVFile.TEXT_QUOTATION);
			CSVWriter output = new CSVWriter(testFile);
			Vector<String> data = new Vector<String>();
			CSVLog log = new CSVLog();
			CSVResult result;

			testFile.deleteOnExit();

			data.add("1");
			data.add("2");
			data.add("t");
			data.add("4");
			data.add("5");

			log.addRow(new CSVRow(data));

			log.add("a");
			log.add("b");
			log.add("1");
			log.add("d");
			log.add("e");

			while(log.hasNext()) {
				System.out.print(log.next().toString());
			}

			output.toCSV(log);
			System.out.println("The application will end in approx. 10 seconds!");
			System.out.println("Please check if the file: " + testFile.getAbsolutePath() + " has been created!");
			System.out.println("This file will automatically been deleted after application ending!");
			Thread.sleep(10000);

			result = input.getCSVResult();

			if(result != null) {
				while(result.hasNext()) {
					System.out.println(
							"1st: " + result.get(0) + ";2nd: " + result.get(1) + ";3rd: " + result.get(2)
					);
					result.next();
				}
			} else {
				System.out.println("No result set received!");
			}
		} catch(IOException e) {
			e.printStackTrace(System.err);
		} catch(InterruptedException e) {
			e.printStackTrace(System.err);
		}
	}

}