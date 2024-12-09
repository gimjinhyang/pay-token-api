package pay.token.api.jpa.card.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Card Entity
 *
 * @author Jinhyang
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "card")
public class CardEntity {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_ref_id", insertable = false, updatable = false)
    private Long cardRefId;

    /**
     * 결제자 CI
     */
    @Column(name = "payer_ci", updatable = false, nullable = false)
    private String payerCi;

    /**
     * 결제자 이름
     */
    @Column(name = "payer_name", updatable = false, nullable = false)
    private String payerName;

    /**
     * 카드 코드
     */
    @Column(name = "card_code", updatable = false, nullable = false)
    private String code;

    /**
     * 카드 번호
     */
    @Column(name = "card_number", updatable = false, nullable = false)
    private String number;

    /**
     * 카드 유효기간 년도
     */
    @Column(name = "card_valid_year", updatable = false, nullable = false)
    private String validYear;

    /**
     * 카드 유효기간 월
     */
    @Column(name = "card_valid_month", updatable = false, nullable = false)
    private String validMonth;

    /**
     * 카드 CVS
     */
    @Column(name = "cvs", updatable = false, nullable = false)
    private String cvs;

    /**
     * 등록시간
     */
    @Column(name = "created_time", updatable = false, nullable = false)
    private long createdTime;

}
