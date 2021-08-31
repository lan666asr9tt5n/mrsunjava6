package simulator.view.input;

import java.io.File;
import java.io.IOException;

import simulator.controller.AbstractController;
import simulator.csv.CSVFile;
import simulator.csv.CSVReader;
import simulator.csv.CSVResult;

import static simulator.controller.AbstractController.FoodType;
import static simulator.controller.AbstractController.InsulinType;;

public class CSVInput extends AbstractInput {

	public static final int COLUMN_DATE           = 0;
	public static final int COLUMN_INPUTTYPE      = 1;
	public static final int COLUMN_RECEIPTS       = 2;
	public static final int COLUMN_INSULIN_AMOUNT = 3;

	private CSVReader reader;

	public CSVInput(AbstractController controller,File file) {
		super(controller);
		this.reader = new CSVReader(file,CSVFile.SEPERATOR_SEMICOLON,CSVFile.TEXT_QUOTATION);
	}

	@Override
	public void workLoop() {
		try {
			CSVResult res = this.reader.getCSVResult();

			while(res.hasNext()) {
				String type = res.get(COLUMN_INPUTTYPE);
				String receipts = res.get(COLUMN_RECEIPTS);
				long date = Long.parseLong(res.get(COLUMN_DATE));

				if(type.equalsIgnoreCase("food")) {
					FoodType food = null;

					if(receipts.equalsIgnoreCase(FoodType.LOW.toString())) {
						food = FoodType.LOW;
					} else if(receipts.equalsIgnoreCase(FoodType.MED.toString())) {
						food = FoodType.MED;
					} else if(receipts.equalsIgnoreCase(FoodType.HIGH.toString())) {
						food = FoodType.HIGH;
					}

					if(food != null) {
						super.controller.addFood(food,date);
					}
				} else if(type.equalsIgnoreCase("insulin")) {
					InsulinType insulin = null;
					double amount = Double.parseDouble(res.get(COLUMN_INSULIN_AMOUNT));

					if(receipts.equalsIgnoreCase(InsulinType.LONG.toString())) {
						insulin = InsulinType.LONG;
					} else if(receipts.equalsIgnoreCase(InsulinType.RAPID.toString())) {
						insulin = InsulinType.RAPID;
					} else if(receipts.equalsIgnoreCase(InsulinType.SHORT.toString())) {
						insulin = InsulinType.SHORT;
					}

					if(insulin != null) {
						super.controller.addInsulin(insulin,amount);
					}
				}

				res.next();
			}
		} catch(IOException e) {
			e.printStackTrace(System.err);
		}
	}

}