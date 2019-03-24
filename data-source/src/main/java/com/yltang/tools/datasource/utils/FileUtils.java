package com.yltang.tools.datasource.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.csvreader.CsvReader;

public final class FileUtils {
	
	private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";
    
    /**
     * 获取最后的行数
     * */
    public static int getExcelRowNum(File file){
    	//读取excel文件
        InputStream is = null;
        try {
        	//获取工作薄
            is = new FileInputStream(file.getPath());
            Workbook wb = null;
            wb = getWorkbok(file, is);
            if(wb == null){
            	return 0;
            }

            //读取第一个工作页sheet
            Sheet sheet = wb.getSheetAt(0);
            if(sheet == null){
            	return 0;
            }
            
            return sheet.getLastRowNum();
        }catch(Exception e){
        	e.printStackTrace();
            return 0;
        }finally{
        	if(is != null){
        		try {
					is.close();
				} catch (IOException e) {}
        	}
        }
    }
    
    /**
     * 读取Excel标题
     * 读取横向数据表的数据，以第一行为标题
     * */
    public static List<List<Object>> readExcelTitle(File file){
    	return readExcel(file, 0, 0);
    }
	
    /**
     * 读取指定起始和结束位置的Excel数据
     * */
	public static List<List<Object>> readExcel(File file, int startIndex, int endIndex){
		//定义返回集合
        List<List<Object>> lists = new ArrayList<List<Object>>();
        //读取excel文件
        InputStream is = null;
        try {
        	//获取工作薄
            is = new FileInputStream(file.getPath());
            Workbook wb = null;
            wb = getWorkbok(file, is);
            if(wb == null){
            	return lists;
            }

            //读取第一个工作页sheet
            Sheet sheet = wb.getSheetAt(0);
            int rowIndex = 0;
            for (Row row : sheet) {
            	List<Object> rList = readRow(row);
            	if(rowIndex >= startIndex && rowIndex<= endIndex){
            		lists.add(rList);
            	}
            	rowIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lists;
	}
	
	/**
     * 判断Excel的版本,获取Workbook
     * @param in
     * @param filename
     * @return
     * @throws IOException
     */
    private static Workbook getWorkbok(File file,InputStream in) throws IOException{
        Workbook wb = null;
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
    
    private static List<Object> readRow(Row row){
    	ArrayList<Object> list = new ArrayList<Object>();
        for (Cell cell : row) {
            list.add(castCell(cell));
        }
        return list;
    }
    
    @SuppressWarnings("deprecation")
	private static Object castCell(Cell cell){
    	String value;
    	switch(cell.getCellType()){
    		case HSSFCell.CELL_TYPE_NUMERIC: // 数字
    			//如果为时间格式的内容
    			if (HSSFDateUtil.isCellDateFormatted(cell)) {      
    				//注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
    				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
    				value=sdf.format(HSSFDateUtil.getJavaDate(cell.
    				getNumericCellValue())).toString();                                 
    				break;
    			}else {
    				value = String.valueOf(cell.getNumericCellValue());
    				if(value.endsWith(".0") || value.endsWith("E10")){
    					value = new DecimalFormat("0").format(cell.getNumericCellValue());
    				}
    			}
    			break;
    		case HSSFCell.CELL_TYPE_STRING: // 字符串
    			value = cell.getStringCellValue();
    			break;
    		case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
    			value = cell.getBooleanCellValue() + "";
    			break;
    		case HSSFCell.CELL_TYPE_FORMULA: // 公式
    			value = cell.getCellFormula() + "";
    			break;
    		case HSSFCell.CELL_TYPE_BLANK: // 空值
    			value = "";
    			break;
    		case HSSFCell.CELL_TYPE_ERROR: // 故障
    			value = "非法字符";
    			break;
    		default:
    			value = "未知类型";
    			break;
    	}
    	return value;
    }
    
	public static List<String[]> csv(InputStream in, int startIndex, int endIndex) {
		List<String[]> csvList = new ArrayList<String[]>();
		if (null != in) {
			CsvReader reader = new CsvReader(in, ',', Charset.forName("GBK"));
			try {
				// 遍历每一行，若有#注释部分，则不处理，若没有，则加入csvList
				while (reader.readRecord()) {
					// 清除注释部分
					if (!reader.getValues()[0].contains("#")){
						csvList.add(reader.getValues());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			reader.close();
		}
		return csvList;
	}
    
    public static void main(String[] args) {
    	File file = new File("C:\\Users\\yltang\\Downloads\\abc.xlsx");
		List<List<Object>> title = readExcelTitle(file);
		List<List<Object>> datas = readExcel(file, 1, 5);
		System.out.println(datas);
		int line = getExcelRowNum(file);
		System.out.println(line);
    }
}
