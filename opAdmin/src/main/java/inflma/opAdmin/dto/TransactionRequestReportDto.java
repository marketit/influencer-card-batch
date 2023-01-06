package inflma.opAdmin.dto;

import lombok.Getter;

@Getter
public class TransactionRequestReportDto {
    private Long userId;
    private Long requestId;
    private String bank;
    private String account;
    private String name;
    private String jumin;
    private int country;
    private int num;
    private int year;
    private int month;
    private int day;
    private int requestPrice;
    private int taxRate;
    private int incomeTax;
    private int localTax;
    private int actualRefund;
}
