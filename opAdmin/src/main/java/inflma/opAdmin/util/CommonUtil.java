package inflma.opAdmin.util;

import inflma.opAdmin.result.ResultPage;
import inflma.opAdmin.result.page.PageData;
import inflma.opAdmin.result.page.Pagination;

import java.util.List;

public class CommonUtil {

    public static ResultPage paginate(
            int page,
            int totalCount,
            List<?> list
    ) {
        Pagination pagination = new Pagination(page, totalCount);
        PageData pageData = new PageData(list, pagination);
        ResultPage resultPage = new ResultPage(true, pageData);

        return resultPage;
    }

}
