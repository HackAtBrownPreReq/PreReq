import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.stage.Stage;

public class CoursesCreator {
	
	public static ArrayList<Course> listOfCourses;
	
	public static InputStream ExcelFileToRead;
	public static XSSFWorkbook  wb;
	public static XSSFWorkbook test;
	public static XSSFSheet sheet;
	

	
    public void start(Stage stage) throws Exception {
		Control control = new Control();
		Scene scene = new Scene(control.getPane(), Constants.STAGE_WIDTH, Constants.STAGE_HEIGHT);
		stage.setScene(scene);
		stage.show();
	}
	
    public static Course findCourse(String code) {
    	for (int i = 0; i < listOfCourses.size(); i++) {
    		if (listOfCourses.get(i).code.equals(code)) {
    			return listOfCourses.get(i);
    		}
    	}
    	return new Course("Bob");
    }
    
	public static void doEverything() throws IOException
	{
		ExcelFileToRead = new FileInputStream("src/ClassesXCel(1).xlsx");
		wb = new XSSFWorkbook(ExcelFileToRead);
		
		test = new XSSFWorkbook(); 
		
		sheet = wb.getSheetAt(0);
		
		XSSFRow row; 
		XSSFCell cell;

		Iterator rows = sheet.rowIterator();

		outer: while (rows.hasNext())
		{
			row=(XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			int colNum = 1;
			String[] myrow = new String[4];
			ArrayList<String> prereqs = new ArrayList<String>();
			
			while (cells.hasNext())
			{
				cell=(XSSFCell) cells.next();
				if (colNum == 1 && cell.getStringCellValue().equals("")) {
					System.out.println("end of sheet");
					break outer;
				}
				if (colNum == 2) {
					System.out.println("colNUm is 2 and cell value is " + cell.getStringCellValue());
					Course[] bob = createCourseFromSheet(cell.getStringCellValue());
					if (bob.length > 0) {
						listOfCourses.add(bob[0]);
					}
				}
				colNum++;
			}
		}
	}
	
	public static Course[] stringsToCourse(String[] courseCodes) throws IOException {
		Course[] result = new Course[courseCodes.length];
		for (int i = 0; i < courseCodes.length; i++) {
			if (inCourseList(courseCodes[i]).length > 0) {
				Course temp = inCourseList(courseCodes[i])[0];
			}
			if ((inCourseList(courseCodes[i]).length > 0)) {
				result[i] = inCourseList(courseCodes[i])[0];
			}
			else result[i] = createCourseFromSheet(courseCodes[i])[0];
			
		}
		
		return result;
	}
	
	public static Course[] inCourseList(String courseCode) {
			for (Course c : listOfCourses) {
				if (c.isReal == false) {
					listOfCourses.remove(c);
				}
				if (c.code.equals(courseCode)) {
					return new Course[] {c};
				}
			}
			System.out.println("we returned null on line 105");
		return new Course[0];
	}
	
	public static Course[] createCourseFromSheet(String code) throws IOException {
		return readXLSXFile(code);
		
	}
	
	public static boolean alreadyExists(String courseCode) {
		for (Course c : listOfCourses) {
			if (c.code.equals(courseCode)) {
				return true;
			}
		}
		return false;
	}
	
	public static Course[] readXLSXFile(String code) throws IOException
	{
		if (inCourseList(code).length == 0) {
			return new Course[0];
		}
		
		XSSFRow row; 
		XSSFCell cell;

		Iterator rows = sheet.rowIterator();

		while (rows.hasNext())
		{
			row=(XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			int colNum = 1;
			String[] myrow = new String[4];
			ArrayList<String> prereqs = new ArrayList<String>();
			
			while (cells.hasNext())
			{
				cell=(XSSFCell) cells.next();
				switch(colNum) {
				case 1: 
				case 2: myrow[colNum-1] = cell.getStringCellValue();
						break;
				case 3: 
				case 4: myrow[colNum-1] = cell.getNumericCellValue() + "";
						break;
				default: prereqs.add(cell.getStringCellValue());
				}
				
				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
				{
					System.out.print(cell.getStringCellValue()+" ");
				}
				else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
				{
					System.out.print(cell.getNumericCellValue()+" ");
				}
				else
				{
					//U Can Handle Boolean, Formula, Errors
				}
				colNum++;
			}
			if (myrow[1].equals(code) && !alreadyExists(code)) {
				return new Course[] {new Course(myrow[0], myrow[1], Double.valueOf(myrow[2]), Double.valueOf(myrow[3]), stringsToCourse(prereqs.toArray(new String[0])))};
			}
			System.out.println();
		}
		System.out.println("we returned null on line 166");
		return new Course[0];
	
	}
	
}
