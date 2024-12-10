package pay.token.api.domain.card.service.command;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import pay.token.api.config.properties.KeyProperties;
import pay.token.api.jpa.card.entity.CardEntity;
import pay.token.api.jpa.card.repository.CardRepository;
import pay.token.api.request.CardRequest;
import pay.token.api.util.SecurityUtil;

/**
 * 카드 등록 파라미터 확인 서비스
 *
 * @author Jinhyang
 */
@RequiredArgsConstructor
@Service
public class CardEntryService {

  private final CardRepository cardRepository;
  private final KeyProperties keyProperties;

  /**
   * 카드 정보 등록
   *
   * @param param {@link CardRequest}
   * @return 등록 카드 ID
   */
  @Transactional
  public Long entry(CardRequest param) {
    final CardEntity card = makeCardEntity(param);
    saveCardEntity(card);
    return card.getCardRefId();
  }

  /**
   * 카드 정보 생성
   *
   * @param param {@link CardRequest}
   * @return {@link CardEntity}
   */
  private CardEntity makeCardEntity(CardRequest param) {
    final String payerName = SecurityUtil.makeEncrypted(param.getPayerName(), keyProperties.getPublicText());
    final String code = SecurityUtil.makeEncrypted(param.getCode(), keyProperties.getPublicText());
    final String number = SecurityUtil.makeEncrypted(param.getNumber(), keyProperties.getPublicText());
    final String validYear = SecurityUtil.makeEncrypted(param.getValidYear(), keyProperties.getPublicText());
    final String validMonth = SecurityUtil.makeEncrypted(param.getValidMonth(), keyProperties.getPublicText());
    final String cvs = SecurityUtil.makeEncrypted(param.getCvs(), keyProperties.getPublicText());
    return new CardEntity(param.getPayerCi(), payerName, code, number, validYear, validMonth, cvs);
  }

  /**
   * 카드 정보 저장
   *
   * @param card {@link CardEntity}
   */
  private void saveCardEntity(CardEntity card) {
    cardRepository.save(card);
  }

}
