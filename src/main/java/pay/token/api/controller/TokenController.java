package pay.token.api.controller;

import com.google.common.collect.ImmutableMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pay.token.api.domain.token.service.command.TokenEntryService;
import pay.token.api.domain.token.service.command.TokenUpdateService;
import pay.token.api.domain.token.service.query.TokenEntryValidateService;
import pay.token.api.domain.token.service.query.TokenVerifyService;
import pay.token.api.request.TokenRequest;
import pay.token.api.request.TokenVerifyRequest;
import pay.token.api.response.DataResponse;
import pay.token.api.response.ErrorResponse;

/**
 * 토큰 관리 컨트롤러
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/token", produces = MediaType.APPLICATION_JSON_VALUE)
public class TokenController {

  private final TokenEntryValidateService tokenEntryValidateService;
  private final TokenEntryService tokenEntryService;
  private final TokenVerifyService tokenVerifyService;
  private final TokenUpdateService tokenUpdateService;


  /**
   * 토큰 발급 요청
   *
   * @param param {@link TokenRequest}
   * @return
   */
  @PostMapping
  public ResponseEntity<?> post(@RequestBody TokenRequest param) {
    try {
      // 토큰 파라미터 확인
      tokenEntryValidateService.validate(param);

      // 토큰 발급
      final String token = tokenEntryService.entry(param);
      return ResponseEntity.ok(new DataResponse(ImmutableMap.of("token", token)));

    } catch (IllegalArgumentException e) {
      log.warn("caught a " + e.getClass() + " with message: " + e.getMessage(), e);
      return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()));
    } catch (Exception e) {
      log.warn("caught a " + e.getClass() + " with message: " + e.getMessage(), e);
      return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST, "토큰 발급을 실패했습니다"));
    }
  }

  /**
   * 토큰 유효성 확인
   *
   * @param param {@link TokenVerifyRequest}
   * @return
   */
  @PostMapping(value = "/verify")
  public ResponseEntity<?> postAsVerify(@RequestBody TokenVerifyRequest param) {
    try {
      // 토큰 유효성 확인
      final Long tokenId = tokenVerifyService.validate(param);

      // 토큰 승인 처리
      tokenUpdateService.approved(tokenId);

      // 토큰 발급
      return ResponseEntity.ok(new ErrorResponse(HttpStatus.OK, "ok"));

    } catch (IllegalArgumentException e) {
      log.warn("caught a " + e.getClass() + " with message: " + e.getMessage(), e);
      return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()));
    } catch (Exception e) {
      log.warn("caught a " + e.getClass() + " with message: " + e.getMessage(), e);
      return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST, "토큰 유효성 확인을 실패했습니다"));
    }
  }

}
