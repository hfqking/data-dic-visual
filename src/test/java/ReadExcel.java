import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;

public class ReadExcel {
    public static void main(String[] args) {
        // 此处为我创建Excel路径：E:/zhanhj/studysrc/jxl下
//        File file = new File("E:\\Coding\\workspace\\odpsDemo\\file\\test.xls");
//        File file = new File("E:\\Coding\\workspace\\odpsDemo\\file\\dim_rules.xls");
//        List excelList = readExcel2List(file);
        File file = new File("C:\\Users\\houfangqing\\Desktop\\123.xls");
        String name = file.getName();
        System.out.println(name);
        List list2 = readExcel2ListMap("C:\\Users\\houfangqing\\Desktop\\123.xls");
//        List list2 = readExcel2List(new File("C:\\Users\\houfangqing\\Desktop\\123.xls"));
        System.out.println(list2);
        List<Map<String,Object>> arrayList = new ArrayList<>();
        List<List<Map<String,Object>>> list = new ArrayList<>();
        int begin = 0;
        int end = 0;
        for (int i = 0; i < list2.size(); i++) {
            Map map = (Map)list2.get(i);
            if(map.get("column_name").equals("")){
                System.out.println(i);
                begin = end;
                 end = i;
                 if(begin != end){
                     System.out.println("截取"+begin+"到"+end);
                     list.add(list2.subList(begin,end));
                     System.out.println(list2.subList(begin,end));
                 }
            }
            if(i == list2.size()-1){
                System.out.println("截取"+end+"到"+(list2.size()));
                list.add(list2.subList(end,list2.size()));
            }
        }
        List<Map<String,Object>> mapList = new ArrayList<>();
        String project_name = "xlk";
        for (List l : list) {
            String table_name  = "";
            String table_comment = "";
            for (Map<String,Object> map : (List<Map<String,Object>>)l) {
                if(map.get("column_name").equals("")){
                    table_name = map.get("table_or_column_name").toString();
                    table_comment = map.get("table_or_column_comment").toString();
                    System.out.println("table_name==>"+map.get("table_or_column_name"));
                }
            }
            for (Map<String,Object> map : (List<Map<String,Object>>)l) {
                Map<String, Object> hashMap = new HashMap<>();

                if(map.get("column_name").equals("")){
                    continue;
                }else {
                    hashMap.put("project_name",project_name);
                    hashMap.put("table_name",table_name);
                    hashMap.put("table_comment",table_comment);
                    hashMap.put("data_type",map.get("data_type").toString());
                    hashMap.put("column_name",map.get("column_name").toString());
                    hashMap.put("column_comment",map.get("table_or_column_comment").toString());
                }
                mapList.add(hashMap);
            }
        }
        System.out.println(mapList);
//        for (int i = 0; i < excelList.size(); i++) {
//            List list = (List) excelList.get(i);
//            System.out.println(list);
//            for (int j = 0; j < list.size(); j++) {
//                System.out.print(list.get(j)+",");
//            }
//            System.out.println();
//        }

    }
    // 去读Excel的方法readExcel，该方法的入口参数为一个File对象
    public static List readExcel2List(File file) {
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                List<List> outerList=new ArrayList<List>();
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 0; i < sheet.getRows(); i++) {
                    List innerList=new ArrayList();
                    // sheet.getColumns()返回该页的总列数
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
//                        if(cellinfo.isEmpty()){
//                            continue;
//                        }
                        innerList.add(cellinfo);
//                        System.out.print(cellinfo);
                    }
                    outerList.add(i, innerList);
//                    System.out.println();
                }
                return outerList;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    
 // 去读Excel的方法readExcel，该方法的入口参数为一个File对象
    public static List<Map<String,Object>> readExcel2ListMap(String str) {
        try {
        	File file = new File(str);
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                List<List> dataList=new LinkedList<List>();
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 0; i < sheet.getRows(); i++) {
                    List innerList=new LinkedList();
                    // sheet.getColumns()返回该页的总列数
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
//                        if(cellinfo.isEmpty()){
//                            continue;
//                        }
                        innerList.add(cellinfo);
//                        System.out.print(cellinfo);
                    }
                    dataList.add(i, innerList);
//                    System.out.println();
                }
//                return dataList;
                
                List<Map<String, Object>> arrayList = new LinkedList<>();
                //tou
                List<String> cloumnList = dataList.get(0);
                
              //数据    list 
        		for (int i = 0; i < dataList.size(); i++) {
        			if (i != 0) {
        				Map<String, Object> map = new HashMap<>();
        				List list = dataList.get(i);
        				for (int j = 0; j < list.size(); j++) {
        					String key = cloumnList.get(j);
        					map.put(key, StringUtils.trim(list.get(j).toString()));
        				}
        				arrayList.add(map);
        			}
        		}
        		return arrayList;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
