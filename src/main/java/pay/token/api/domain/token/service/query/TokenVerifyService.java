package pay.token.api.domain.token.service.query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import pay.token.api.config.properties.KeyProperties;
import pay.token.api.domain.token.repository.query.TokenOneRepository;
import pay.token.api.jpa.token.enums.TokenStatus;
import pay.token.api.request.TokenVerifyRequest;
import pay.token.api.util.SecurityUtil;
import pay.token.jooq.schema.public_.tables.records.TokenRecord;

/**
 * 토큰 유효성 확인 서비스
 *
 * @author Jinhyang
 */
@RequiredArgsConstructor
@Service
public class TokenVerifyService {

  private final TokenOneRepository tokenOneRepository;
  private final KeyProperties keyProperties;


  /**
   * 토큰 유효성 확인
   *
   * @param param {@link TokenVerifyRequest}
   * @return
   */
  @Transactional(readOnly = true)
  public Long validate(TokenVerifyRequest param) {
    verifyTokenValue(param.getToken());

    final Long cardRefId = getCardRefId(param.getToken());
    final TokenRecord token = getTokenRecord(param.getToken());

    verifyCardRefId(cardRefId, token);
    verifyExpireTime(token);
    verifyStatus(token);

    return token.getTokenId();
  }

  /**
   * 토큰에서 카드 ID 반환
   *
   * @param token 토큰
   * @return 카드 ID
   */
  private Long getCardRefId(String token) {
    final String decrypted = SecurityUtil.makeDecrypted(token, keyProperties.getTokenPrivate());
    final String id = StringUtils.substringBefore(decrypted, "_");
    return Long.valueOf(id);
  }

  /**
   * 토큰 정보 조회
   *
   * @param text 토큰 문자열
   * @return {@link TokenRecord}
   */
  private TokenRecord getTokenRecord(String text) {
    final TokenRecord record = tokenOneRepository.findOne(text);
    if (record == null) {
      throw new IllegalStateException("토큰이 없습니다.");
    }
    return record;
  }

  /**
   * 카드 ID 동일여부 확인
   *
   * @param cardRefId 카드 ID
   * @param token     {@link TokenRecord}
   */
  private void verifyCardRefId(Long cardRefId, TokenRecord token) {
    if (cardRefId.longValue() != token.getCardRefId().longValue()) {
      throw new IllegalArgumentException("카드 정보가 다릅니다.");
    }
  }

  /**
   * 토큰 유효시간 확인
   *
   * @param token {@link TokenRecord}
   */
  private void verifyExpireTime(TokenRecord token) {
    final LocalDateTime time = token.getTokenExpire();
    if (time.isBefore(LocalDateTime.now())) {
      throw new IllegalArgumentException("토큰 유효시간이 지났습니다.");
    }
  }

  /**
   * 토큰 상태 확인
   *
   * @param token {@link TokenRecord}
   */
  private void verifyStatus(TokenRecord token) {
    if (!StringUtils.equals(token.getTokenStatusCode(), TokenStatus.READY.name())) {
      throw new IllegalArgumentException("만료된 토큰 입니다.");
    }
  }

  /**
   * 토큰 값 확인
   *
   * @param token 토큰 값
   */
  private void verifyTokenValue(String token) {
    if (StringUtils.isBlank(token)) {
      throw new IllegalArgumentException("토큰 값을 확인해 주세요.");
    }
  }

}
