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
    ) {
        return transactionRequestService.withdrawal(transactionRequestDto);
    }

    @GetMapping(value = "withdrawal/excel")
    public void transactionRequestExcel(
            HttpServletResponse response,
            @RequestParam HashMap<String, Object> param
    ) throws IOException {
        List<HashMap<String, Object>> body = transactionRequestService.transactionRequestExcel(param);
        List<String> header = List.of("은행명", "계좌번호", "이름", "요청한 금액", "메세지","", "", "", "", "일련번호");
        List<String> bodyList = List.of("bank", "account", "name", "actual_refund" ,"message", "","","","","request_id");
        transactionRequestExcelData(response, header, body, bodyList);
    }

    @GetMapping(value = "month")
    public void transactionRequestMonth(
            HttpServletResponse response,
            @RequestParam HashMap<String, Object> param) throws IOException {
        List<HashMap<String, Object>> body = transactionRequestService.transactionRequestMonth(param);
        List<String> header = List.of("유저아이디", "요청번호", "은행", "계좌번호", "상호(성명)", "주민등록번호", "내/외", "소득구분코드", "년", "월", "일", "지급액(세전)", "세율(%)", "소득세", "지방소득세", "지급액(세후)");
        List<String> bodyList = List.of("user_id", "request_id", "bank", "account", "name", "jumin", "country", "num", "year", "month", "day", "request_price", "tax_rate", "income_tax", "local_tax", "actual_refund");

        transactionRequestExcelData(response, header, body, bodyList);
    }

    @GetMapping(value = "refusal")
    public void refusalTransactionRequest(
            @RequestParam HashMap<String, Object> param
    ) {
        transactionRequestService.refusalTransactionRequest(param);
    }

    @GetMapping(value = "complete")
    public void completeTransactionRequest(
            @RequestParam HashMap<String, Object> param
    ) {
        transactionRequestService.completeTransactionRequest(param);
    }

    private void transactionRequestExcelData(HttpServletResponse response, List<String> header, List<HashMap<String, Object>> body, List<String> bodyList) throws IOException {
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
            for (String s : bodyList) {
                cell = row.createCell(cellCnt++);
                if (body.get(i).get(s) == null) {
                    cell.setCellValue("");
                    continue;
                }
                cell.setCellValue(body.get(i).get(s).toString());
            }
        }

        // 컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=marketit-trabsactuib.xls");
        response.setCharacterEncoding("utf-8");

        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
    }

}
