package filedownloader.pepsi7959.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Hello world!
 *
 */
public class App extends ExcelHelper {
	static Logger log = Logger.getLogger(App.class);

	public App() {
		BasicConfigurator.configure();
	}

	public static LinkedList<String> readUrlFromExcel(String excelFile, int sheetNumber, int skipRows,
			int[] colToRead) {

		LinkedList<String> listofUrl = new LinkedList<String>();
		System.out.println("\nStarting read file " + excelFile);
		FileInputStream file;

		try {

			file = new FileInputStream(new File(excelFile));
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(sheetNumber);
			Iterator<Row> rowIterator = skipRow(sheet, skipRows);

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// TODO: for loop to read column
				Cell cell = row.getCell(colToRead[0]);
				String data = cell.getStringCellValue();
				System.out.println("data : " + data);
				listofUrl.add(data);
			}

			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listofUrl;
	}

	public static int downloader(LinkedList<String> listofFileName, String outputPath) {
		System.out.println("\nStarting loading...");
		String url = "";
		String output = "";
		int status = 0;

		try {
			Iterator<String> iters = listofFileName.iterator();
			while (iters.hasNext()) {
				url = iters.next();
				Pattern pattern = Pattern.compile("\\w+.(jpeg|jpg|JPG)$");
				Matcher matcher = pattern.matcher(url);
				if( matcher.find() ) {
					output = outputPath + "/" + matcher.group();
					System.out.println("Start download file: " + output + " from url: " + url);
					FileUtils.copyURLToFile(new URL(url), new File(output));
					status++;
				}else {
					System.out.println("Not found + " + url);
				}
			}
		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}


	/* SOLVED ISSUES */
	/*
	 * link - log4j:
	 * https://crunchify.com/java-how-to-configure-log4j-logger-property-correctly/
	 * - https://github.com/netty/netty/issues/7769
	 */
	public static void main(String[] args) {

		BasicConfigurator.configure();
		String Filename = "/Users/narongsak.mala/Github/FileDownloader/pepsi7959.com/resources/Photo Un Ai Rak  รายการที่อนุมัติเผยแพร่ แต่งกายสวย.xlsx";
		int sheetNumber = 0;
		int skipRows = 1;
		int colToRead[] = { 0 };
		String outputPath = "/tmp/output";
		LinkedList<String> listofURLs = App.readUrlFromExcel(Filename, sheetNumber, skipRows, colToRead);
		int numofFiles = downloader(listofURLs, outputPath +"/1");
		
		Filename = "/Users/narongsak.mala/Github/FileDownloader/pepsi7959.com/resources/Photo Un Ai Rak  รายการที่อนุมัติเผยแพร่ บรรยายกาศง.xlsx";
		listofURLs = App.readUrlFromExcel(Filename, sheetNumber, skipRows, colToRead);
		numofFiles += downloader(listofURLs ,outputPath +"/2");
		
		Filename = "/Users/narongsak.mala/Github/FileDownloader/pepsi7959.com/resources/Photo Un Ai Rak  รายการที่อนุมัติเผยแพร่ ภาพความประ.xlsx";
		listofURLs = App.readUrlFromExcel(Filename, sheetNumber, skipRows, colToRead);

		numofFiles += downloader(listofURLs, outputPath +"/3");
		
		System.out.println("\n\n======================================\n");
		System.out.println("Number of Total files: " + numofFiles);
		
		
	}
}
