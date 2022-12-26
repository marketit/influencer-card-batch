package inflma.opAdmin.service;

import inflma.opAdmin.dao.ExcelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelServiceImpl {
    private final ExcelMapper excelMapper;

    public List<HashMap<String,Object>> transactionRequestExcel(HashMap<String,Object> param){
        return excelMapper.transactionRequestExcel(param);
    }

    public void completeTransactionRequest(HashMap<String,Object> param) {
        excelMapper.completeTransactionRequest(param);
    }
}
