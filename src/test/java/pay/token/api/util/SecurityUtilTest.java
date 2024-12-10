package pay.token.api.util;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import pay.token.api.config.properties.KeyProperties;

@Slf4j
@SpringBootTest
class SecurityUtilTest {

  @Autowired
  private KeyProperties keyProperties;

  @Disabled
  @Test
  void makeCardValue() {
    final String text = "test";
    final String encrypted = SecurityUtil.makeEncrypted(text, keyProperties.getCardPublic());
    final String decrypted = SecurityUtil.makeDecrypted(encrypted, keyProperties.getCardPrivate());

    log.info("text : {}", text);
    log.info("encrypted : {}", encrypted);
    log.info("decrypted : {}", decrypted);
  }

  @Disabled
  @Test
  void makeKey() {
    final SecurityUtil util = new SecurityUtil();
    util.invokeKeys();
    final String publicKey = SecurityUtil.getPublicKeyToString(util.getPublicKey());
    final String privateKey = SecurityUtil.getPrivateKeyToString(util.getPrivateKey());

    log.info("=============================================================");
    log.info("================== New Key Pairs ============================");
    log.info("=============================================================");
    log.info("publicKey: {}", publicKey);
    log.info("privateKey: {}", privateKey);
    log.info("=============================================================");
  }

  @Disabled
  @Test
  void makeTokenValue() {
    final String text = "2_1733842967897";
    final String encrypted = SecurityUtil.makeEncrypted(text, keyProperties.getTokenPublic());
    final String decrypted = SecurityUtil.makeDecrypted(encrypted, keyProperties.getTokenPrivate());

    log.info("text : {}", text);
    log.info("encrypted : {}", encrypted);
    log.info("decrypted : {}", decrypted);
  }


}
