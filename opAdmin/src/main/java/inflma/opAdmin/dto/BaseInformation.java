package inflma.opAdmin.dto;

import lombok.Data;

@Data
public class BaseInformation {
    private String createdAt;
    private String createId;
    private String updateId;
    private String updatedAt;
    private String closeId;
    private String closeDate;
    private String completeId;
    private String completeDate;

    private int perPage = 15; // 보여줄 페이지 개수
    private int page = 1; // 현재 활성 페이지
    private int pageOffset;

    private int totalCnt;
    private int pageTotal; //페이지 총 개수
    private int pageDataLimit; // 보여줄 데이터 개수
    private int pageStart; // 페이지 시작 번호
    private int pageEnd; // 페이지 끝 번호

    private String startDate;
    private String endDate;
    private String groupByDate;
}
