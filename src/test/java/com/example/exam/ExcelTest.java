package com.example.exam;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelTest {
    @Test
    void f01() throws IOException {
        FileInputStream fis = new FileInputStream("VUE试题2007.xlsx");
        //获取xlsx文件的执行对象
        Workbook workbook = new XSSFWorkbook(fis);
        //获取第一个工作表
        Sheet sheet = workbook.getSheetAt(0);
        //遍历每一行
        for (Row row : sheet) {
            //遍历每一列
            for (Cell cell : row) {
                //获取单元格的值
                String cellValue = cell.toString();
                //打印单元格
                System.out.println(cellValue + "\t");
            }
            //换行
            System.out.println();
        }
    }

    @Test
    void f02() throws IOException {
        FileInputStream fis = new FileInputStream("VUE试题.xls");
        //获取xlsx文件的执行对象
        Workbook workbook = new HSSFWorkbook(fis);
        //获取第一个工作表
        Sheet sheet = workbook.getSheetAt(0);
        //遍历每一行
        for (Row row : sheet) {
            //遍历每一列
            for (Cell cell : row) {
                //获取单元格的值
                String cellValue = cell.toString();
                //打印单元格
                System.out.println(cellValue+"\t");
            }
            //换行
            System.out.println();
        }
    }
}
