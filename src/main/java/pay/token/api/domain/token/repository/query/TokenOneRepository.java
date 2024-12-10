package pay.token.api.domain.token.repository.query;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import pay.token.jooq.schema.public_.tables.records.TokenRecord;

import static pay.token.jooq.schema.public_.Tables.TOKEN;

/**
 * 토큰 조회 저장소
 *
 * @author Jinhyang
 */
@RequiredArgsConstructor
@Repository
public class TokenOneRepository {

  private final DSLContext dsl;

  /**
   * 토큰 조회
   *
   * @param text 토큰 문자열
   * @return {@link  TokenRecord}
   */
  public TokenRecord findOne(String text) {
    return dsl.selectFrom(TOKEN).where(TOKEN.TOKEN_TEXT.eq(text)).orderBy(TOKEN.TOKEN_ID.desc()).limit(1).fetchOne();
  }

}
