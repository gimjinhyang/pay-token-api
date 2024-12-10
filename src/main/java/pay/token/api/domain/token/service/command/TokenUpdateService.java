package pay.token.api.domain.token.service.command;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import pay.token.api.jpa.token.entity.TokenEntity;
import pay.token.api.jpa.token.enums.TokenStatus;
import pay.token.api.jpa.token.repository.TokenRepository;

/**
 * 토큰 수정 서비스
 *
 * @author Jinhyang
 */
@RequiredArgsConstructor
@Service
public class TokenUpdateService {

  private final TokenRepository tokenRepository;

  /**
   * 토큰 승인 처리
   *
   * @param tokenId 토큰 ID
   */
  @Transactional
  public void approved(Long tokenId) {

    verifyTokenId(tokenId);

    // 토큰 정보 조회
    final TokenEntity token = getTokenEntity(tokenId);

    // 토큰 상태 변경
    updateStatus(token, TokenStatus.APPROVED);
  }

  private TokenEntity getTokenEntity(Long tokenId) {
    final Optional<TokenEntity> token = tokenRepository.findById(tokenId);
    if (!token.isPresent()) {
      throw new IllegalArgumentException("토큰 정보가 없습니다.");
    }
    return token.get();
  }

  /**
   * 토큰 상태 갱신
   *
   * @param token  {@link TokenEntity}
   * @param status {@link TokenStatus}
   */
  private void updateStatus(TokenEntity token, TokenStatus status) {
    token.updateStatus(status);
  }

  /**
   * 토큰 ID 확인
   *
   * @param tokenId 토큰 ID
   */
  private void verifyTokenId(Long tokenId) {
    if (tokenId == null || tokenId < 1L) {
      throw new IllegalArgumentException("토큰 ID를 확인해 주세요.");
    }
  }

}
