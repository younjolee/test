package net.datasa.ex_cashbook.domain.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 가계부 정보 엔티티
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cashbook_info")
public class CashbookEntity {
    //일련번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_num")
    private Integer infoNum;

    //작성자 정보 (외래키)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private MemberEntity member;

    //구분
    @Column(name = "type", nullable = false, length = 20)
    private String type;

    //내역
    @Column(name = "memo", length = 1000)
    private String memo;

    //금액
    @Column(name = "amount", columnDefinition = "int default 0")
    private Integer amount = 0;

    //날짜
    @Column(name = "input_date", nullable = false, columnDefinition = "date")
    private LocalDate inputDate;
}
