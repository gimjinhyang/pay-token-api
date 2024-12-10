package pay.token.api.domain.card.service.query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import pay.token.api.request.CardRequest;

/**
 * 카드 등록 파라미터 확인 서비스
 *
 * @author Jinhyang
 */
@Component
public class CardEntryValidateService {

  /**
   * 카드 등록 파라미터 확인
   *
   * @param param {@link CardRequest}
   */
  public void validate(CardRequest param) {
    verifyPayerCi(param.getPayerCi());
    verifyPayerName(param.getPayerName());
    verifyCardCode(param.getCode());
    verifyCardNumber(param.getNumber());
    verifyCardValidYear(param.getValidYear());
    verifyCardValidMonth(param.getValidMonth());
    verifyCardCvs(param.getCvs());
  }

  /**
   * 카드 코드 확인 (카드사 종류)
   *
   * @param code 카드 코드
   */
  private void verifyCardCode(String code) {
    if (StringUtils.isBlank(code)) {
      throw new IllegalArgumentException("카드 구분을 확인해 주세요.");
    }
  }

  /**
   * 카드 CVS 확인
   *
   * @param cvs 카드 CVS
   */
  private void verifyCardCvs(String cvs) {
    if (StringUtils.isBlank(cvs)) {
      throw new IllegalArgumentException("카드 cvs를 확인해 주세요.");
    }
  }

  /**
   * 카드 번호 확인
   *
   * @param number 카드 번호
   */
  private void verifyCardNumber(String number) {
    if (StringUtils.isBlank(number)) {
      throw new IllegalArgumentException("카드 번호를 확인해 주세요.");
    }
  }

  /**
   * 카드 유효기간 월 확인
   *
   * @param month 카드 유효기간 월
   */
  private void verifyCardValidMonth(String month) {
    if (StringUtils.isBlank(month)) {
      throw new IllegalArgumentException("카드 유효기간의 월을 확인해 주세요.");
    }
  }

  /**
   * 카드 유효기간 년도 확인
   *
   * @param year 카드 유효기간 년도
   */
  private void verifyCardValidYear(String year) {
    if (StringUtils.isBlank(year)) {
      throw new IllegalArgumentException("카드 유효기간의 년도를 확인해 주세요.");
    }
  }

  /**
   * 결제자 CI 확인
   *
   * @param ci 결제자 CI
   */
  private void verifyPayerCi(String ci) {
    if (StringUtils.isBlank(ci)) {
      throw new IllegalArgumentException("결제자 CI를 확인해 주세요.");
    }
  }

  /**
   * 결제자 이름 확인
   *
   * @param name 결제자 이름
   */
  private void verifyPayerName(String name) {
    if (StringUtils.isBlank(name)) {
      throw new IllegalArgumentException("결제자 이름을 확인해 주세요.");
    }
  }

}
