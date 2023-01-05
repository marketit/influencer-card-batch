package inflma.opAdmin.dto;

import lombok.Getter;

@Getter
public class TransactionRequestReportDto {
    private Long user_id;
    private Long request_id;
    private String bank;
    private String account;
    private String name;
    private String jumin;
    private int country;
    private int num;
    private int year;
    private int month;
    private int day;
    private int request_price;
    private int tax_rate;
    private int income_tax;
    private int local_tax;
    private int actual_refund;
    private String message;
}
