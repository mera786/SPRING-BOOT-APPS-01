package com.app.helper;

import com.app.entities.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static boolean checkExcelFormats(MultipartFile file){
        String contentType = file.getContentType();
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
        return true;
    }else {
        return false;
    }
}


public static List<Product> convertExcelToList(InputStream is){
    List<Product> list = new ArrayList<>();

    try {

        XSSFWorkbook workbook = new XSSFWorkbook(is);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        int rowNum =0;
        while (iterator.hasNext()){
            Row row = iterator.next();

            if(rowNum==0){
                rowNum++;
                continue;
            }

            Iterator<Cell> cells = row.iterator();
            int cellId=0;
            Product product = new Product();
            while (cells.hasNext()){
                Cell cell = cells.next();
                switch (cellId){
                    case 0:
                        product.setProductId((int)
                                cell.getNumericCellValue());
                        break;


                    case 1:

                        product.setProductName(cell.getStringCellValue());
                        break;

                    case 2:

                        product.setProductDetails(cell.getStringCellValue());
                        break;

                    case 3:

                        product.setPrice(cell.getNumericCellValue());
                        break;
                    default:
                        break;

                }
                cellId++;
            }
            list.add(product);
        }

    }catch (Exception e){
        e.printStackTrace();
    }

    return list;
}
}
