package net.datasa.ex_cashbook.domain.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 가계부 정보 DTO
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CashbookDTO {
    private Integer infoNum;    //일련번호
    private String memberId;    //사용자 아이디
    private String type;        //구분
    private String memo;        //내역
    private Integer amount;     //금액
    LocalDate inputDate;        //날짜
}
