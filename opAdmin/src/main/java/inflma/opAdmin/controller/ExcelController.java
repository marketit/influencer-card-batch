package inflma.opAdmin.controller;

import inflma.opAdmin.service.ExcelServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelServiceImpl excelService;

    @GetMapping(value = "/marketit/transaction-request")
    public void transactionRequestExcel(
            HttpServletResponse response,
            @RequestParam HashMap<String, Object> param
    ) throws IOException, IllegalAccessException {
        List<HashMap<String, Object>> body = excelService.transactionRequestExcel(param);
        List<String> header = Arrays.asList("은행명", "계좌번호", "이름", "요청한 금액", "세금후 금액", "세금");

        transactionRequestExcelData(response, header, body);
    }

    private void transactionRequestExcelData(HttpServletResponse response, List<String> header, List<HashMap<String, Object>> body) throws FileNotFoundException, IOException, IllegalAccessException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("첫번째 시트");
        Row row = null;
        Cell cell =  null;
        int rowNum = 0;

        // Header

        row = sheet.createRow(rowNum++);
        for (int i = 0; i < header.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(header.get(i));
        }

        // Body
        for (int i = 0; i < body.size(); i++) {
            int cellCnt = 0;
            row = sheet.createRow(rowNum++);

            if (body.get(i).get("bank") != null) {
                cell = row.createCell(cellCnt++);
                cell.setCellValue(body.get(i).get("bank").toString());
            }
            if (body.get(i).get("account") != null) {
                cell = row.createCell(cellCnt++);
                cell.setCellValue(body.get(i).get("account").toString());
            }
            if (body.get(i).get("name") != null) {
                cell = row.createCell(cellCnt++);
                cell.setCellValue(body.get(i).get("name").toString());
            }
            if (body.get(i).get("request_price") != null) {
                cell = row.createCell(cellCnt++);
                cell.setCellValue(body.get(i).get("request_price").toString());
            }

            if (body.get(i).get("actual_refund") != null) {
                cell = row.createCell(cellCnt++);
                cell.setCellValue(body.get(i).get("actual_refund").toString());
            }
            if (body.get(i).get("tax_fee") != null) {
                cell = row.createCell(cellCnt++);
                cell.setCellValue(body.get(i).get("tax_fee").toString());
            }

        }

        // 컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
//        response.setHeader("Content-Disposition", "attachment;filename=example.xls");
        response.setHeader("Content-Disposition", "attachment;filename=marketit-trabsactuib.xls");
        response.setCharacterEncoding("utf-8");
        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
    }
}