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
   * 공개키
   */
  private String publicText;

  /**
   * 개인키
   */
  private String privateText;

}
