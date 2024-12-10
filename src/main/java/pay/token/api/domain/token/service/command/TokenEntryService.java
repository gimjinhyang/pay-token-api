package pay.token.api.domain.token.service.command;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import pay.token.api.config.properties.KeyProperties;
import pay.token.api.jpa.token.entity.TokenEntity;
import pay.token.api.jpa.token.repository.TokenRepository;
import pay.token.api.request.TokenRequest;
import pay.token.api.util.SecurityUtil;

/**
 * 토큰 발급 서비스
 *
 * @author Jinhyang
 */
@RequiredArgsConstructor
@Service
public class TokenEntryService {

  private final TokenRepository tokenRepository;
  private final KeyProperties keyProperties;

  /**
   * 토큰 등록
   *
   * @param param {@link TokenRequest}
   * @return 토큰
   */
  @Transactional
  public String entry(TokenRequest param) {

    // 토큰 만료 시간 생성
    final LocalDateTime expire = makeTokenExpire();
    // 토큰 값 생성
    final String text = makeTokenText(param.getCardRefId(), expire);
    // 토큰 정보 생성
    final TokenEntity token = makeTokenEntity(param.getCardRefId(), text, expire);

    // 토큰 정보 저장
    saveTokenEntity(token);

    return text;
  }

  /**
   * 토큰 정보 생성
   *
   * @param cardRefId 카드 ID
   * @param text      토큰 값
   * @param expire    만료시간
   * @return {@link TokenEntity}
   */
  private TokenEntity makeTokenEntity(Long cardRefId, String text, LocalDateTime expire) {
    return new TokenEntity(cardRefId, text, expire);
  }

  /**
   * 토큰 만료 시간 생성
   *
   * @return 만료 시간
   */
  private LocalDateTime makeTokenExpire() {
    return LocalDateTime.now().plusMinutes(5);
  }

  /**
   * 토큰 값 생성
   *
   * @param cardRefId 카드 ID
   * @param expire    만료 시간
   * @return 토큰 값
   */
  private String makeTokenText(Long cardRefId, LocalDateTime expire) {
    final Timestamp time = Timestamp.valueOf(expire);
    final String text = String.format("%s_%s", cardRefId, time.getTime());
    return SecurityUtil.makeEncrypted(text, keyProperties.getTokenPublic());
  }

  /**
   * 토큰 정보 저장
   *
   * @param token {@link TokenEntity}
   */
  private void saveTokenEntity(TokenEntity token) {
    // 토큰 저장
    tokenRepository.save(token);
  }

}
