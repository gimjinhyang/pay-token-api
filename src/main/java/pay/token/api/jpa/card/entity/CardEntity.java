package pay.token.api.jpa.card.entity;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
  @Column(name = "card_cvs", updatable = false, nullable = false)
  private String cvs;

  /**
   * 등록시간
   */
  @Column(name = "created_time", updatable = false, nullable = false)
  private LocalDateTime createdTime = LocalDateTime.now();


  // //////////////////////////////////////////////////////////////
  //
  // Constructors
  //
  // //////////////////////////////////////////////////////////////

  public CardEntity(String payerCi, String payerName, String code, String number, String validYear, String validMonth,
      String cvs) {
    setPayerCi(payerCi);
    setPayerName(payerName);
    setCode(code);
    setNumber(number);
    setValidYear(validYear);
    setValidMonth(validMonth);
    setCvs(cvs);
  }


  // //////////////////////////////////////////////////////////////
  //
  // Methods
  //
  // //////////////////////////////////////////////////////////////

  private void setCode(String code) {
    if (StringUtils.isBlank(code)) {
      throw new IllegalArgumentException("no code");
    }
    this.code = code;
  }

  private void setCvs(String cvs) {
    if (StringUtils.isBlank(cvs)) {
      throw new IllegalArgumentException("no cvs");
    }
    this.cvs = cvs;
  }

  private void setNumber(String number) {
    if (StringUtils.isBlank(number)) {
      throw new IllegalArgumentException("no number");
    }
    this.number = number;
  }

  private void setPayerCi(String payerCi) {
    if (StringUtils.isBlank(payerCi)) {
      throw new IllegalArgumentException("no payer ci");
    }
    this.payerCi = payerCi;
  }

  private void setPayerName(String payerName) {
    if (StringUtils.isBlank(payerName)) {
      throw new IllegalArgumentException("no payer name");
    }
    this.payerName = payerName;
  }

  private void setValidMonth(String validMonth) {
    if (StringUtils.isBlank(validMonth)) {
      throw new IllegalArgumentException("no valid month");
    }
    this.validMonth = validMonth;
  }

  private void setValidYear(String validYear) {
    if (StringUtils.isBlank(validYear)) {
      throw new IllegalArgumentException("no valid year");
    }
    this.validYear = validYear;
  }

}
