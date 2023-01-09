package inflma.opAdmin.dao;

import inflma.opAdmin.dto.AlramListDto;
import inflma.opAdmin.dto.TransactionRequestDto;
import inflma.opAdmin.dto.TransactionRequestReportDto;
import inflma.opAdmin.dto.TransactionRequestState;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface TransactionRequestMapper {
    List<TransactionRequestDto> transactionRequestExcel(HashMap<String, Object> param);

    List<TransactionRequestDto> transactionRequestWithdrawalList(TransactionRequestDto transactionRequestDto);

    int transactionRequestWithdrawalCount(TransactionRequestDto transactionRequestDto);

    List<TransactionRequestReportDto> transactionRequestMonth(HashMap<String, Object> param);

    int transactionRequestChangeState(TransactionRequestState transactionRequestState);

    AlramListDto findByRequestId(String requestId);

    void requestLog(AlramListDto requestInfo);
}
