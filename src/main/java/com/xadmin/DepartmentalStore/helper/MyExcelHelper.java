package com.xadmin.DepartmentalStore.helper;

import com.xadmin.DepartmentalStore.bean.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyExcelHelper {

    public static boolean checkExcel(MultipartFile file) {

        String dataType = file.getContentType();

        if(dataType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        }

        else {
            return false;
        }

    }


//   will convert excel to list of products

    public static List<Product> convertExcel(InputStream inp)
    {
        List<Product> list = new ArrayList<>();

        try {

            XSSFWorkbook sheets = new XSSFWorkbook(inp);
            XSSFSheet data = sheets.getSheet("data");

            int rowNum=0;
            Iterator<Row> it = data.iterator();

            while(it.hasNext())
            {
                Row row =  it.next();

                if(rowNum == 0)
                {
                    rowNum++;continue;
                }

                Iterator<Cell> cells = row.iterator();
                int cellId = 0;

                Product p = new Product();

                while(cells.hasNext())
                {
                    Cell cell = cells.next();

                    switch(cellId)
                    {
                        case 0:
                            p.setProductId((long) cell.getNumericCellValue());
                            break;

                        case 1:
                            p.setProductName(cell.getStringCellValue());
                            break;

                        case 2:
                            p.setProductDesc((cell.getStringCellValue()));
                            break;

                        case 3:
                            p.setPrice(cell.getNumericCellValue());
                            break;

                        case 4:
                            p.setCount((int) cell.getNumericCellValue());
                            break;

                        default:
                            break;
                    }

                    cellId++;
                }

                list.add(p);
            }


        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return list;
    }

}
