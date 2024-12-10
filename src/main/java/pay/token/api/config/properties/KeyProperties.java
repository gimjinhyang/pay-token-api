package pay.token.api.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Key Property
 *
 * @author Jinhyang
 */
@Getter
@Setter
@ToString
@ConfigurationProperties("key")
public class KeyProperties {

  /**
   * 카드 공개키
   */
  private String cardPublic;

  /**
   * 카드 개인키
   */
  private String cardPrivate;

  /**
   * 토큰 공개키
   */
  private String tokenPublic;

  /**
   * 토큰 개인키
   */
  private String tokenPrivate;

}
