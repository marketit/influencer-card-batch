package inflma.opAdmin.controller;

import inflma.opAdmin.dto.TransactionRequestDto;
import inflma.opAdmin.result.ResultPage;
import inflma.opAdmin.service.TransactionRequestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("inflma/transaction-request")
public class TransactionRequestController {

    private final TransactionRequestServiceImpl transactionRequestService;

    @GetMapping(value = "withdrawal")
    @ResponseBody
    public ResultPage transactionRequest(
            TransactionRequestDto transactionRequestDto
    ){
        return transactionRequestService.withdrawal(transactionRequestDto);
    }

    @GetMapping(value = "excel")
    public void transactionRequestExcel(
            HttpServletResponse response,
            @RequestParam HashMap<String, Object> param
    ) throws IOException {
        List<HashMap<String, Object>> body = transactionRequestService.transactionRequestExcel(param);
        List<String> header = List.of("은행명", "계좌번호", "이름", "요청한 금액", "","","","일련번호");

        transactionRequestExcelData(response, header, body);
    }

    @GetMapping(value="refusal")
    public void refusalTransactionRequest(
            @RequestParam HashMap<String, Object> param
    ) throws IOException {
        transactionRequestService.refusalTransactionRequest(param);
    }

    @GetMapping(value="complete")
    public void completeTransactionRequest(
            @RequestParam HashMap<String, Object> param
    ){
        transactionRequestService.completeTransactionRequest(param);
    }

    private void transactionRequestExcelData(HttpServletResponse response, List<String> header, List<HashMap<String, Object>> body) throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("첫번째 시트");
        Row row;
        Cell cell;
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
            if (body.get(i).get("message") != null) {
                cell = row.createCell(cellCnt++);
                cell.setCellValue(body.get(i).get("message").toString());
            }
            cell = row.createCell(cellCnt++);
            cell.setCellValue("");
            cell = row.createCell(cellCnt++);
            cell.setCellValue("");
            cell = row.createCell(cellCnt++);
            cell.setCellValue("");
            if (body.get(i).get("request_id") != null) {
                cell = row.createCell(cellCnt++);
                cell.setCellValue(body.get(i).get("request_id").toString());
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
