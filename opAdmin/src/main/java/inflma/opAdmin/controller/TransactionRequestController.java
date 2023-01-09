package inflma.opAdmin.controller;

import inflma.opAdmin.dto.TransactionRequestCancelDto;
import inflma.opAdmin.dto.TransactionRequestDto;
import inflma.opAdmin.dto.TransactionRequestReportDto;
import inflma.opAdmin.result.ResultBody;
import inflma.opAdmin.result.ResultPage;
import inflma.opAdmin.service.TransactionRequestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public List<TransactionRequestDto> transactionRequestExcel(
            @RequestParam HashMap<String, Object> param
    ) {
        return transactionRequestService.transactionRequestExcel(param);
    }

    @GetMapping(value = "month/excel")
    @ResponseBody
    public List<TransactionRequestReportDto> transactionRequestMonth(
            @RequestParam HashMap<String, Object> param) {
        return transactionRequestService.transactionRequestMonth(param);
    }


    @GetMapping(value = "withdrawal/complete")
    public int requestComplete(
            @RequestParam HashMap<String, Object> param
    ) {
        return transactionRequestService.requestComplete(param);
    }

    @GetMapping(value = "withdrawal/cancel")
    @ResponseBody
    public ResultBody withdrawalCancel(
            TransactionRequestCancelDto param
    ) {
        return transactionRequestService.withdrawalCancel(param);
    }

}
