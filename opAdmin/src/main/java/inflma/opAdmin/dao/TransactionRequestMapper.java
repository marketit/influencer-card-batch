package inflma.opAdmin.dao;

import inflma.opAdmin.dto.TransactionRequestDto;
import inflma.opAdmin.dto.TransactionRequestReportDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface TransactionRequestMapper {
    List<TransactionRequestDto> transactionRequestExcel(HashMap<String, Object> param);

    void refusalTransactionRequest(java.util.HashMap<java.lang.String,java.lang.Object> param);

    List<TransactionRequestDto> transactionRequestWithdrawalList(TransactionRequestDto transactionRequestDto);

    int transactionRequestWithdrawalCount(TransactionRequestDto transactionRequestDto);

    List<TransactionRequestReportDto> transactionRequestMonth(HashMap<String, Object> param);
}
