package inflma.opAdmin.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ExcelMapper {
    List<HashMap<String, Object>> transactionRequestExcel(HashMap<String, Object> param);

    void completeTransactionRequest(HashMap<String, Object> param);
}
