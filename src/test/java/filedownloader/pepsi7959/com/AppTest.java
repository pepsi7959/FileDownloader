package filedownloader.pepsi7959.com;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * 
	 */
	public void testApp() {
		assertTrue(true);
	}

	public void testReadUrlFromExcel() {
		String Filename = "/Users/narongsak.mala/Github/FileDownloader/pepsi7959.com/resources/Photo Un Ai Rak  รายการที่อนุมัติเผยแพร่ แต่งกายสวย.xlsx";
		int sheetNumber = 0;
		int skipRows = 1;
		int colToRead[] = { 0 };

		LinkedList<String> listofURLs = App.readUrlFromExcel(Filename, sheetNumber, skipRows, colToRead);
		assertTrue( listofURLs != null && listofURLs.size() > 0 );
		
		Filename = "/Users/narongsak.mala/Github/FileDownloader/pepsi7959.com/resources/Photo Un Ai Rak  รายการที่อนุมัติเผยแพร่ บรรยายกาศง.xlsx";
		listofURLs = App.readUrlFromExcel(Filename, sheetNumber, skipRows, colToRead);
		assertTrue( listofURLs != null && listofURLs.size() > 0 );

		Filename = "/Users/narongsak.mala/Github/FileDownloader/pepsi7959.com/resources/Photo Un Ai Rak  รายการที่อนุมัติเผยแพร่ ภาพความประ.xlsx";
		listofURLs = App.readUrlFromExcel(Filename, sheetNumber, skipRows, colToRead);
		assertTrue( listofURLs != null && listofURLs.size() > 0 );

		String url = "https://cmsphotounairak2018.phralan.in.th/files/original/1550823604.jpeg";
		Pattern pattern = Pattern.compile("\\w+.(jpeg|jpg)$");
		Matcher matcher = pattern.matcher(url);
		if( matcher.find() ) {
			String output = "/tmp/output/"+matcher.group();
			System.out.println("Start download file: " + output + " from url: " + url);
		}else {
			System.out.println("Not found");
		}
	}
}
