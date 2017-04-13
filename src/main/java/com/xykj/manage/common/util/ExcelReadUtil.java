package com.cmsg.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cmsg.pojo.CarParams;
import com.cmsg.pojo.CarParamsValue;

public class ExcelReadUtil {
	
	@SuppressWarnings({ "resource", "deprecation" })
	public static String readExcel(List<CarParams> lt,String filepath, String filename, int startrow, int startcol, int sheetnum) {
		try {
			List<CarParamsValue> carValueLt = new ArrayList<>();
			XSSFWorkbook wb = new XSSFWorkbook(filepath + filename);
			wb.getSheetAt(sheetnum);
			//读取第一张sheet
			XSSFSheet sheet = wb.getSheetAt(sheetnum);
			
			//取当前表单中的总行数
			int rowNum = sheet.getLastRowNum() + 1;//取得最后一行的行号

			CarParams carParam = null;
			CarParamsValue carParamsValue = null;
			XSSFRow curRow = null;
			//行循环开始
			for (int i= startrow; i < rowNum; i++) {
				int curColumnIndex = 0;
				curRow = sheet.getRow(i);
				
				for (int j = 0; j < lt.size(); j++) {
					carParam = lt.get(j);
					carParamsValue = new CarParamsValue();
					carParamsValue.setGroup(carParam.getGroup());
					List<String> keys = carParam.getParams();
					List<Map<String, Object>> values = new ArrayList<>();
					for (int k = 0; k < keys.size(); k++) {
						Map<String, Object> m = new HashMap<>();
						if (curRow.getCell(curColumnIndex).getCellTypeEnum() == CellType.NUMERIC) {
							m.put(keys.get(k), curRow.getCell(curColumnIndex).getNumericCellValue());
						}else{
							m.put(keys.get(k), curRow.getCell(curColumnIndex).getStringCellValue());
						}
						
						
						values.add(m);
						curColumnIndex ++;
					}
					carParamsValue.setParams(values);
					carValueLt.add(carParamsValue);
				}
			}
			
			System.out.println(JsonUtils.objectToJson(carValueLt));

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
}
