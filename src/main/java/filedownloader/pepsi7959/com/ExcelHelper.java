package filedownloader.pepsi7959.com;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelHelper {

	public ExcelHelper() {
		
	}
	
	protected static Iterator<Cell> skipCell(Row row, int skip_col) {
		Iterator<Cell> cellIterator = row.cellIterator();
		for(int i = 0; i < skip_col; i++) {
			if(cellIterator.hasNext())
				cellIterator.next();
		}
		return cellIterator;
	}
	
	protected static Iterator<Row> skipRow(Sheet sheet, int skip_row) {
		Iterator<Row> rowIterator = sheet.iterator();
		for(int i = 0; i < skip_row; i++) {
			if( rowIterator.hasNext() )
				rowIterator.next();
			else
				System.out.println("Warning: cannot skip row ");
		}
		return rowIterator;
	}
	
}
