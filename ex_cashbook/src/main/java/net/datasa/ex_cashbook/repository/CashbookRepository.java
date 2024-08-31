package net.datasa.ex_cashbook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.datasa.ex_cashbook.domain.entity.CashbookEntity;

/**
 * 게시판 관련 repository
 */

@Repository
public interface CashbookRepository extends JpaRepository<CashbookEntity, Integer> {

    //한 사용자의 모든 가계부 내역을 날짜순으로 조회
    List<CashbookEntity> findByMember_MemberIdOrderByInputDate(String memberId);
    // findBy로 시작하는게 select문을 만들어주는 약속임
    // findByMember member라는 객체가 있음(entity에) . 그 객체 안에 memberId가 또 따로 있고
    // 전달받는건 회원아이디임(memberId)
    
    // select * from cashbook_info where member_id = 'xxx' order by input date; 
    // where 대신 여러개의 컬럼 대상으로 하는 것도 가능함
    // 위에 객체에 pagable 넣으면 페이지 나눠서 받을 수 있음
    
    
    //한 사용자의 특정 연도의 수입 또는 지출의 합계를 계산
    //전달받은 값을 Query의 지정한 곳에 넣어서 쿼리를 실행한다.
    //쿼리는 Entity 클래스의 이름을 기준으로 작성한다.
    //전달받은 값은 @Param에서 지정한 이름과 같은 (예) :year ) 부분에 대입된다.
    //매개변수의 이름은 상관없다. (예) y)
    // """는 문자열을 여러 줄에 걸쳐서 입력할 수 있게 해준다.
    @Query("""
        SELECT SUM(c.amount) FROM CashbookEntity c 
        WHERE 
            c.type = :type 
            AND YEAR(c.inputDate) = :year
            AND c.member.memberId = :id
     """)
    // :type 콜론 다음이 파라미터 이름임
    // 테이블에 타입이라는 컬럼의 "수입" 이런게 이걸 통해서 만들어짐
    // :year는 2024  
    // :id
    // "를 1개만 쓰면 1줄로 옆으로 써야함 (여러줄에 쓰려면 """)
    
    Optional<Integer> sumAmountByTypeAndYear(@Param("year") int y, @Param("type") String t, @Param("id") String id);
    // 이 단계에선 메서드 이름이 중요하진 않음(sumAmountByTypeAndYear)
    // 숫자(연도), 문자열(수입 or 지출), 문자열 id(로그인한 아이디로부터)
    // 위에는 배열이나, pagable객체도 올수 있음
    
    // 리턴 타입은 select문에 따라서 정확하게 지정해야 함
    // 만약 이름을 찾는 경우는 memberEntity lis 리턴해야 함
    
   
    
    
    
}
