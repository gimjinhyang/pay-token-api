package pay.token.api.domain.token.service.query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import pay.token.api.jpa.card.entity.CardEntity;
import pay.token.api.jpa.card.repository.CardRepository;
import pay.token.api.request.TokenRequest;

/**
 * 토큰 발급 파라미터 확인 서비스
 *
 * @author Jinhyang
 */
@RequiredArgsConstructor
@Service
public class TokenEntryValidateService {

  private final CardRepository cardRepository;


  /**
   * 토큰 발급 파라미터 확인
   *
   * @param param {@link TokenRequest}
   */
  @Transactional(readOnly = true)
  public void validate(TokenRequest param) {
    verifyCardRefId(param.getCardRefId());
    verifyExistCard(param.getCardRefId());
  }

  /**
   * 카드 ID 확인
   *
   * @param cardRefId 카드 ID
   */
  private void verifyCardRefId(Long cardRefId) {
    if (cardRefId == null || cardRefId < 1L) {
      throw new IllegalArgumentException("카드 ID를 확인해 주세요.");
    }
  }

  /**
   * 등록 카드 존재 여부 확인
   *
   * @param cardRefId 카드 ID
   */
  private void verifyExistCard(Long cardRefId) {
    final Optional<CardEntity> card = cardRepository.findById(cardRefId);
    if (!card.isPresent()) {
      throw new IllegalStateException("등록된 카드 정보가 없습니다.");
    }
  }

}
