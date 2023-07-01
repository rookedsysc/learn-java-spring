package org.example.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.MemberDTO;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Excel {
    public void load() {
        try {
            // Stream을 받아오는 가장 기본적인 형태
            FileInputStream file = new FileInputStream(new File("/Users/rookedsysc/Downloads/example.xlsx"));
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                Date dateValue = cell.getDateCellValue();
                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                String formattedDate = dateFormat.format(dateValue);
                                System.out.print(formattedDate + "\t");
                            } else {
                                double numericValue = cell.getNumericCellValue();
                                if (numericValue == Math.floor(numericValue)) {
                                    int intValue = (int) numericValue;
                                    System.out.print(intValue + "\t");
                                } else {
                                    System.out.print(numericValue + "\t");
                                }
                            }
                            break;
                        case STRING:
                            String stringValue = cell.getStringCellValue();
                            System.out.print(stringValue + "\t");
                            break;
                        case BOOLEAN:
                            boolean booleanValue = cell.getBooleanCellValue();
                            System.out.print(booleanValue + "\t");
                            break;
                        case FORMULA:
                            String formulaValue = cell.getCellFormula();
                            System.out.print(formulaValue + "\t");
                            break;
                        default:
                            System.out.println("\t");
                            break;
                    }
                }
                System.out.println();
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(MemberDTO[] listMembers) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Members");

        int rowCount = 0;

        for (MemberDTO member : listMembers) {
            Row row = sheet.createRow(rowCount++);
            writeMember(workbook,member, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream("/Users/rookedsysc/Downloads/java-excel-write-example.xlsx")) {
            workbook.write(outputStream);
        }
    }

    private void writeMember(XSSFWorkbook workbook, MemberDTO member, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(member.getName());

        // 시간 형식으로 데이터 넣는 방법
        // 엑셀에 month/day/year hour:minute 형식으로 데이터 넣기
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        cell = row.createCell(1);
        cell.setCellValue(member.getBirthDate());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellValue(member.getPhone());

        cell = row.createCell(3);
        cell.setCellValue(member.isMarried());
    }
}

