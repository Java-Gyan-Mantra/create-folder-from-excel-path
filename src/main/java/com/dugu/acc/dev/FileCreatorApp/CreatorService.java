package com.dugu.acc.dev.FileCreatorApp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreatorService {

	@SuppressWarnings("resource")
	public List<String> getDirectory(String xlxFileLocation) throws IOException {
		List<String> paths = new ArrayList<String>();
		XSSFWorkbook workBook = new XSSFWorkbook(xlxFileLocation);
		XSSFSheet sheet = workBook.getSheetAt(0);
		int totalRows = sheet.getPhysicalNumberOfRows();
		System.out.println("Total Specified path find :" + (totalRows - 1));
		Row row;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = (Row) sheet.getRow(i);
			String path = row.getCell(1).getStringCellValue();
			paths.add(path);
		}
		return paths;
	}

	public void createFile(List<String> paths) {
		for (String path : paths) {
			File file = new File(path);
			if (!file.exists()) {
				if (file.mkdir()) {
					System.out.println("Directory is created! with path : "
							+ "(" + path + ")");
				} else {
					System.out.println("Failed to create directory!");
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		CreatorService service = new CreatorService();
		service.createFile(service
				.getDirectory("C:/Users/basanta.kumar.hota/Desktop/info.xlsx"));

	}
}
