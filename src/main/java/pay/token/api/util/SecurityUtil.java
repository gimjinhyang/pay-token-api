package pay.token.api.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import lombok.extern.slf4j.Slf4j;

/**
 * 보안 유틸 (RSA 암호화)
 *
 * @author Jinhyang
 */
@Slf4j
public class SecurityUtil {

  private KeyPair keyPair;

  /**
   * PrivateKey를 문자열로 반환
   *
   * @param key {@link PrivateKey}
   * @return 문자열 키
   */
  public static String getPrivateKeyToString(PrivateKey key) {
    final String encode = Base64.getEncoder().encodeToString(key.getEncoded());
    return removeNewLine(encode);
  }

  /**
   * PublicKey를 문자열로 반환
   *
   * @param key {@link PublicKey}
   * @return 문자열 키
   */
  public static String getPublicKeyToString(PublicKey key) {
    final String encode = Base64.getEncoder().encodeToString(key.getEncoded());
    return removeNewLine(encode);
  }

  /**
   * 복호화
   *
   * @param text of type String indicating the encrypted contents.
   * @param key  of type {@link PrivateKey}
   * @return decrypted value as a String
   */
  public static String makeDecrypted(String text, String key) {
    if (text == null) {
      return null;
    }

    try {
      final PrivateKey privateKey = getPrivateKeyFromString(key);
      final Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.DECRYPT_MODE, privateKey);

      byte[] encryptedBytes = Base64.getDecoder().decode(text.getBytes());
      byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
      return new String(decryptedBytes);

    } catch (Exception e) {
      log.error("caught a " + e.getClass() + " with message: " + e.getMessage(), e);
    }

    return null;
  }

  /**
   * 암호화
   *
   * @param text 암호화 대상 문자열
   * @param key  PublicKey 문자열
   * @return 암호화 문자열
   */
  public static String makeEncrypted(String text, String key) {
    if (text == null || key == null) {
      return null;
    }

    try {
      final PublicKey publicKey = getPublicKeyFromString(key);
      final Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);

      byte[] bytes = cipher.doFinal(text.getBytes());
      final String encrypted = Base64.getEncoder().encodeToString(bytes);
      return removeNewLine(encrypted);

    } catch (Exception e) {
      log.error("caught a " + e.getClass() + " with message: " + e.getMessage(), e);
    }

    return null;
  }

  /**
   * 문자열을 PrivateKey로 반환
   *
   * @param key 문자열 키
   * @return {@link PrivateKey}
   */
  private static PrivateKey getPrivateKeyFromString(String key)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    final KeyFactory factory = KeyFactory.getInstance("RSA");
    byte[] bytes = Base64.getDecoder().decode(key.getBytes());
    final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
    return factory.generatePrivate(spec);
  }

  /**
   * 문자열을 PublicKey로 변환
   *
   * @param key 문자열 키
   * @return {@link PublicKey}
   */
  private static PublicKey getPublicKeyFromString(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
    final KeyFactory factory = KeyFactory.getInstance("RSA");
    byte[] bytes = Base64.getDecoder().decode(key.getBytes());
    final X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
    return factory.generatePublic(spec);
  }

  /**
   * 줄바꿈 문자(\n) 제거
   *
   * @param text 문자열
   * @return 문자열
   */
  private static String removeNewLine(String text) {
    if (text == null) {
      return null;
    }
    return text.replaceAll("\r", "").replaceAll("\n", "");
  }

  /**
   * PrivateKey 반환
   *
   * @return {@link PrivateKey}
   */
  public PrivateKey getPrivateKey() {
    return keyPair.getPrivate();
  }

  /**
   * PublicKey 반환
   *
   * @return {@link PublicKey}
   */
  public PublicKey getPublicKey() {
    return keyPair.getPublic();
  }

  /**
   * key pair 생성
   */
  public void invokeKeys() {
    try {
      KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
      generator.initialize(2048);
      keyPair = generator.generateKeyPair();
    } catch (Exception e) {
      log.error("caught a " + e.getClass() + " with message: " + e.getMessage(), e);
    }
  }
}
